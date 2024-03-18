package af.cmr.indyli.akdemia.ws.controller;

import java.nio.file.AccessDeniedException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import af.cmr.indyli.akdemia.business.dto.basic.TrainingBasicDTO;
import af.cmr.indyli.akdemia.business.dto.full.TrainingFullDTO;
import af.cmr.indyli.akdemia.business.exception.AkdemiaBusinessException;
import af.cmr.indyli.akdemia.business.service.ITrainingService;
import af.cmr.indyli.akdemia.business.utils.ConstsValues;
import af.cmr.indyli.akdemia.ws.utils.AkdemiaUrlBase;
import jakarta.annotation.Resource;

@RestController
@CrossOrigin(origins = AkdemiaUrlBase.url, maxAge = AkdemiaUrlBase.maxAge)
@RequestMapping("/trainings")
public class TrainingController {
	
	@Resource(name = ConstsValues.ServiceKeys.TRAINING_SERVICE_KEY)
    private ITrainingService trainingService;
	
	/**
	 * Récupère la liste de tous les formations.
	 *
	 * @return ResponseEntity contenant la liste des formations.
	 */
    @GetMapping
    public ResponseEntity<List<TrainingBasicDTO>> getAll() {
        return ResponseEntity.ok(ResponseEntity.ok(trainingService.findAll()).getBody());
    }
    
    /**
     * Récupère les informations sur une formation par son identifiant.
     *
     * @param id L'identifiant de la formation.
     * @return ResponseEntity contenant les informations sur la formation.
     * @throws AkdemiaBusinessException Si une exception métier se produit.
     */
    @GetMapping("/{id}")
    public ResponseEntity<TrainingFullDTO> getOne(@PathVariable("id") int id) throws AkdemiaBusinessException {
        return ResponseEntity.ok(trainingService.findById(id));
    }

    /**
     * Crée une nouvelle formation.
     *
     * @param dto Informations sur la formation à créer.
     * @return ResponseEntity contenant les informations sur la formation nouvellement créée.
     * @throws AkdemiaBusinessException Si une exception métier se produit.
     */
    @PostMapping
    public ResponseEntity<TrainingFullDTO> create(@RequestBody TrainingFullDTO trainingDTO) throws AkdemiaBusinessException {
        return ResponseEntity.ok(trainingService.create(trainingDTO));
    }

    /**
     * Met à jour les informations sur une formation.
     *
     * @param id  L'identifiant de la formation à mettre à jour.
     * @param dto Informations sur la formation mise à jour.
     * @return ResponseEntity contenant les informations sur la formation mise à jour.
     * @throws AccessDeniedException    Si l'accès est refusé.
     * @throws AkdemiaBusinessException Si une exception métier se produit.
     */
    @PutMapping("/{id}")
    public ResponseEntity<TrainingFullDTO> update(@PathVariable("id") int id, @RequestBody TrainingFullDTO trainingDTO) 
            throws AccessDeniedException, AkdemiaBusinessException {
        return ResponseEntity.ok(trainingService.update(trainingDTO));
    }

    /**
     * Supprime une formation par son identifiant.
     *
     * @param id L'identifiant de la formation à supprimer.
     * @return ResponseEntity indiquant le succès de la suppression.
     * @throws AkdemiaBusinessException Si une exception métier se produit.
     * @throws AccessDeniedException    Si l'accès est refusé.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) throws AkdemiaBusinessException, AccessDeniedException {
        trainingService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
