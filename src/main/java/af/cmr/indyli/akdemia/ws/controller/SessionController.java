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

import af.cmr.indyli.akdemia.business.dto.basic.SessionBasicDTO;
import af.cmr.indyli.akdemia.business.dto.full.SessionFullDTO;
import af.cmr.indyli.akdemia.business.exception.AkdemiaBusinessException;
import af.cmr.indyli.akdemia.business.service.ISessionService;
import af.cmr.indyli.akdemia.business.utils.ConstsValues;
import af.cmr.indyli.akdemia.ws.utils.AkdemiaUrlBase;
import jakarta.annotation.Resource;

@RestController
@CrossOrigin(origins = AkdemiaUrlBase.url, maxAge = AkdemiaUrlBase.maxAge)
@RequestMapping("/sessions")
public class SessionController {
	
	@Resource(name = ConstsValues.ServiceKeys.SESSION_SERVICE_KEY)
    private ISessionService sessionService;
	
	@GetMapping
    public ResponseEntity<List<SessionBasicDTO>> getAll() {
        return ResponseEntity.ok(ResponseEntity.ok(sessionService.findAll()).getBody());
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<SessionFullDTO> getOne(@PathVariable("id") int id) throws AkdemiaBusinessException {
        return ResponseEntity.ok(sessionService.findById(id));
    }
	
	
    @PostMapping
    public ResponseEntity<SessionFullDTO> create(@RequestBody SessionFullDTO sessionDTO) throws AkdemiaBusinessException {
    	return ResponseEntity.ok(sessionService.create(sessionDTO));
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<SessionFullDTO> update(@PathVariable("id") int id, @RequestBody SessionFullDTO sessionDTO) 
    		throws AccessDeniedException, AkdemiaBusinessException {
    	return ResponseEntity.ok(sessionService.update(sessionDTO));
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) throws AkdemiaBusinessException, AccessDeniedException {
        sessionService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
