package spring.study.dividend.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import spring.study.dividend.entity.CompanyEntity;
import spring.study.dividend.entity.DividendEntity;
import spring.study.dividend.model.Company;
import spring.study.dividend.model.ScrapedResult;
import spring.study.dividend.repository.CompanyRepository;
import spring.study.dividend.repository.DividendRepository;
import spring.study.dividend.scraper.Scraper;

import java.util.List;

import static spring.study.dividend.model.constants.CacheKey.KEY_FINANCE;

@Component
@Slf4j
@RequiredArgsConstructor
public class ScraperScheduler {
    private final CompanyRepository companyRepository;
    private final Scraper yahooFinanceScraper;
    private final DividendRepository dividendRepository;

    // 일정 주기마다 수행
    @CacheEvict(value = KEY_FINANCE, allEntries = true)
    @Scheduled(cron = "${scheduler.scrap.yahoo}")
    public void yahooFinanceScheduling(){
        log.info("scraping scheduler is started");

        // 저장된 회사 목록을 조회
        List<CompanyEntity> companies = companyRepository.findAll();

        for (CompanyEntity company : companies) {
            log.info("scraping scheduler is started -> " + company.getName());
            ScrapedResult scrapedResult = yahooFinanceScraper
                    .scrap(new Company(company.getName(), company.getTicker()));

            scrapedResult.getDividends().stream()
                    .map(e -> new DividendEntity(company.getId(), e))
                    .forEach(e -> {
                        boolean exists = dividendRepository
                                .existsByCompanyIdAndDate(e.getCompanyId(), e.getDate());
                        if (!exists) {
                            dividendRepository.save(e);
                            log.info("insert new dividend -> " + e);
                        }
                    });
            // 연속적으로 스크래핑 대상 사이트 서버에 요청을 보내지 않도록 일시정지
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
