package eu.elev8x.ruleservice.persistance.condition;

import eu.elev8x.ruleservice.persistance.rule.RuleEntity;
import eu.elex8x.apicore.core.condition.ConditionOperator;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "conditions")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConditionEntity {
    @Id
    @SequenceGenerator(
            name = "condition_id_sequence",
            sequenceName = "condition_id_sequence")
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "condition_id_sequence")
    private Long id;

    @Column(name = "attribute",
            nullable = false)
    private String attribute;

    @Column(name = "operator",
            nullable = false)
    @Enumerated(EnumType.STRING)
    private ConditionOperator operator;

    @Column(name = "value",
            nullable = false)
    private String value;

    @ManyToOne
    @JoinColumn(
            name="rule_id",
            nullable = false)
    private RuleEntity rule;
}