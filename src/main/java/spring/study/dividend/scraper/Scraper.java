package spring.study.dividend.scraper;

import spring.study.dividend.model.Company;
import spring.study.dividend.model.ScrapedResult;

public interface Scraper {
    Company scrapCompanyByTicker(String ticker);
    ScrapedResult scrap(Company company);
}
