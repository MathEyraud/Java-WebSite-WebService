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

import af.cmr.indyli.akdemia.business.dto.basic.ManagerBasicDTO;
import af.cmr.indyli.akdemia.business.dto.full.ManagerFullDTO;
import af.cmr.indyli.akdemia.business.exception.AkdemiaBusinessException;
import af.cmr.indyli.akdemia.business.service.IManagerService;
import af.cmr.indyli.akdemia.business.utils.ConstsValues;
import af.cmr.indyli.akdemia.ws.utils.AkdemiaUrlBase;
import jakarta.annotation.Resource;

/**
 * RESTful controller to manage operations related to managers.
 */
@RestController
@CrossOrigin(origins = AkdemiaUrlBase.url, maxAge = AkdemiaUrlBase.maxAge)
@RequestMapping("/managers")
public class ManagerController {

	@Resource(name = ConstsValues.ServiceKeys.MANAGER_SERVICE_KEY)
	private IManagerService managerService;

	/**
	 * Retrieve the list of all managers.
	 *
	 * @return ResponseEntity containing the list of managers.
	 */
	@GetMapping
	public ResponseEntity<List<ManagerBasicDTO>> getAll() {
		return ResponseEntity.ok(managerService.findAll());
	}

	/**
	 * Retrieve information about a manager by its identifier.
	 *
	 * @param id The identifier of the manager.
	 * @return ResponseEntity containing information about the manager.
	 * @throws AkdemiaBusinessException If a business exception occurs.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<ManagerBasicDTO> getOne(@PathVariable int id) throws AkdemiaBusinessException {
		return ResponseEntity.ok(managerService.findById(id));
	}

	/**
	 * Create a new manager.
	 *
	 * @param dto Information about the manager to create.
	 * @return ResponseEntity containing information about the newly created
	 *         manager.
	 * @throws AkdemiaBusinessException If a business exception occurs.
	 */
	@PostMapping
	public ResponseEntity<ManagerFullDTO> create(@RequestBody ManagerFullDTO dto) throws AkdemiaBusinessException {
		return ResponseEntity.ok(managerService.create(dto));
	}

	/**
	 * Update information about a manager.
	 *
	 * @param id  The identifier of the manager to update.
	 * @param dto The new information about the manager.
	 * @return ResponseEntity containing the updated information of the manager.
	 * @throws AccessDeniedException    If access is denied.
	 * @throws AkdemiaBusinessException If a business exception occurs.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<ManagerFullDTO> update(@PathVariable int id, @RequestBody ManagerFullDTO dto)
			throws AccessDeniedException, AkdemiaBusinessException {
		return ResponseEntity.ok(managerService.update(dto));
	}

	/**
	 * Delete a manager by its identifier.
	 *
	 * @param id The identifier of the manager to delete.
	 * @return ResponseEntity with an empty body indicating the manager has been
	 *         successfully deleted.
	 * @throws AkdemiaBusinessException If a business exception occurs.
	 * @throws AccessDeniedException    If access is denied.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) throws AkdemiaBusinessException, AccessDeniedException {
		managerService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
