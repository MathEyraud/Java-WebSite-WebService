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

import af.cmr.indyli.akdemia.business.dto.basic.TopicBasicDTO;
import af.cmr.indyli.akdemia.business.dto.full.TopicFullDTO;
import af.cmr.indyli.akdemia.business.exception.AkdemiaBusinessException;
import af.cmr.indyli.akdemia.business.service.ITopicService;
import af.cmr.indyli.akdemia.business.utils.ConstsValues;
import af.cmr.indyli.akdemia.ws.utils.AkdemiaUrlBase;
import jakarta.annotation.Resource;

/**
 * RESTful controller to manage operations related to topics.
 */
@RestController
@CrossOrigin(origins = AkdemiaUrlBase.url, maxAge = AkdemiaUrlBase.maxAge)
@RequestMapping("/themes")
public class TopicController {

	@Resource(name = ConstsValues.ServiceKeys.TOPIC_SERVICE_KEY)
	private ITopicService topicService;

	/**
	 * Retrieve the list of all topics.
	 *
	 * @return ResponseEntity containing the list of topics.
	 */
	@GetMapping
	public ResponseEntity<List<TopicBasicDTO>> getAll() {
		return ResponseEntity.ok(topicService.findAll());
	}

	/**
	 * Retrieve information about a topic by its identifier.
	 *
	 * @param id The identifier of the topic.
	 * @return ResponseEntity containing information about the topic.
	 * @throws AkdemiaBusinessException If a business exception occurs.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<TopicFullDTO> getOne(@PathVariable int id) throws AkdemiaBusinessException {
		return ResponseEntity.ok(topicService.findById(id));
	}

	/**
	 * Create a new topic.
	 *
	 * @param dto Information about the topic to create.
	 * @return ResponseEntity containing information about the newly created topic.
	 * @throws AkdemiaBusinessException If a business exception occurs.
	 */
	@PostMapping
	public ResponseEntity<TopicFullDTO> create(@RequestBody TopicFullDTO dto) throws AkdemiaBusinessException {
		return ResponseEntity.ok(topicService.create(dto));
	}

	/**
	 * Update information about a topic.
	 *
	 * @param id  The identifier of the topic to update.
	 * @param dto The new information about the topic.
	 * @return ResponseEntity containing the updated information of the topic.
	 * @throws AkdemiaBusinessException If a business exception occurs.
	 * @throws AccessDeniedException    If access is denied.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<TopicFullDTO> update(@PathVariable int id, @RequestBody TopicFullDTO dto)
			throws AkdemiaBusinessException, AccessDeniedException {
		return ResponseEntity.ok(topicService.update(dto));
	}

	/**
	 * Delete a topic by its identifier.
	 *
	 * @param id The identifier of the topic to delete.
	 * @return ResponseEntity with an empty body indicating the topic has been
	 *         successfully deleted.
	 * @throws AkdemiaBusinessException If a business exception occurs.
	 * @throws AccessDeniedException    If access is denied.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) throws AkdemiaBusinessException, AccessDeniedException {
		topicService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
