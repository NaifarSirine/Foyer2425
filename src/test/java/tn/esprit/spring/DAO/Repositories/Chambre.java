package tn.esprit.spring.DAO.Repositories;



import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import tn.esprit.spring.DAO.Entities.Chambre;
import tn.esprit.spring.DAO.Entities.TypeChambre;


import java.util.Optional;

@DataJpaTest
class ChambreRepositoryTest {

    @Autowired
    private tn.esprit.spring.DAO.Repositories.ChambreRepository chambreRepository;

    @Test
    void testSaveAndFindChambre() {
        // Créer une chambre
        Chambre chambre = Chambre.builder()
                .numeroChambre(202L)
                .typeC(TypeChambre.SIMPLE)
                .build();

        // Sauvegarder la chambre
        Chambre savedChambre = chambreRepository.save(chambre);
        Assertions.assertNotNull(savedChambre.getIdChambre());

        // Rechercher la chambre par ID
        Optional<Chambre> fetchedChambre = chambreRepository.findById(savedChambre.getIdChambre());
        Assertions.assertTrue(fetchedChambre.isPresent());
        Assertions.assertEquals(202L, fetchedChambre.get().getNumeroChambre());
    }

    @Test
    void testFindByNumeroChambre() {
        // Ajouter une chambre
        Chambre chambre = Chambre.builder()
                .numeroChambre(303L)
                .typeC(TypeChambre.TRIPLE)
                .build();
        chambreRepository.save(chambre);

        // Rechercher par numéro
        Chambre fetchedChambre = chambreRepository.findByNumeroChambre(303L);
        Assertions.assertNotNull(fetchedChambre);
        Assertions.assertEquals(303L, fetchedChambre.getNumeroChambre());
    }
}
