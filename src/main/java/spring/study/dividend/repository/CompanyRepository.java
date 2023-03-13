package spring.study.dividend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.study.dividend.entity.CompanyEntity;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {
    Optional<CompanyEntity> findByName(String name);
    boolean existsByTicker(String ticker);
}
