package spring.study.dividend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.study.dividend.entity.CompanyEntity;
import spring.study.dividend.entity.DividendEntity;
import spring.study.dividend.model.Company;
import spring.study.dividend.model.Dividend;
import spring.study.dividend.model.ScrapedResult;
import spring.study.dividend.repository.CompanyRepository;
import spring.study.dividend.repository.DividendRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FinanceService {
    private final CompanyRepository companyRepository;
    private final DividendRepository dividendRepository;


    public ScrapedResult getDividendByCompanyName(String companyName) {

        // 1. 회사명을 기준으로 회사 정보를 조회
        CompanyEntity companyEntity = companyRepository.findByName(companyName)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회사명입니다."));
        // 2. 조회된 회사 ID로 배당금 정보 조회
        List<DividendEntity> dividendEntities = dividendRepository.findAllByCompanyId(companyEntity.getId());

        List<Dividend> dividends = dividendEntities.stream().map(e -> Dividend.builder()
                        .date(e.getDate())
                        .dividend(e.getDividend())
                        .build())
                .collect(Collectors.toList());

        // 3. 결과 조합 후 반환
        return new ScrapedResult(
                Company.builder()
                        .ticker(companyEntity.getTicker())
                        .name(companyEntity.getName())
                        .build(),
                dividends);
    }
}
