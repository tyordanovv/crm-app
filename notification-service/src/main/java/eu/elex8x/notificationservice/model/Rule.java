package eu.elex8x.notificationservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "rules")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Rule {
    @Id
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
    private LocalDate updatedAt;
}
