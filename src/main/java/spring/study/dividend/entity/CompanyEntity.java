package spring.study.dividend.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import spring.study.dividend.model.Company;

import javax.persistence.*;

@Entity(name = "COMPANY")
@Getter
@ToString
@NoArgsConstructor
public class CompanyEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Column(unique = true)
    private String ticker;

    public CompanyEntity(Company company) {
        this.name = company.getName();
        this.ticker = company.getTicker();
    }
}
