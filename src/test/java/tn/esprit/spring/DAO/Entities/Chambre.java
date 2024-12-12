package tn.esprit.spring.DAO.Entities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tn.esprit.spring.DAO.Entities.Bloc;
import tn.esprit.spring.DAO.Entities.Chambre;
import tn.esprit.spring.DAO.Entities.TypeChambre;


class ChambreEntityTest {

    @Test
    void testChambreProperties() {
        // Créer une instance de Chambre
        Chambre chambre = new Chambre();
        chambre.setIdChambre(1L);
        chambre.setNumeroChambre(101L);
        chambre.setTypeC(TypeChambre.DOUBLE);

        // Associer un Bloc
        Bloc bloc = new Bloc();
        bloc.setIdBloc(1L);
        bloc.setNomBloc("Bloc A");
        chambre.setBloc(bloc);

        // Valider les propriétés
        Assertions.assertEquals(1L, chambre.getIdChambre());
        Assertions.assertEquals(101L, chambre.getNumeroChambre());
        Assertions.assertEquals(TypeChambre.DOUBLE, chambre.getTypeC());
        Assertions.assertEquals(bloc, chambre.getBloc());
    }
}
