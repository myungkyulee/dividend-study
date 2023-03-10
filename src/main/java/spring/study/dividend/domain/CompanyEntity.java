package spring.study.dividend.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
}
