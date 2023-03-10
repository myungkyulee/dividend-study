package spring.study.dividend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.study.dividend.domain.CompanyEntity;
import spring.study.dividend.domain.DividendEntity;

@Repository
public interface DividendRepository extends JpaRepository<DividendEntity, Long> {
}
