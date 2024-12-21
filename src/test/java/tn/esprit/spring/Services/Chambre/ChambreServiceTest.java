package tn.esprit.spring.Services.Chambre;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.DAO.Entities.Bloc;
import tn.esprit.spring.DAO.Entities.Chambre;
import tn.esprit.spring.DAO.Entities.TypeChambre;
import tn.esprit.spring.DAO.Repositories.ChambreRepository;
import tn.esprit.spring.DAO.Repositories.BlocRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ChambreServiceTest {

    @Mock
    private ChambreRepository chambreRepository;

    @Mock
    private BlocRepository blocRepository;

    @InjectMocks
    private ChambreService chambreService;

    private Chambre chambre;
    private Bloc bloc;

    @BeforeEach
    public void setUp() {
        bloc = new Bloc();
        bloc.setIdBloc(1L);
        bloc.setNomBloc("Bloc1");

        chambre = new Chambre();
        chambre.setIdChambre(1L);
        chambre.setNumeroChambre(101);
        chambre.setTypeC(TypeChambre.SIMPLE);
        chambre.setBloc(bloc);  // Assign the mock Bloc to the Chambre
    }

    @Test
    public void testAddOrUpdate() {
        when(chambreRepository.save(chambre)).thenReturn(chambre);

        Chambre result = chambreService.addOrUpdate(chambre);

        assertNotNull(result);
        assertEquals(1L, result.getIdChambre());
        assertEquals(101, result.getNumeroChambre());
        assertEquals(TypeChambre.SIMPLE, result.getTypeC());
        verify(chambreRepository, times(1)).save(chambre);
    }

    @Test
    public void testFindAll() {
        when(chambreRepository.findAll()).thenReturn(Arrays.asList(chambre));

        List<Chambre> result = chambreService.findAll();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(chambreRepository, times(1)).findAll();
    }

    @Test
    public void testFindById() {
        when(chambreRepository.findById(1L)).thenReturn(Optional.of(chambre));

        Chambre result = chambreService.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getIdChambre());
        verify(chambreRepository, times(1)).findById(1L);
    }

    @Test
    public void testFindById_NotFound() {
        when(chambreRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> chambreService.findById(1L));
    }

    @Test
    public void testDeleteById() {
        doNothing().when(chambreRepository).deleteById(1L);

        chambreService.deleteById(1L);

        verify(chambreRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDelete() {
        doNothing().when(chambreRepository).delete(chambre);

        chambreService.delete(chambre);

        verify(chambreRepository, times(1)).delete(chambre);
    }

    @Test
    public void testGetChambresParNomBloc() {
        when(chambreRepository.findByBlocNomBloc("Bloc1")).thenReturn(Arrays.asList(chambre));

        List<Chambre> result = chambreService.getChambresParNomBloc("Bloc1");

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(chambreRepository, times(1)).findByBlocNomBloc("Bloc1");
    }

    @Test
    public void testNbChambreParTypeEtBloc() {
        when(chambreRepository.findAll()).thenReturn(Arrays.asList(chambre));

        long result = chambreService.nbChambreParTypeEtBloc(TypeChambre.SIMPLE, 1L);

        assertEquals(1, result);
        verify(chambreRepository, times(1)).findAll();
    }


}
