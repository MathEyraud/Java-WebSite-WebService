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

import af.cmr.indyli.akdemia.business.dto.basic.SubTopicBasicDTO;
import af.cmr.indyli.akdemia.business.dto.full.SubTopicFullDTO;
import af.cmr.indyli.akdemia.business.exception.AkdemiaBusinessException;
import af.cmr.indyli.akdemia.business.service.ISubTopicService;
import af.cmr.indyli.akdemia.business.utils.ConstsValues;
import af.cmr.indyli.akdemia.ws.utils.AkdemiaUrlBase;
import jakarta.annotation.Resource;

/**
 * RESTful controller to manage operations related to subtopics.
 */
@RestController
@CrossOrigin(origins = AkdemiaUrlBase.url, maxAge = AkdemiaUrlBase.maxAge)
@RequestMapping("/subthemes")
public class SubTopicController {

	@Resource(name = ConstsValues.ServiceKeys.SUB_TOPIC_SERVICE_KEY)
	private ISubTopicService subThemeService;

	/**
	 * Retrieve the list of all subtopics.
	 *
	 * @return ResponseEntity containing the list of subtopics.
	 */
	@GetMapping
	public ResponseEntity<List<SubTopicBasicDTO>> getAll() {
		return ResponseEntity.ok(subThemeService.findAll());
	}

	/**
	 * Retrieve information about a subtopic by its identifier.
	 *
	 * @param id The identifier of the subtopic.
	 * @return ResponseEntity containing information about the subtopic.
	 * @throws AkdemiaBusinessException If a business exception occurs.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<SubTopicFullDTO> getOne(@PathVariable Integer id) throws AkdemiaBusinessException {
		return ResponseEntity.ok(subThemeService.findById(id));
	}

	/**
	 * Create a new subtopic.
	 *
	 * @param dto Information about the subtopic to create.
	 * @return ResponseEntity containing information about the newly created
	 *         subtopic.
	 * @throws AkdemiaBusinessException If a business exception occurs.
	 */
	@PostMapping
	public ResponseEntity<SubTopicFullDTO> create(@RequestBody SubTopicFullDTO dto) throws AkdemiaBusinessException {
		return ResponseEntity.ok(subThemeService.create(dto));
	}

	/**
	 * Update information about a subtopic.
	 *
	 * @param id  The identifier of the subtopic to update.
	 * @param dto The new information about the subtopic.
	 * @return ResponseEntity containing the updated information of the subtopic.
	 * @throws AkdemiaBusinessException If a business exception occurs.
	 * @throws AccessDeniedException    If access is denied.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<SubTopicFullDTO> update(@PathVariable Integer id, @RequestBody SubTopicFullDTO dto)
			throws AkdemiaBusinessException, AccessDeniedException {
		return ResponseEntity.ok(subThemeService.update(dto));
	}

	/**
	 * Delete a subtopic by its identifier.
	 *
	 * @param id The identifier of the subtopic to delete.
	 * @return ResponseEntity with an empty body indicating the subtopic has been
	 *         successfully deleted.
	 * @throws AkdemiaBusinessException If a business exception occurs.
	 * @throws AccessDeniedException    If access is denied.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) throws AkdemiaBusinessException, AccessDeniedException {
		subThemeService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
