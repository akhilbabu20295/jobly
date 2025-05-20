package tech.codeguru.jobly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.codeguru.jobly.entity.Company;
import tech.codeguru.jobly.entity.dto.request.CompanyDTO;
import tech.codeguru.jobly.service.CompanyService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping("/create")
    public ResponseEntity<Company> createCompany(@RequestBody CompanyDTO companyDTO) {
        return ResponseEntity.ok(companyService.createCompany(companyDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable Long id, @RequestBody CompanyDTO companyDTO) {
        return ResponseEntity.ok(companyService.updateCompany(id, companyDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
        return companyService.getCompanyById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies() {
        return ResponseEntity.ok(companyService.getAllCompanies());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        companyService.deleteCompany(id);
        return ResponseEntity.noContent().build();
    }
}
