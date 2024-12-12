package tn.esprit.spring.Sercices;



import org.springframework.stereotype.Service;
import tn.esprit.spring.DAO.Entities.Chambre;
import tn.esprit.spring.DAO.Repositories.ChambreRepository;


import java.util.List;

@Service
public class ChambreService {
    private final ChambreRepository chambreRepository;

    public ChambreService(ChambreRepository chambreRepository) {
        this.chambreRepository = chambreRepository;
    }

    public List<Chambre> findAll() {
        return chambreRepository.findAll();
    }

    public Chambre addOrUpdate(Chambre chambre) {
        return chambreRepository.save(chambre);
    }
}
