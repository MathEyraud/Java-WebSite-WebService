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

import af.cmr.indyli.akdemia.business.dto.basic.TestBasicDTO;
import af.cmr.indyli.akdemia.business.dto.full.TestFullDTO;
import af.cmr.indyli.akdemia.business.exception.AkdemiaBusinessException;
import af.cmr.indyli.akdemia.business.service.ITestService;
import af.cmr.indyli.akdemia.business.utils.ConstsValues;
import af.cmr.indyli.akdemia.ws.utils.AkdemiaUrlBase;
import jakarta.annotation.Resource;

/**
 * RESTful controller to manage operations related to tests.
 */
@RestController
@CrossOrigin(origins = AkdemiaUrlBase.url, maxAge = AkdemiaUrlBase.maxAge)
@RequestMapping("/tests")
public class TestController {

	@Resource(name = ConstsValues.ServiceKeys.TEST_SERVICE_KEY)
	private ITestService testService;

	/**
	 * Retrieve the list of all tests.
	 *
	 * @return ResponseEntity containing the list of tests.
	 */
	@GetMapping
	public ResponseEntity<List<TestBasicDTO>> getAll() {
		return ResponseEntity.ok(testService.findAll());
	}

	/**
	 * Retrieve information about a test by its identifier.
	 *
	 * @param id The identifier of the test.
	 * @return ResponseEntity containing information about the test.
	 * @throws AkdemiaBusinessException If a business exception occurs.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<TestBasicDTO> getOne(@PathVariable int id) throws AkdemiaBusinessException {
		return ResponseEntity.ok(testService.findById(id));
	}

	/**
	 * Create a new test.
	 *
	 * @param dto Information about the test to create.
	 * @return ResponseEntity containing information about the newly created test.
	 * @throws AkdemiaBusinessException If a business exception occurs.
	 */
	@PostMapping
	public ResponseEntity<TestFullDTO> create(@RequestBody TestFullDTO dto) throws AkdemiaBusinessException {
		return ResponseEntity.ok(testService.create(dto));
	}

	/**
	 * Update information about a test.
	 *
	 * @param id  The identifier of the test to update.
	 * @param dto The new information about the test.
	 * @return ResponseEntity containing the updated information of the test.
	 * @throws AkdemiaBusinessException If a business exception occurs.
	 * @throws AccessDeniedException    If access is denied.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<TestFullDTO> update(@PathVariable int id, @RequestBody TestFullDTO dto)
			throws AccessDeniedException, AkdemiaBusinessException {
		return ResponseEntity.ok(testService.update(dto));
	}

	/**
	 * Delete a test by its identifier.
	 *
	 * @param id The identifier of the test to delete.
	 * @return ResponseEntity with an empty body indicating the test has been
	 *         successfully deleted.
	 * @throws AkdemiaBusinessException If a business exception occurs.
	 * @throws AccessDeniedException    If access is denied.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) throws AkdemiaBusinessException, AccessDeniedException {
		testService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
