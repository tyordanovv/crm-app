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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AggregatedRuleServiceTest {

    @Mock
    private RuleService ruleService;
    @Mock
    private ConditionService conditionService;

    @InjectMocks
    private DefaultAggregatedRuleService defaultAggregatedRuleService;

    @Test
    void createAggregatedRule_ShouldReturnAggregatedRuleDTO() {
        // Arrange
        RuleEntity mockRuleEntity = new RuleEntity(1L, "user1", "Test Rule", "Test Description", LocalDateTime.now(), new HashSet<>());
        ConditionEntity conditionEntity1 = new ConditionEntity(1L, "Attribute1", ConditionOperator.EQUAL, "Value1", mockRuleEntity);
        ConditionEntity conditionEntity2 = new ConditionEntity(2L, "Attribute2", ConditionOperator.LESS_THAN, "Value2", mockRuleEntity);

        Set<ConditionDTO> mockConditionEntities = new HashSet<>();
        mockConditionEntities.add(new ConditionDTO(1L, "Attribute1", ConditionOperator.EQUAL.getValue(), "Value1"));
        mockConditionEntities.add(new ConditionDTO(2L, "Attribute2", ConditionOperator.LESS_THAN.getValue(), "Value2"));

        AggregatedRuleCreateRequest request = new AggregatedRuleCreateRequest("Test Rule", "Test Description", mockConditionEntities,  "user1");


        when(ruleService.createRule(any(RuleCreateRequest.class))).thenReturn(mockRuleEntity);
        when(conditionService.createCondition(any(), any())).thenAnswer(invocation -> {
            ConditionDTO conditionDTO = invocation.getArgument(0);
            return new ConditionEntity(
                    1L,
                    conditionDTO.attribute(),
                    ConditionOperator.fromString(conditionDTO.operator()),
                    conditionDTO.value(),
                    mockRuleEntity);
        });

        // Act
        AggregatedRuleDTO aggregatedRuleDTO = defaultAggregatedRuleService.createAggregatedRule(request);

        // Assert
        assertNotNull(aggregatedRuleDTO);
        assertEquals(mockRuleEntity.getId(), aggregatedRuleDTO.ruleId());
        assertEquals(mockRuleEntity.getName(), aggregatedRuleDTO.name());
        assertEquals(mockRuleEntity.getDescription(), aggregatedRuleDTO.description());
        assertEquals(2, aggregatedRuleDTO.conditions().size()); // Assuming there are 2 conditions
    }
}
