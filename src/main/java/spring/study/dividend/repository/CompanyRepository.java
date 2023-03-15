package spring.study.dividend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.study.dividend.entity.CompanyEntity;


import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {
    Optional<CompanyEntity> findByName(String name);
    Optional<CompanyEntity> findByTicker(String ticker);
    boolean existsByTicker(String ticker);
    // IgnoreCase는 대소문자에 상관없이 찾으라는 뜻
    Page<CompanyEntity> findByNameStartingWithIgnoreCase(String s, Pageable pageable);
}
