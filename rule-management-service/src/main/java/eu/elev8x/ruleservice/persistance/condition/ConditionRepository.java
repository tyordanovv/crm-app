package eu.elev8x.ruleservice.persistance.condition;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConditionRepository extends JpaRepository<ConditionEntity, Long> {
}
