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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import af.cmr.indyli.akdemia.business.dto.basic.InterSessionBasicDTO;
import af.cmr.indyli.akdemia.business.dto.full.InterSessionFullDTO;
import af.cmr.indyli.akdemia.business.exception.AkdemiaBusinessException;
import af.cmr.indyli.akdemia.business.service.IInterSessionService;
import af.cmr.indyli.akdemia.business.utils.ConstsValues;
import af.cmr.indyli.akdemia.ws.utils.AkdemiaUrlBase;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.annotation.Resource;

@RestController
@CrossOrigin(origins = AkdemiaUrlBase.url, maxAge = AkdemiaUrlBase.maxAge)
@RequestMapping("/intersessions")
public class InterSessionController {
	
	@Resource(name = ConstsValues.ServiceKeys.INTER_SESSION_SERVICE_KEY)
    private IInterSessionService interSessionService;
	
	@GetMapping
    public ResponseEntity<List<InterSessionBasicDTO>> getAll() {
        return ResponseEntity.ok(ResponseEntity.ok(interSessionService.findAll()).getBody());
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<InterSessionFullDTO> getOne(@PathVariable("id") int id) throws AkdemiaBusinessException {
        return ResponseEntity.ok(interSessionService.findById(id));
    }
	
    @PostMapping
    public ResponseEntity<InterSessionFullDTO> create(@RequestBody InterSessionFullDTO interSessionDTO) throws AkdemiaBusinessException {
    	return ResponseEntity.ok(interSessionService.create(interSessionDTO));
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<InterSessionFullDTO> update(@PathVariable("id") int id, @RequestBody InterSessionFullDTO interSessionDTO) 
    		throws AccessDeniedException, AkdemiaBusinessException {
    	return ResponseEntity.ok(interSessionService.update(interSessionDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) throws AkdemiaBusinessException, AccessDeniedException {
        interSessionService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
