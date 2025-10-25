package com.jaccey.jopapp.company;


import com.jaccey.jopapp.job.Job;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies() {
        List<Company> companies = companyService.getAllCompanies();

        return ResponseEntity.ok(companies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompany(@PathVariable Long id) {
        Company company = companyService.getCompanyById(id);

        if(company != null) return new ResponseEntity<>(company, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<String> addCompany(@RequestBody Company company) {
        companyService.createCompany(company);

        return new ResponseEntity<String>("Company added successfully", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Long id, @RequestBody Company company) {
        boolean updated = companyService.updateCompany(id, company);

        if (updated) return new ResponseEntity<>("Company with id: " + id + " updated successfully", HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompanyById(@PathVariable Long id) {
        boolean deleted = companyService.deleteCompanyById(id);

        if (deleted) return new ResponseEntity<>("Company with id: " + id + " deleted successfully", HttpStatus.OK);

        return new ResponseEntity<>("Company Not Found.", HttpStatus.NOT_FOUND);
    }
}
