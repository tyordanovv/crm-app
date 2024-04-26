package eu.elev8x.ruleservice.service.implementation;

import eu.elev8x.ruleservice.persistance.condition.ConditionEntity;
import eu.elev8x.ruleservice.persistance.rule.RuleEntity;
import eu.elev8x.ruleservice.service.ConditionService;
import eu.elev8x.ruleservice.service.RuleService;
import eu.elex8x.apicore.composite.AggregatedRuleCreateRequest;
import eu.elex8x.apicore.composite.AggregatedRuleDTO;
import eu.elex8x.apicore.core.condition.ConditionDTO;
import eu.elex8x.apicore.core.condition.ConditionOperator;
import eu.elex8x.apicore.core.rule.RuleCreateRequest;
import eu.elex8x.apicore.core.rule.RuleType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AggregatedRuleServiceTest {
    private static final String userId = "user1";


    @Mock
    private RuleService ruleService;
    @Mock
    private ConditionService conditionService;

    @InjectMocks
    private DefaultAggregatedRuleService defaultAggregatedRuleService;

    @Test
    void createAggregatedRule_ShouldReturnAggregatedRuleDTO() {
        // Arrange
        RuleEntity mockRuleEntity = new RuleEntity(1L, userId, "Test Rule", "Test Description", LocalDateTime.now(), new HashSet<>(), RuleType.SCHEDULED);
        ConditionEntity conditionEntity1 = new ConditionEntity(1L, "Attribute1", ConditionOperator.EQUAL, "Value1", mockRuleEntity);
        ConditionEntity conditionEntity2 = new ConditionEntity(2L, "Attribute2", ConditionOperator.LESS_THAN, "Value2", mockRuleEntity);

        Set<ConditionDTO> mockConditionDTO = new HashSet<>();
        ConditionDTO conditionDTO1 = new ConditionDTO(1L, "Attribute1", ConditionOperator.EQUAL.getValue(), "Value1");
        ConditionDTO conditionDTO2 = new ConditionDTO(2L, "Attribute2", ConditionOperator.LESS_THAN.getValue(), "Value2");
        mockConditionDTO.add(conditionDTO1);
        mockConditionDTO.add(conditionDTO2);

        AggregatedRuleCreateRequest request = new AggregatedRuleCreateRequest("Test Rule", "Test Description", mockConditionDTO,  userId);

        when(ruleService.createRule(any(RuleCreateRequest.class)))
                .thenReturn(mockRuleEntity);
        when(conditionService.createCondition(conditionDTO1, mockRuleEntity))
                .thenReturn(conditionEntity1);
        when(conditionService.createCondition(conditionDTO2, mockRuleEntity))
                .thenReturn(conditionEntity2);

        // Act
        AggregatedRuleDTO aggregatedRuleDTO = defaultAggregatedRuleService.createAggregatedRule(request);

        // Assert
        assertNotNull(aggregatedRuleDTO);
        assertEquals(mockRuleEntity.getId(), aggregatedRuleDTO.ruleId());
        assertEquals(mockRuleEntity.getName(), aggregatedRuleDTO.name());
        assertEquals(mockRuleEntity.getDescription(), aggregatedRuleDTO.description());
        assertEquals(2, aggregatedRuleDTO.conditions().size()); // Assuming there are 2 conditions
    }

    @Test
    void getScheduledAggregatedRules_ShouldReturnAggregatedRuleDTO() {
        // Arrange

        ConditionEntity conditionEntity1 = new ConditionEntity(1L, "Attribute1", ConditionOperator.EQUAL, "Value1", null);
        ConditionEntity conditionEntity2 = new ConditionEntity(2L, "Attribute2", ConditionOperator.LESS_THAN, "Value2", null);

        Set<ConditionEntity> mockConditionEntities = new HashSet<>();
        mockConditionEntities.add(conditionEntity1);
        mockConditionEntities.add(conditionEntity2);

        RuleEntity mockRuleEntity1 = new RuleEntity(1L, userId, "Test Rule", "Test Description", LocalDateTime.now(), mockConditionEntities, RuleType.SCHEDULED);
        RuleEntity mockRuleEntity2 = new RuleEntity(2L, userId, "Test Rule2", "Test Description2", LocalDateTime.now(), mockConditionEntities, RuleType.SCHEDULED);

        List<RuleEntity> mockRuleEntities = new ArrayList<>();
        mockRuleEntities.add(mockRuleEntity1);
        mockRuleEntities.add(mockRuleEntity2);

        when(ruleService.fetchUserRulesByType(any(String.class), eq(RuleType.SCHEDULED)))
                .thenReturn(mockRuleEntities);

        // Act
        List<AggregatedRuleDTO> aggregatedRuleDTOs = defaultAggregatedRuleService.getScheduledAggregatedRules(userId);

        // Assert
        assertNotNull(aggregatedRuleDTOs);
        assertEquals(aggregatedRuleDTOs.get(0).conditions().size(), 2);
    }
}
