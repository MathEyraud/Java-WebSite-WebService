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

import af.cmr.indyli.akdemia.business.dto.basic.RoleBasicDTO;
import af.cmr.indyli.akdemia.business.dto.full.RoleFullDTO;
import af.cmr.indyli.akdemia.business.exception.AkdemiaBusinessException;
import af.cmr.indyli.akdemia.business.service.IRoleService;
import af.cmr.indyli.akdemia.business.utils.ConstsValues;
import af.cmr.indyli.akdemia.ws.utils.AkdemiaUrlBase;
import jakarta.annotation.Resource;

/**
 * RESTful controller to manage operations related to user roles.
 */
@RestController
@CrossOrigin(origins = AkdemiaUrlBase.url, maxAge = AkdemiaUrlBase.maxAge)
@RequestMapping("/roles")
public class RoleController {

	@Resource(name = ConstsValues.ServiceKeys.ROLE_SERVICE_KEY)
	private IRoleService roleService;

	/**
	 * Retrieve the list of all user roles.
	 *
	 * @return ResponseEntity containing the list of user roles.
	 */
	@GetMapping
	public ResponseEntity<List<RoleBasicDTO>> getAll() {
		return ResponseEntity.ok(roleService.findAll());
	}

	/**
	 * Retrieve information about a user role by its identifier.
	 *
	 * @param id The identifier of the user role.
	 * @return ResponseEntity containing information about the user role.
	 * @throws AkdemiaBusinessException If a business exception occurs.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<RoleBasicDTO> getOne(@PathVariable int id) throws AkdemiaBusinessException {
		return ResponseEntity.ok(roleService.findById(id));
	}

	/**
	 * Create a new user role.
	 *
	 * @param dto Information about the user role to create.
	 * @return ResponseEntity containing information about the newly created user
	 *         role.
	 * @throws AkdemiaBusinessException If a business exception occurs.
	 */
	@PostMapping
	public ResponseEntity<RoleFullDTO> create(@RequestBody RoleFullDTO dto) throws AkdemiaBusinessException {
		return ResponseEntity.ok(roleService.create(dto));
	}

	/**
	 * Update information about a user role.
	 *
	 * @param id  The identifier of the user role to update.
	 * @param dto The new information about the user role.
	 * @return ResponseEntity containing the updated information of the user role.
	 * @throws AccessDeniedException    If access is denied.
	 * @throws AkdemiaBusinessException If a business exception occurs.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<RoleFullDTO> update(@PathVariable int id, @RequestBody RoleFullDTO dto)
			throws AccessDeniedException, AkdemiaBusinessException {
		return ResponseEntity.ok(roleService.update(dto));
	}

	/**
	 * Delete a user role by its identifier.
	 *
	 * @param id The identifier of the user role to delete.
	 * @return ResponseEntity with an empty body indicating the user role has been
	 *         successfully deleted.
	 * @throws AkdemiaBusinessException If a business exception occurs.
	 * @throws AccessDeniedException    If access is denied.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) throws AkdemiaBusinessException, AccessDeniedException {
		roleService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
