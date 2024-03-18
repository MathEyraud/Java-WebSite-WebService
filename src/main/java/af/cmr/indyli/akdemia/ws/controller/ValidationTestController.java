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

import af.cmr.indyli.akdemia.business.dto.basic.ValidationTestBasicDTO;
import af.cmr.indyli.akdemia.business.dto.full.ValidationTestFullDTO;
import af.cmr.indyli.akdemia.business.exception.AkdemiaBusinessException;
import af.cmr.indyli.akdemia.business.service.IValidationTestService;
import af.cmr.indyli.akdemia.business.utils.ConstsValues;
import af.cmr.indyli.akdemia.ws.utils.AkdemiaUrlBase;
import jakarta.annotation.Resource;

/**
 * RESTful controller to manage validation test-related operations.
 */
@RestController
@CrossOrigin(origins = AkdemiaUrlBase.url, maxAge = AkdemiaUrlBase.maxAge)
@RequestMapping("/validationtests")
public class ValidationTestController {

	@Resource(name = ConstsValues.ServiceKeys.VALIDATION_TEST_SERVICE_KEY)
	private IValidationTestService validationTestService;

	/**
	 * Retrieve a list of all validation tests.
	 *
	 * @return ResponseEntity containing the list of validation tests.
	 */
	@GetMapping
	public ResponseEntity<List<ValidationTestBasicDTO>> getAll() {
		return ResponseEntity.ok(ResponseEntity.ok(validationTestService.findAll()).getBody());
	}

	/**
	 * Retrieve information about a validation test by its identifier.
	 *
	 * @param id The identifier of the validation test.
	 * @return ResponseEntity containing information about the validation test.
	 * @throws AkdemiaBusinessException If a business exception occurs.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<ValidationTestBasicDTO> getOne(@PathVariable int id) throws AkdemiaBusinessException {
		return ResponseEntity.ok(validationTestService.findById(id));
	}

	/**
	 * Create a new validation test.
	 *
	 * @param dto Information about the validation test to create.
	 * @return ResponseEntity containing information about the newly created
	 *         validation test.
	 * @throws AkdemiaBusinessException If a business exception occurs.
	 */
	@PostMapping
	public ResponseEntity<ValidationTestFullDTO> create(@RequestBody ValidationTestFullDTO dto)
			throws AkdemiaBusinessException {
		return ResponseEntity.ok(validationTestService.create(dto));
	}

	/**
	 * Update information about a validation test.
	 *
	 * @param id  The identifier of the validation test to update.
	 * @param dto Information about the updated validation test.
	 * @return ResponseEntity containing information about the updated validation
	 *         test.
	 * @throws AccessDeniedException    If access is denied.
	 * @throws AkdemiaBusinessException If a business exception occurs.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<ValidationTestFullDTO> update(@PathVariable int id, @RequestBody ValidationTestFullDTO dto)
			throws AccessDeniedException, AkdemiaBusinessException {
		return ResponseEntity.ok(validationTestService.update(dto));
	}

	/**
	 * Delete a validation test by its identifier.
	 *
	 * @param id The identifier of the validation test to delete.
	 * @return ResponseEntity indicating the success of the deletion.
	 * @throws AkdemiaBusinessException If a business exception occurs.
	 * @throws AccessDeniedException    If access is denied.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) throws AkdemiaBusinessException, AccessDeniedException {
		validationTestService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
