package eu.elev8x.ruleservice.service;

import eu.elev8x.ruleservice.persistance.rule.RuleEntity;
import eu.elex8x.apicore.core.rule.RuleCreateRequest;
import eu.elex8x.apicore.core.rule.RuleType;

import java.util.List;
import java.util.Set;

public interface RuleService {
    RuleEntity createRule(RuleCreateRequest request);

    void deleteConditionByRuleID(Long ruleId);

    List<RuleEntity> fetchUserRulesByType(String userId, RuleType ruleType);
}
