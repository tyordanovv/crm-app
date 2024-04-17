package eu.elev8x.ruleservice.persistance.rule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuleRepository extends JpaRepository<RuleEntity, Long> {
    void deleteAllByRuleId(Long ruleId);
}
