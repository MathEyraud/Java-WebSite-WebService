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

import af.cmr.indyli.akdemia.business.dto.basic.TrainerBasicDTO;
import af.cmr.indyli.akdemia.business.dto.full.TrainerFullDTO;
import af.cmr.indyli.akdemia.business.exception.AkdemiaBusinessException;
import af.cmr.indyli.akdemia.business.service.ITrainerService;
import af.cmr.indyli.akdemia.business.utils.ConstsValues;
import af.cmr.indyli.akdemia.ws.utils.AkdemiaUrlBase;
import jakarta.annotation.Resource;

@RestController
@CrossOrigin(origins = AkdemiaUrlBase.url, maxAge = AkdemiaUrlBase.maxAge)
@RequestMapping("/trainers")
public class TrainerController {

	@Resource(name = ConstsValues.ServiceKeys.TRAINER_SERVICE_KEY)
    private ITrainerService trainerService;

	/**
	 * Retrieve a list of all trainer.
	 *
	 * @return ResponseEntity containing the list of trainers.
	 */
    @GetMapping
    public ResponseEntity<List<TrainerBasicDTO>> getAll() {
        return ResponseEntity.ok(ResponseEntity.ok(trainerService.findAll()).getBody());
    }
    
    /**
	 * Retrieve information about a trainer by its identifier.
	 *
	 * @param id The identifier of the trainer.
	 * @return ResponseEntity containing information about the trainer.
	 * @throws AkdemiaBusinessException If a business exception occurs.
	 */
    @GetMapping("/{id}")
    public ResponseEntity<TrainerFullDTO> getOne(@PathVariable("id") int id) throws AkdemiaBusinessException {
        return ResponseEntity.ok(trainerService.findById(id));
    }
    
    /**
	 * Create a new trainer.
	 *
	 * @param dto Information about the trainer to create.
	 * @return ResponseEntity containing information about the newly created
	 *         trainer.
	 * @throws AkdemiaBusinessException If a business exception occurs.
	 */
    @PostMapping
    public ResponseEntity<TrainerFullDTO> create(@RequestBody TrainerFullDTO trainerDTO) throws AkdemiaBusinessException {
    	return ResponseEntity.ok(trainerService.create(trainerDTO));
    }

    /**
	 * Update information about a trainer.
	 *
	 * @param id  The identifier of the trainer to update.
	 * @param dto Information about the updated trainer.
	 * @return ResponseEntity containing information about the updated trainer.
	 * @throws AccessDeniedException    If access is denied.
	 * @throws AkdemiaBusinessException If a business exception occurs.
	 */
    @PutMapping("/{id}")
    public ResponseEntity<TrainerFullDTO> update(@PathVariable("id") int id, @RequestBody TrainerFullDTO trainerDTO) 
    		throws AccessDeniedException, AkdemiaBusinessException {
    	return ResponseEntity.ok(trainerService.update(trainerDTO));
    }

    /**
	 * Delete a trainer by its identifier.
	 *
	 * @param id The identifier of the trainer to delete.
	 * @return ResponseEntity indicating the success of the deletion.
	 * @throws AkdemiaBusinessException If a business exception occurs.
	 * @throws AccessDeniedException    If access is denied.
	 */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) throws AkdemiaBusinessException, AccessDeniedException {
        trainerService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
