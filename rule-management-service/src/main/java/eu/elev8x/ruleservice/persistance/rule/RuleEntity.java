package eu.elev8x.ruleservice.persistance.rule;

import eu.elev8x.ruleservice.persistance.condition.ConditionEntity;
import eu.elex8x.apicore.core.rule.RuleDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "rules")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RuleEntity {
    @Id
    @SequenceGenerator(
            name = "rule_id_sequence",
            sequenceName = "rule_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "rule_id_sequence"
    )
    @Column(
            name = "rule_id"
    )
    private Long id;
    @Column(
            name = "user_id",
            nullable = false
    )
    private String userId;
    @Column(
            name = "rule_name",
            nullable = false
    )
    private String name;
    @Column(
            name = "rule_description"
    )
    private String description;
    @Column(
            name = "updated_at",
            nullable = false
    )
    private LocalDateTime updatedAt;
    @OneToMany(
            mappedBy="rules"
    )
    private Set<ConditionEntity> conditions;

    public RuleDTO toRuleDTO() {
        return new RuleDTO(
                this.id,
                this.name,
                this.description,
                this.updatedAt,
                this.userId
        );
    }
}
