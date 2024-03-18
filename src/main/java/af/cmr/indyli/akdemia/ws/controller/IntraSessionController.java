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

import af.cmr.indyli.akdemia.business.dto.basic.IntraSessionBasicDTO;
import af.cmr.indyli.akdemia.business.dto.full.IntraSessionFullDTO;
import af.cmr.indyli.akdemia.business.exception.AkdemiaBusinessException;
import af.cmr.indyli.akdemia.business.service.IIntraSessionService;
import af.cmr.indyli.akdemia.business.utils.ConstsValues;
import af.cmr.indyli.akdemia.ws.utils.AkdemiaUrlBase;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.annotation.Resource;

@RestController
	@CrossOrigin(origins = AkdemiaUrlBase.url, maxAge = AkdemiaUrlBase.maxAge)
	@RequestMapping("/intrasessions")
	public class IntraSessionController {
		
		@Resource(name = ConstsValues.ServiceKeys.INTRA_SESSION_SERVICE_KEY)
	    private IIntraSessionService intraSessionService;
		
		@GetMapping
	    public ResponseEntity<List<IntraSessionBasicDTO>> getAll() {
	        return ResponseEntity.ok(ResponseEntity.ok(intraSessionService.findAll()).getBody());
	    }
		
		@GetMapping("/{id}")
	    public ResponseEntity<IntraSessionFullDTO> getOne(@PathVariable("id") int id) throws AkdemiaBusinessException {
	        return ResponseEntity.ok(intraSessionService.findById(id));
	    }
		
		
	    @PostMapping
	    public ResponseEntity<IntraSessionFullDTO> create(@RequestBody IntraSessionFullDTO intraSessionDTO) throws AkdemiaBusinessException {
	    	return ResponseEntity.ok(intraSessionService.create(intraSessionDTO));
	    }

	    
	    @PutMapping("/{id}")
	    public ResponseEntity<IntraSessionFullDTO> update(@PathVariable("id") int id, @RequestBody IntraSessionFullDTO intraSessionDTO) 
	    		throws AccessDeniedException, AkdemiaBusinessException {
	    	return ResponseEntity.ok(intraSessionService.update(intraSessionDTO));
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> delete(@PathVariable("id") int id) throws AkdemiaBusinessException, AccessDeniedException {
	        intraSessionService.deleteById(id);
	        return ResponseEntity.ok().build();
	    }


}