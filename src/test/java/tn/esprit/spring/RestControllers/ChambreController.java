package tn.esprit.spring.RestControllers;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.DAO.Entities.Chambre;
import tn.esprit.spring.Services.Chambre.ChambreService;


import java.util.List;

@RestController
@RequestMapping("/chambres")
public class ChambreController {
    private final ChambreService chambreService;

    public ChambreController(ChambreService chambreService) {
        this.chambreService = chambreService;
    }

    @GetMapping
    public List<Chambre> getAllChambres() {
        return chambreService.findAll();
    }

    @PostMapping
    public Chambre addChambre(@RequestBody Chambre chambre) {
        return chambreService.addOrUpdate(chambre);
    }
}
