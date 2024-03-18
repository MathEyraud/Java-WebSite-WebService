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

import af.cmr.indyli.akdemia.business.dto.basic.CompanyBasicDTO;
import af.cmr.indyli.akdemia.business.dto.full.CompanyFullDTO;
import af.cmr.indyli.akdemia.business.exception.AkdemiaBusinessException;
import af.cmr.indyli.akdemia.business.service.ICompanyService;
import af.cmr.indyli.akdemia.business.utils.ConstsValues;
import af.cmr.indyli.akdemia.ws.utils.AkdemiaUrlBase;
import jakarta.annotation.Resource;

/**
 * RESTful controller to manage company-related operations.
 */
@RestController
@CrossOrigin(origins = AkdemiaUrlBase.url, maxAge = AkdemiaUrlBase.maxAge)
@RequestMapping("/companies")
public class CompagnyController {

	@Resource(name = ConstsValues.ServiceKeys.COMPANY_SERVICE_KEY)
	private ICompanyService companyService;

	/**
	 * Retrieve a list of all companies.
	 *
	 * @return ResponseEntity containing the list of companies.
	 */
	@GetMapping
	public ResponseEntity<List<CompanyBasicDTO>> getAll() {
		return ResponseEntity.ok(ResponseEntity.ok(companyService.findAll()).getBody());
	}

	/**
	 * Retrieve information about a company by its identifier.
	 *
	 * @param id The identifier of the company.
	 * @return ResponseEntity containing information about the company.
	 * @throws AkdemiaBusinessException If a business exception occurs.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<CompanyFullDTO> getOne(@PathVariable int id) throws AkdemiaBusinessException {
		return ResponseEntity.ok(companyService.findById(id));
	}

	/**
	 * Create a new company.
	 *
	 * @param dto Information about the company to create.
	 * @return ResponseEntity containing information about the newly created
	 *         company.
	 * @throws AkdemiaBusinessException If a business exception occurs.
	 */
	@PostMapping
	public ResponseEntity<CompanyFullDTO> create(@RequestBody CompanyFullDTO dto) throws AkdemiaBusinessException {
		return ResponseEntity.ok(companyService.create(dto));
	}

	/**
	 * Update information about a company.
	 *
	 * @param id  The identifier of the company to update.
	 * @param dto Information about the updated company.
	 * @return ResponseEntity containing information about the updated company.
	 * @throws AccessDeniedException    If access is denied.
	 * @throws AkdemiaBusinessException If a business exception occurs.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<CompanyFullDTO> update(@PathVariable int id, @RequestBody CompanyFullDTO dto)
			throws AccessDeniedException, AkdemiaBusinessException {
		return ResponseEntity.ok(companyService.update(dto));
	}

	/**
	 * Delete a company by its identifier.
	 *
	 * @param id The identifier of the company to delete.
	 * @return ResponseEntity indicating the success of the deletion.
	 * @throws AkdemiaBusinessException If a business exception occurs.
	 * @throws AccessDeniedException    If access is denied.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) throws AkdemiaBusinessException, AccessDeniedException {
		companyService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
