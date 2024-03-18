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

import af.cmr.indyli.akdemia.business.dto.basic.ParticularSubscriptionBasicDTO;
import af.cmr.indyli.akdemia.business.dto.full.ParticularSubscriptionFullDTO;
import af.cmr.indyli.akdemia.business.exception.AkdemiaBusinessException;
import af.cmr.indyli.akdemia.business.service.IParticularSubscriptionService;
import af.cmr.indyli.akdemia.business.utils.ConstsValues;
import af.cmr.indyli.akdemia.ws.utils.AkdemiaUrlBase;
import jakarta.annotation.Resource;

@RestController
@CrossOrigin(origins = AkdemiaUrlBase.url, maxAge = AkdemiaUrlBase.maxAge)
@RequestMapping("/particularsubscriptions")
public class ParticularSubscriptionController {

	@Resource(name = ConstsValues.ServiceKeys.PARTICULAR_SUBSCRIPTION_SERVICE_KEY)
    private IParticularSubscriptionService particularSubscriptionService;

	/**
	 * Retrieve a list of all particularSubscription.
	 *
	 * @return ResponseEntity containing the list of particularSubscriptions.
	 */
    @GetMapping
    public ResponseEntity<List<ParticularSubscriptionBasicDTO>> getAll() {
        return ResponseEntity.ok(particularSubscriptionService.findAll());
    }
    
    /**
	 * Retrieve information about a particularSubscription by its identifier.
	 *
	 * @param id The identifier of the particularSubscription.
	 * @return ResponseEntity containing information about the particularSubscription.
	 * @throws AkdemiaBusinessException If a business exception occurs.
	 */
    @GetMapping("/{id}")
    public ResponseEntity<ParticularSubscriptionFullDTO> getOne(@PathVariable("id") int id) throws AkdemiaBusinessException {
        return ResponseEntity.ok(particularSubscriptionService.findById(id));
    }
    
    /**
	 * Create a new particularSubscription.
	 *
	 * @param dto Information about the particularSubscription to create.
	 * @return ResponseEntity containing information about the newly created
	 *         particularSubscription.
	 * @throws AkdemiaBusinessException If a business exception occurs.
	 */
    @PostMapping
    public ResponseEntity<ParticularSubscriptionFullDTO> create(@RequestBody ParticularSubscriptionFullDTO particularSubscriptionFullDTO) throws AkdemiaBusinessException {
    	return ResponseEntity.ok(particularSubscriptionService.create(particularSubscriptionFullDTO));
    }

    /**
	 * Update information about a particularSubscription.
	 *
	 * @param id  The identifier of the particularSubscription to update.
	 * @param dto Information about the updated particularSubscription.
	 * @return ResponseEntity containing information about the updated trainer.
	 * @throws AccessDeniedException    If access is denied.
	 * @throws AkdemiaBusinessException If a business exception occurs.
	 */
    @PutMapping("/{id}")
    public ResponseEntity<ParticularSubscriptionFullDTO> update(@PathVariable("id") int id, @RequestBody ParticularSubscriptionFullDTO particularSubscriptionDTO) 
    		throws AccessDeniedException, AkdemiaBusinessException {
    	return ResponseEntity.ok(particularSubscriptionService.update(particularSubscriptionDTO));
    }

    /**
	 * Delete a particularSubscription by its identifier.
	 *
	 * @param id The identifier of the particularSubscription to delete.
	 * @return ResponseEntity indicating the success of the deletion.
	 * @throws AkdemiaBusinessException If a business exception occurs.
	 * @throws AccessDeniedException    If access is denied.
	 */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) throws AkdemiaBusinessException, AccessDeniedException {
    	particularSubscriptionService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
