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
import eu.elex8x.apicore.core.rule.RuleType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DefaultAggregatedRuleService implements AggregatedRuleService {
    private static final RuleType RULE_TYPE_CONSTANT = RuleType.SCHEDULED;

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
        Set<ConditionDTO> conditionDTOs = new HashSet<>();

        request.conditions()
                .forEach(conditionDTO -> {
                    ConditionEntity entity = conditionService.createCondition(conditionDTO, rule);
                    conditionDTOs.add(mapConditionEntityToDTO(entity));
                });

        return new AggregatedRuleDTO(
                rule.getId(),
                rule.getName(),
                rule.getDescription(),
                rule.getType().getValue(),
                conditionDTOs
        );
    }

    @Override
    public void updateAggregatedRule(AggregatedRuleUpdateRequest request) {
        //TODO
    }

    @Override
    public void deleteAggregatedRule(Long ruleId) {
        conditionService.deleteConditionByRuleID(ruleId);
        ruleService.deleteConditionByRuleID(ruleId);
    }

    @Override
    public List<AggregatedRuleDTO> getScheduledAggregatedRules(String userId) {
        List<RuleEntity> ruleEntities = ruleService.fetchUserRulesByType(
                userId,
                RULE_TYPE_CONSTANT
        );

        return ruleEntities
                .stream()
                .map(this::toAggregatedRule)
                .collect(Collectors.toList());
    }

    @Override
    public List<AggregatedRuleDTO> getAllScheduledAggregatedRules() {
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

    private Set<ConditionDTO> mapConditionEntitiesToDTOs(Set<ConditionEntity> conditionEntities) {
        return conditionEntities
                .stream()
                .map(this::mapConditionEntityToDTO)
                .collect(Collectors.toSet());
    }

    private AggregatedRuleDTO toAggregatedRule(RuleEntity ruleEntity) {
        return new AggregatedRuleDTO(
                ruleEntity.getId(),
                ruleEntity.getName(),
                ruleEntity.getDescription(),
                ruleEntity.getType().getValue(),
                mapConditionEntitiesToDTOs(ruleEntity.getConditions())
        );
    }
}
