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

import af.cmr.indyli.akdemia.business.dto.basic.ParticularBasicDTO;
import af.cmr.indyli.akdemia.business.dto.full.ParticularFullDTO;
import af.cmr.indyli.akdemia.business.exception.AkdemiaBusinessException;
import af.cmr.indyli.akdemia.business.service.IParticularService;
import af.cmr.indyli.akdemia.business.utils.ConstsValues;
import af.cmr.indyli.akdemia.ws.utils.AkdemiaUrlBase;
import jakarta.annotation.Resource;

/**
 * RESTful controller to manage operations related to particulars.
 */
@RestController
@CrossOrigin(origins = AkdemiaUrlBase.url, maxAge = AkdemiaUrlBase.maxAge)
@RequestMapping("/particulars")
public class ParticularController {

	@Resource(name = ConstsValues.ServiceKeys.PARTICULAR_SERVICE_KEY)
	private IParticularService particularService;

	/**
	 * Retrieve the list of all particulars.
	 *
	 * @return ResponseEntity containing the list of particulars.
	 */
	@GetMapping
	public ResponseEntity<List<ParticularBasicDTO>> getAll() {
		return ResponseEntity.ok(particularService.findAll());
	}

	/**
	 * Retrieve information about a particular by its identifier.
	 *
	 * @param id The identifier of the particular.
	 * @return ResponseEntity containing information about the particular.
	 * @throws AkdemiaBusinessException If a business exception occurs.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<ParticularFullDTO> getOne(@PathVariable int id) throws AkdemiaBusinessException {
		return ResponseEntity.ok(particularService.findById(id));
	}

	/**
	 * Create a new particular.
	 *
	 * @param dto Information about the particular to create.
	 * @return ResponseEntity containing information about the newly created
	 *         particular.
	 * @throws AkdemiaBusinessException If a business exception occurs.
	 */
	@PostMapping
	public ResponseEntity<ParticularFullDTO> create(@RequestBody ParticularFullDTO dto)
			throws AkdemiaBusinessException {
		return ResponseEntity.ok(particularService.create(dto));
	}

	/**
	 * Update information about a particular.
	 *
	 * @param id  The identifier of the particular to update.
	 * @param dto The new information about the particular.
	 * @return ResponseEntity containing the updated information of the particular.
	 * @throws AccessDeniedException    If access is denied.
	 * @throws AkdemiaBusinessException If a business exception occurs.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<ParticularFullDTO> update(@PathVariable int id, @RequestBody ParticularFullDTO dto)
			throws AccessDeniedException, AkdemiaBusinessException {
		return ResponseEntity.ok(particularService.update(dto));
	}

	/**
	 * Delete a particular by its identifier.
	 *
	 * @param id The identifier of the particular to delete.
	 * @return ResponseEntity with an empty body indicating the particular has been
	 *         successfully deleted.
	 * @throws AkdemiaBusinessException If a business exception occurs.
	 * @throws AccessDeniedException    If access is denied.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) throws AkdemiaBusinessException, AccessDeniedException {
		particularService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
