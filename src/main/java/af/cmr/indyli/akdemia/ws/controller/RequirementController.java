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

import af.cmr.indyli.akdemia.business.dto.basic.RequirementBasicDTO;
import af.cmr.indyli.akdemia.business.dto.full.RequirementFullDTO;
import af.cmr.indyli.akdemia.business.exception.AkdemiaBusinessException;
import af.cmr.indyli.akdemia.business.service.IRequirementService;
import af.cmr.indyli.akdemia.business.utils.ConstsValues;
import af.cmr.indyli.akdemia.ws.utils.AkdemiaUrlBase;
import jakarta.annotation.Resource;

/**
 * RESTful controller to manage operations related to project requirements.
 */
@RestController
@CrossOrigin(origins = AkdemiaUrlBase.url, maxAge = AkdemiaUrlBase.maxAge)
@RequestMapping("/requirements")
public class RequirementController {

	@Resource(name = ConstsValues.ServiceKeys.REQUIREMENT_SERVICE_KEY)
	private IRequirementService requirementService;

	/**
	 * Retrieve the list of all project requirements.
	 *
	 * @return ResponseEntity containing the list of project requirements.
	 */
	@GetMapping
	public ResponseEntity<List<RequirementBasicDTO>> getAll() {
		return ResponseEntity.ok(requirementService.findAll());
	}

	/**
	 * Retrieve information about a project requirement by its identifier.
	 *
	 * @param id The identifier of the project requirement.
	 * @return ResponseEntity containing information about the project requirement.
	 * @throws AkdemiaBusinessException If a business exception occurs.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<RequirementBasicDTO> getOne(@PathVariable int id) throws AkdemiaBusinessException {
		return ResponseEntity.ok(requirementService.findById(id));
	}

	/**
	 * Create a new project requirement.
	 *
	 * @param dto Information about the project requirement to create.
	 * @return ResponseEntity containing information about the newly created project
	 *         requirement.
	 * @throws AkdemiaBusinessException If a business exception occurs.
	 */
	@PostMapping
	public ResponseEntity<RequirementFullDTO> create(@RequestBody RequirementFullDTO dto)
			throws AkdemiaBusinessException {
		return ResponseEntity.ok(requirementService.create(dto));
	}

	/**
	 * Update information about a project requirement.
	 *
	 * @param id  The identifier of the project requirement to update.
	 * @param dto The new information about the project requirement.
	 * @return ResponseEntity containing the updated information of the project
	 *         requirement.
	 * @throws AccessDeniedException    If access is denied.
	 * @throws AkdemiaBusinessException If a business exception occurs.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<RequirementFullDTO> update(@PathVariable int id, @RequestBody RequirementFullDTO dto)
			throws AccessDeniedException, AkdemiaBusinessException {
		return ResponseEntity.ok(requirementService.update(dto));
	}

	/**
	 * Delete a project requirement by its identifier.
	 *
	 * @param id The identifier of the project requirement to delete.
	 * @return ResponseEntity with an empty body indicating the project requirement
	 *         has been successfully deleted.
	 * @throws AkdemiaBusinessException If a business exception occurs.
	 * @throws AccessDeniedException    If access is denied.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) throws AkdemiaBusinessException, AccessDeniedException {
		requirementService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
