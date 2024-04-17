package eu.elev8x.ruleservice.service.implementation;

import eu.elev8x.ruleservice.persistance.condition.ConditionEntity;
import eu.elev8x.ruleservice.persistance.rule.RuleEntity;
import eu.elev8x.ruleservice.service.AggregatedRuleService;
import eu.elev8x.ruleservice.service.ConditionService;
import eu.elev8x.ruleservice.service.RuleService;
import eu.elex8x.apicore.composite.AggregatedRuleCreateRequest;
import eu.elex8x.apicore.composite.AggregatedRuleDTO;
import eu.elex8x.apicore.composite.AggregatedRuleUpdateRequest;
import eu.elex8x.apicore.core.condition.ConditionDTO;
import eu.elex8x.apicore.core.rule.RuleCreateRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DefaultAggregatedRuleService implements AggregatedRuleService {
    private final RuleService ruleService;
    private final ConditionService conditionService;

    @Override
    public AggregatedRuleDTO createAggregatedRule(AggregatedRuleCreateRequest request) {
        RuleEntity rule = ruleService.createRule(
                new RuleCreateRequest(
                        request.name(),
                        request.description(),
                        request.userId()
                )
        );

        Set<ConditionEntity> conditionEntities = request.conditions().stream()
                .map(condition -> conditionService.createCondition(condition, rule))
                .collect(Collectors.toSet());

        Set<ConditionDTO> conditionDTOs = conditionEntities.stream()
                .map(this::mapConditionEntityToDTO)
                .collect(Collectors.toSet());

        return new AggregatedRuleDTO(
                rule.getId(),
                rule.getName(),
                rule.getDescription(),
                conditionDTOs
        );
    }

    @Override
    public void updateAggregatedRule(AggregatedRuleUpdateRequest request) {

    }

    @Override
    public void deleteAggregatedRule(Long ruleId) {
        conditionService.deleteConditionByRuleID(ruleId);
        ruleService.deleteConditionByRuleID(ruleId);
    }

    @Override
    public Set<AggregatedRuleDTO> getScheduledAggregatedRules(String userId) {
        return null;
    }

    private ConditionDTO mapConditionEntityToDTO(ConditionEntity conditionEntity) {
        return new ConditionDTO(
                conditionEntity.getId(),
                conditionEntity.getAttribute(),
                conditionEntity.getOperator().getValue(),
                conditionEntity.getValue()
        );
    }
}
