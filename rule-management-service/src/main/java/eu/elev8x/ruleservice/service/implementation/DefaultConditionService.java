package eu.elev8x.ruleservice.service.implementation;

import eu.elev8x.ruleservice.persistance.condition.ConditionEntity;
import eu.elev8x.ruleservice.persistance.condition.ConditionRepository;
import eu.elev8x.ruleservice.persistance.rule.RuleEntity;
import eu.elev8x.ruleservice.service.ConditionService;
import eu.elex8x.apicore.core.condition.ConditionDTO;
import eu.elex8x.apicore.core.condition.ConditionOperator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DefaultConditionService implements ConditionService {
    private final ConditionRepository repository;

    @Override
    public ConditionEntity createCondition(ConditionDTO condition, RuleEntity rule) {
        return repository.save(
                ConditionEntity.builder()
                        .value(condition.value())
                        .operator(ConditionOperator.fromString(condition.operator()))
                        .attribute(condition.attribute())
                        .rule(rule)
                        .build());
    }

    @Override
    public void deleteConditionByRuleID(Long ruleId) {
        repository.deleteById(ruleId);
    }
}
