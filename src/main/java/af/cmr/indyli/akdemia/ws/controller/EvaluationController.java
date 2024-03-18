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

import af.cmr.indyli.akdemia.business.dto.basic.EvaluationBasicDTO;
import af.cmr.indyli.akdemia.business.dto.full.EvaluationFullDTO;
import af.cmr.indyli.akdemia.business.exception.AkdemiaBusinessException;
import af.cmr.indyli.akdemia.business.service.IEvaluationService;
import af.cmr.indyli.akdemia.business.utils.ConstsValues;
import af.cmr.indyli.akdemia.ws.utils.AkdemiaUrlBase;
import jakarta.annotation.Resource;

/**
 * RESTful controller to manage operations related to evaluations.
 */
@RestController
@CrossOrigin(origins = AkdemiaUrlBase.url, maxAge = AkdemiaUrlBase.maxAge)
@RequestMapping("/evaluations")
public class EvaluationController {

	@Resource(name = ConstsValues.ServiceKeys.EVALUATION_SERVICE_KEY)
	private IEvaluationService evaluationService;

	/**
	 * Retrieve the list of all evaluations.
	 *
	 * @return ResponseEntity containing the list of evaluations.
	 */
	@GetMapping
	public ResponseEntity<List<EvaluationBasicDTO>> getAll() {
		return ResponseEntity.ok(evaluationService.findAll());
	}

	/**
	 * Retrieve information about an evaluation by its identifier.
	 *
	 * @param id The identifier of the evaluation.
	 * @return ResponseEntity containing information about the evaluation.
	 * @throws AkdemiaBusinessException If a business exception occurs.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<EvaluationFullDTO> getOne(@PathVariable int id) throws AkdemiaBusinessException {
		return ResponseEntity.ok(evaluationService.findById(id));
	}

	/**
	 * Create a new evaluation.
	 *
	 * @param dto Information about the evaluation to create.
	 * @return ResponseEntity containing information about the newly created
	 *         evaluation.
	 * @throws AkdemiaBusinessException If a business exception occurs.
	 */
	@PostMapping
	public ResponseEntity<EvaluationFullDTO> create(@RequestBody EvaluationFullDTO dto)
			throws AkdemiaBusinessException {
		return ResponseEntity.ok(evaluationService.create(dto));
	}

	/**
	 * Update information about an evaluation.
	 *
	 * @param id  The identifier of the evaluation to update.
	 * @param dto The new information about the evaluation.
	 * @return ResponseEntity containing the updated information of the evaluation.
	 * @throws AccessDeniedException    If access is denied.
	 * @throws AkdemiaBusinessException If a business exception occurs.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<EvaluationFullDTO> update(@PathVariable int id, @RequestBody EvaluationFullDTO dto)
			throws AccessDeniedException, AkdemiaBusinessException {
		return ResponseEntity.ok(evaluationService.update(dto));
	}

	/**
	 * Delete an evaluation by its identifier.
	 *
	 * @param id The identifier of the evaluation to delete.
	 * @return ResponseEntity with an empty body indicating the evaluation has been
	 *         successfully deleted.
	 * @throws AkdemiaBusinessException If a business exception occurs.
	 * @throws AccessDeniedException    If access is denied.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) throws AkdemiaBusinessException, AccessDeniedException {
		evaluationService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
