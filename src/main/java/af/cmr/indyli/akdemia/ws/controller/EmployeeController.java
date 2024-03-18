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

import af.cmr.indyli.akdemia.business.dto.full.EmployeeFullDTO;
import af.cmr.indyli.akdemia.business.exception.AkdemiaBusinessException;
import af.cmr.indyli.akdemia.business.service.IEmployeeService;
import af.cmr.indyli.akdemia.business.utils.ConstsValues;
import af.cmr.indyli.akdemia.ws.utils.AkdemiaUrlBase;
import jakarta.annotation.Resource;

/**
 * RESTful controller to manage operations related to employees.
 */
@RestController
@CrossOrigin(origins = AkdemiaUrlBase.url, maxAge = AkdemiaUrlBase.maxAge)
@RequestMapping("/employees")
public class EmployeeController {

	@Resource(name = ConstsValues.ServiceKeys.EMPLOYEE_SERVICE_KEY)
	private IEmployeeService employeeService;

	/**
	 * Retrieve the list of all employees.
	 *
	 * @return ResponseEntity containing the list of employees.
	 */
	@GetMapping
	public ResponseEntity<List<EmployeeFullDTO>> getAll() {
		return ResponseEntity.ok(employeeService.findAllFull());
	}

	/**
	 * Retrieve information about an employee by their identifier.
	 *
	 * @param id The identifier of the employee.
	 * @return ResponseEntity containing information about the employee.
	 * @throws AkdemiaBusinessException If a business exception occurs.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeFullDTO> getOne(@PathVariable int id) throws AkdemiaBusinessException {
		return ResponseEntity.ok(employeeService.findById(id));
	}

	/**
	 * Create a new employee.
	 *
	 * @param dto Information about the employee to create.
	 * @return ResponseEntity containing information about the newly created
	 *         employee.
	 * @throws AkdemiaBusinessException If a business exception occurs.
	 */
	@PostMapping
	public ResponseEntity<EmployeeFullDTO> create(@RequestBody EmployeeFullDTO dto) throws AkdemiaBusinessException {
		return ResponseEntity.ok(employeeService.create(dto));
	}

	/**
	 * Update information about an employee.
	 *
	 * @param id  The identifier of the employee to update.
	 * @param dto The new information about the employee.
	 * @return ResponseEntity containing the updated information of the employee.
	 * @throws AccessDeniedException    If access is denied.
	 * @throws AkdemiaBusinessException If a business exception occurs.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<EmployeeFullDTO> update(@PathVariable int id, @RequestBody EmployeeFullDTO dto)
			throws AccessDeniedException, AkdemiaBusinessException {
		return ResponseEntity.ok(employeeService.update(dto));
	}

	/**
	 * Delete an employee by their identifier.
	 *
	 * @param id The identifier of the employee to delete.
	 * @return ResponseEntity with an empty body indicating the employee has been
	 *         successfully deleted.
	 * @throws AkdemiaBusinessException If a business exception occurs.
	 * @throws AccessDeniedException    If access is denied.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) throws AkdemiaBusinessException, AccessDeniedException {
		employeeService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
