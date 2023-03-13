package spring.study.dividend.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import spring.study.dividend.model.Dividend;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity(name = "DIVIDEND")
@Getter
@ToString
@NoArgsConstructor
public class DividendEntity {
    @Id
    @GeneratedValue
    private Long id;
    private Long companyId;
    private String dividend;
    private LocalDateTime date;

    public DividendEntity(Long companyId, Dividend dividend) {
        this.companyId = companyId;
        this.date = dividend.getDate();
        this.dividend = dividend.getDividend();
    }
}
