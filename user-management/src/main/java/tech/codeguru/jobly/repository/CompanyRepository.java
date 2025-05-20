package tech.codeguru.jobly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.codeguru.jobly.entity.Company;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    Optional<Company> findByName(String name);
}
