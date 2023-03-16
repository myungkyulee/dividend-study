package spring.study.dividend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import spring.study.dividend.entity.CompanyEntity;
import spring.study.dividend.entity.DividendEntity;
import spring.study.dividend.exception.impl.NoCompanyException;
import spring.study.dividend.model.Company;
import spring.study.dividend.model.Dividend;
import spring.study.dividend.model.ScrapedResult;
import spring.study.dividend.repository.CompanyRepository;
import spring.study.dividend.repository.DividendRepository;

import java.util.List;
import java.util.stream.Collectors;

import static spring.study.dividend.model.constants.CacheKey.KEY_FINANCE;

@Slf4j
@Service
@RequiredArgsConstructor
public class FinanceService {
    private final CompanyRepository companyRepository;
    private final DividendRepository dividendRepository;


    @Cacheable(key = "#companyName", value = KEY_FINANCE)
    public ScrapedResult getDividendByCompanyName(String companyName) {
        log.info("search company");
        // 1. 회사명을 기준으로 회사 정보를 조회
        CompanyEntity companyEntity = companyRepository.findByName(companyName)
                .orElseThrow(() -> new NoCompanyException());
        // 2. 조회된 회사 ID로 배당금 정보 조회
        List<DividendEntity> dividendEntities = dividendRepository.findAllByCompanyId(companyEntity.getId());

        List<Dividend> dividends = dividendEntities.stream()
                .map(e -> new Dividend(e.getDate(), e.getDividend()))
                .collect(Collectors.toList());

        // 3. 결과 조합 후 반환
        return new ScrapedResult(
                new Company(companyEntity.getTicker(), companyEntity.getName()),
                dividends);
    }
}
