package tn.esprit.spring.Sercices;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.DAO.Entities.Chambre;
import tn.esprit.spring.DAO.Repositories.ChambreRepository;


import java.util.Arrays;
import java.util.List;

@SpringBootTest
class ChambreServiceTest {

    @Mock
    private ChambreRepository chambreRepository;

    @InjectMocks
    private tn.esprit.spring.Sercices.ChambreService chambreService;

    @Test
    void testFindAll() {
        // Simuler des données
        Chambre chambre1 = new Chambre();
        chambre1.setNumeroChambre(101L);
        Chambre chambre2 = new Chambre();
        chambre2.setNumeroChambre(102L);

        Mockito.when(chambreRepository.findAll()).thenReturn(Arrays.asList(chambre1, chambre2));

        // Appeler le service
        List<Chambre> chambres = chambreService.findAll();
        Assertions.assertEquals(2, chambres.size());
        Assertions.assertEquals(101L, chambres.get(0).getNumeroChambre());
    }

    @Test
    void testAddOrUpdate() {
        // Simuler une chambre
        Chambre chambre = new Chambre();
        chambre.setNumeroChambre(202L);

        Mockito.when(chambreRepository.save(chambre)).thenReturn(chambre);

        // Ajouter la chambre
        Chambre savedChambre = chambreService.addOrUpdate(chambre);
        Assertions.assertNotNull(savedChambre);
        Assertions.assertEquals(202L, savedChambre.getNumeroChambre());
    }
}
