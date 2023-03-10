package spring.study.dividend.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity(name = "DIVIDEND")
@Getter
@ToString
@NoArgsConstructor
public class DividendEntity {
    @Id @GeneratedValue
    private Long id;
    private Long companyId;
    private String dividend;
    private LocalDateTime date;
}
