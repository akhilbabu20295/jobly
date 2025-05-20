package tech.codeguru.jobly.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.codeguru.jobly.entity.Company;
import tech.codeguru.jobly.entity.dto.request.CompanyDTO;
import tech.codeguru.jobly.repository.CompanyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyRepository companyRepository;


    @Override
    public Company createCompany(CompanyDTO companyDTO) {
        Company company = convertToEntity(companyDTO);
        return companyRepository.save(company);
    }


    @Override
    public Company updateCompany(Long id, CompanyDTO companyDTO) {
        return companyRepository.findById(id).map(existingCompany -> {
            existingCompany.setName(companyDTO.getName());
            existingCompany.setLogoUrl(companyDTO.getLogoUrl());
            existingCompany.setWebsite(companyDTO.getWebsite());
            existingCompany.setIndustry(companyDTO.getIndustry());
            existingCompany.setLocation(companyDTO.getLocation());
            existingCompany.setDescription(companyDTO.getDescription());
            return companyRepository.save(existingCompany);
        }).orElseThrow(() -> new RuntimeException("Company not found with ID: " + id));
    }

    @Override
    public Optional<Company> getCompanyById(Long id) {
        return companyRepository.findById(id);
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }

    public Company convertToEntity(CompanyDTO companyDTO) {
        Company company = new Company();
        company.setName(companyDTO.getName());
        company.setDescription(companyDTO.getDescription());
        company.setWebsite(companyDTO.getWebsite());
        company.setLocation(companyDTO.getLocation());
        company.setIndustry(companyDTO.getIndustry());
        company.setLogoUrl(company.getLogoUrl());
        return company;
    }
}