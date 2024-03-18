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

import af.cmr.indyli.akdemia.business.dto.basic.PlanningBasicDTO;
import af.cmr.indyli.akdemia.business.dto.full.PlanningFullDTO;
import af.cmr.indyli.akdemia.business.exception.AkdemiaBusinessException;
import af.cmr.indyli.akdemia.business.service.IPlanningService;
import af.cmr.indyli.akdemia.business.utils.ConstsValues;
import af.cmr.indyli.akdemia.ws.utils.AkdemiaUrlBase;
import jakarta.annotation.Resource;

/**
 * RESTful controller to manage operations related to plannings.
 */
@RestController
@CrossOrigin(origins = AkdemiaUrlBase.url, maxAge = AkdemiaUrlBase.maxAge)
@RequestMapping("/planifications")
public class PlanningController {

	@Resource(name = ConstsValues.ServiceKeys.PLANNING_SERVICE_KEY)
	private IPlanningService planificationService;

	/**
	 * Retrieve the list of all plannings.
	 *
	 * @return ResponseEntity containing the list of plannings.
	 */
	@GetMapping
	public ResponseEntity<List<PlanningBasicDTO>> getAll() {
		return ResponseEntity.ok(planificationService.findAll());
	}

	/**
	 * Retrieve information about a planning by its identifier.
	 *
	 * @param id The identifier of the planning.
	 * @return ResponseEntity containing information about the planning.
	 * @throws AkdemiaBusinessException If a business exception occurs.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<PlanningFullDTO> getOne(@PathVariable int id) throws AkdemiaBusinessException {
		return ResponseEntity.ok(planificationService.findById(id));
	}

	/**
	 * Create a new planning.
	 *
	 * @param dto Information about the planning to create.
	 * @return ResponseEntity containing information about the newly created
	 *         planning.
	 * @throws AkdemiaBusinessException If a business exception occurs.
	 */
	@PostMapping
	public ResponseEntity<PlanningFullDTO> create(@RequestBody PlanningFullDTO dto) throws AkdemiaBusinessException {
		return ResponseEntity.ok(planificationService.create(dto));
	}

	/**
	 * Update information about a planning.
	 *
	 * @param id  The identifier of the planning to update.
	 * @param dto The new information about the planning.
	 * @return ResponseEntity containing the updated information of the planning.
	 * @throws AccessDeniedException    If access is denied.
	 * @throws AkdemiaBusinessException If a business exception occurs.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<PlanningFullDTO> update(@PathVariable int id, @RequestBody PlanningFullDTO dto)
			throws AccessDeniedException, AkdemiaBusinessException {
		return ResponseEntity.ok(planificationService.update(dto));
	}

	/**
	 * Delete a planning by its identifier.
	 *
	 * @param id The identifier of the planning to delete.
	 * @return ResponseEntity with an empty body indicating the planning has been
	 *         successfully deleted.
	 * @throws AkdemiaBusinessException If a business exception occurs.
	 * @throws AccessDeniedException    If access is denied.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) throws AkdemiaBusinessException, AccessDeniedException {
		planificationService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
