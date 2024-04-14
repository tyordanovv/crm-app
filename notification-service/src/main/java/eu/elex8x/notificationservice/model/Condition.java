package eu.elex8x.notificationservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "conditions")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Condition {
    @Id
    private Long id;
    @Column(name = "attribute")
    private String attribute;
    @Column(name = "operator")
    private ConditionOperator operator;
    @Column(name = "value")
    private Object value;
}