package eu.elev8x.ruleservice.service;

import eu.elev8x.ruleservice.persistance.condition.ConditionEntity;
import eu.elev8x.ruleservice.persistance.rule.RuleEntity;
import eu.elex8x.apicore.core.condition.ConditionDTO;
import org.springframework.transaction.annotation.Transactional;

public interface ConditionService {
    ConditionEntity createCondition(ConditionDTO request, RuleEntity rule);

    void deleteConditionByRuleID(Long ruleId);
}
