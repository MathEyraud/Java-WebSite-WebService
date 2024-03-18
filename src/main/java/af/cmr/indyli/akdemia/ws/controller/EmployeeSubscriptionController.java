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

import af.cmr.indyli.akdemia.business.dto.basic.EmployeeSubscriptionBasicDTO;
import af.cmr.indyli.akdemia.business.dto.full.EmployeeSubscriptionFullDTO;
import af.cmr.indyli.akdemia.business.exception.AkdemiaBusinessException;
import af.cmr.indyli.akdemia.business.service.IEmployeeSubscriptionService;
import af.cmr.indyli.akdemia.business.utils.ConstsValues;
import af.cmr.indyli.akdemia.ws.utils.AkdemiaUrlBase;
import jakarta.annotation.Resource;

@RestController
@CrossOrigin(origins = AkdemiaUrlBase.url, maxAge = AkdemiaUrlBase.maxAge)
@RequestMapping("/employeesubscriptions")
public class EmployeeSubscriptionController {

	@Resource(name = ConstsValues.ServiceKeys.EMPLOYEE_SUBSCRIPTION_SERVICE_KEY)
	private IEmployeeSubscriptionService employeeSubscriptionService;
	

	/**
	 * Retrieve a list of all employeeSubscription.
	 *
	 * @return ResponseEntity containing the list of employeeSubscriptions.
	 */
    @GetMapping
    public ResponseEntity<List<EmployeeSubscriptionBasicDTO>> getAll() {
        return ResponseEntity.ok(ResponseEntity.ok(employeeSubscriptionService.findAll()).getBody());
    }
    
    /**
	 * Retrieve information about a employeeSubscription by its identifier.
	 *
	 * @param id The identifier of the employeeSubscription.
	 * @return ResponseEntity containing information about the employeeSubscription.
	 * @throws AkdemiaBusinessException If a business exception occurs.
	 */
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeSubscriptionFullDTO> getOne(@PathVariable("id") int id) throws AkdemiaBusinessException {
        return ResponseEntity.ok(employeeSubscriptionService.findById(id));
    }
    
    /**
	 * Create a new employeeSubscription.
	 *
	 * @param dto Information about the employeeSubscription to create.
	 * @return ResponseEntity containing information about the newly created
	 *         employeeSubscription.
	 * @throws AkdemiaBusinessException If a business exception occurs.
	 */
    @PostMapping
    public ResponseEntity<EmployeeSubscriptionFullDTO> create(@RequestBody EmployeeSubscriptionFullDTO EmployeeSubscriptionDTO) throws AkdemiaBusinessException {
    	return ResponseEntity.ok(employeeSubscriptionService.create(EmployeeSubscriptionDTO));
    }

    /**
	 * Update information about a employeeSubscription.
	 *
	 * @param id  The identifier of the employeeSubscription to update.
	 * @param dto Information about the updated employeeSubscription.
	 * @return ResponseEntity containing information about the updated trainer.
	 * @throws AccessDeniedException    If access is denied.
	 * @throws AkdemiaBusinessException If a business exception occurs.
	 */
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeSubscriptionFullDTO> update(@PathVariable("id") int id, @RequestBody EmployeeSubscriptionFullDTO employeeSubscriptionDTO) 
    		throws AccessDeniedException, AkdemiaBusinessException {
    	return ResponseEntity.ok(employeeSubscriptionService.update(employeeSubscriptionDTO));
    }

    /**
	 * Delete a employeeSubscription by its identifier.
	 *
	 * @param id The identifier of the employeeSubscription to delete.
	 * @return ResponseEntity indicating the success of the deletion.
	 * @throws AkdemiaBusinessException If a business exception occurs.
	 * @throws AccessDeniedException    If access is denied.
	 */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) throws AkdemiaBusinessException, AccessDeniedException {
    	employeeSubscriptionService.deleteById(id);
        return ResponseEntity.ok().build();
    }
	
}
