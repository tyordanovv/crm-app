package eu.elev8x.ruleservice.persistance.rule;

import eu.elex8x.apicore.core.rule.RuleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RuleRepository extends JpaRepository<RuleEntity, Long> {
    void deleteAllByRuleId(Long ruleId);

    List<RuleEntity> findByUserIdAndType(String userId, RuleType type);
}
