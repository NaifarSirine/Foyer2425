package tn.esprit.spring.RestControllers;

import tn.esprit.spring.DAO.Entities.Chambre;
import tn.esprit.spring.DAO.Entities.TypeChambre;
import tn.esprit.spring.Services.Chambre.IChambreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class ChambreRestControllerTest {

    @Mock
    private IChambreService chambreService;

    @InjectMocks
    private ChambreRestController chambreRestController;

    private MockMvc mockMvc;

    private Chambre chambreMock;
    private Chambre chambreMock2;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(chambreRestController).build();

        // Set up mock Chambre objects
        chambreMock = new Chambre();
        chambreMock.setIdChambre(1L);
        chambreMock.setNumeroChambre(101);
        chambreMock.setTypeC(TypeChambre.SIMPLE);

        chambreMock2 = new Chambre();
        chambreMock2.setIdChambre(2L);
        chambreMock2.setNumeroChambre(102);
        chambreMock2.setTypeC(TypeChambre.DOUBLE);
    }

    @Test
    public void testAddOrUpdate() throws Exception {
        // Mock the service method
        when(chambreService.addOrUpdate(any(Chambre.class))).thenReturn(chambreMock);

        // Test the POST method with correctly formatted JSON
        mockMvc.perform(post("/chambre/addOrUpdate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"idChambre\":1, \"numeroChambre\":101, \"typeC\":\"SIMPLE\"}")) // Use "SIMPLE"
                .andExpect(status().isOk())  // Expect 200 OK
                .andExpect(jsonPath("$.idChambre").value(1L))
                .andExpect(jsonPath("$.numeroChambre").value(101))
                .andExpect(jsonPath("$.typeC").value("SIMPLE")); // Check the enum value

        verify(chambreService, times(1)).addOrUpdate(any(Chambre.class));
    }

    @Test
    public void testDelete() throws Exception {
        // Test the DELETE method with valid JSON for a Chambre object
        mockMvc.perform(delete("/chambre/delete")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"idChambre\":1, \"numeroChambre\":101, \"typeC\":\"SIMPLE\"}"))  // Use "SIMPLE"
                .andExpect(status().isOk());  // Expect 200 OK

        verify(chambreService, times(1)).delete(any(Chambre.class));
    }

    @Test
    public void testFindById() throws Exception {
        // Mock the service method
        when(chambreService.findById(1L)).thenReturn(chambreMock);

        // Test the GET method with correct ID parameter
        mockMvc.perform(get("/chambre/findById?id=1"))
                .andExpect(status().isOk())  // Expect 200 OK
                .andExpect(jsonPath("$.idChambre").value(1L))
                .andExpect(jsonPath("$.numeroChambre").value(101))
                .andExpect(jsonPath("$.typeC").value("SIMPLE")); // Ensure the enum is correct

        verify(chambreService, times(1)).findById(1L);
    }

    @Test
    public void testGetChambresNonReserveParNomFoyerEtTypeChambre() throws Exception {
        // Mock the service method
        List<Chambre> chambres = Arrays.asList(chambreMock);
        when(chambreService.getChambresNonReserveParNomFoyerEtTypeChambre("FoyerA", TypeChambre.SIMPLE)).thenReturn(chambres);

        // Test the GET method with correct parameters
        mockMvc.perform(get("/chambre/getChambresNonReserveParNomFoyerEtTypeChambre?nomFoyer=FoyerA&type=SIMPLE"))  // Use "SIMPLE"
                .andExpect(status().isOk())  // Expect 200 OK
                .andExpect(jsonPath("$[0].idChambre").value(1L));

        verify(chambreService, times(1)).getChambresNonReserveParNomFoyerEtTypeChambre("FoyerA", TypeChambre.SIMPLE);
    }

    @Test
    public void testNbChambreParTypeEtBloc() throws Exception {
        // Mock the service method
        when(chambreService.nbChambreParTypeEtBloc(TypeChambre.SIMPLE, 1L)).thenReturn(5L);

        // Test the GET method with correct parameters
        mockMvc.perform(get("/chambre/nbChambreParTypeEtBloc?type=SIMPLE&idBloc=1"))  // Use "SIMPLE"
                .andExpect(status().isOk())  // Expect 200 OK
                .andExpect(jsonPath("$").value(5));

        verify(chambreService, times(1)).nbChambreParTypeEtBloc(TypeChambre.SIMPLE, 1L);
    }


}
