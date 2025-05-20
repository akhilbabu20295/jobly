package tech.codeguru.jobly.service;

import tech.codeguru.jobly.entity.Company;
import tech.codeguru.jobly.entity.dto.request.CompanyDTO;

import java.util.List;
import java.util.Optional;

public interface CompanyService {

    Company createCompany(CompanyDTO company);

    Company updateCompany(Long id, CompanyDTO companyDTO);

    Optional<Company> getCompanyById(Long id);

    List<Company> getAllCompanies();

    void deleteCompany(Long id);
}
