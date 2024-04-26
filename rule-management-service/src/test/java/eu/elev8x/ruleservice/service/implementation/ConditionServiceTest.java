package eu.elev8x.ruleservice.service.implementation;

import eu.elev8x.ruleservice.persistance.condition.ConditionEntity;
import eu.elev8x.ruleservice.persistance.condition.ConditionRepository;
import eu.elev8x.ruleservice.persistance.rule.RuleEntity;
import eu.elev8x.ruleservice.persistance.rule.RuleRepository;
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
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ConditionServiceTest {
    @Mock
    private ConditionRepository repository;
    @InjectMocks
    private DefaultConditionService ruleService;

    @Test
    void createRule_ShouldReturnRuleEntity() {
        // Arrange
        LocalDateTime time = LocalDateTime.now();
        RuleEntity mockRuleEntity = new RuleEntity(1L, "user1", "Test Rule", "Test Description", time, new HashSet<>(), RuleType.SCHEDULED);
        ConditionDTO mockConditionDTO = new ConditionDTO(null, "attribute", ConditionOperator.EQUAL.toString(), "value");

        ConditionEntity mockConditionEntity = new ConditionEntity(1L, "attribute", ConditionOperator.EQUAL, "value", mockRuleEntity);

        when(repository.save(any(ConditionEntity.class)))
                .thenReturn(mockConditionEntity);

        // Act
        ConditionEntity returnedConditionEntity = ruleService.createCondition(mockConditionDTO, mockRuleEntity);

        // Assert
        assertNotNull(returnedConditionEntity);
        assertEquals(returnedConditionEntity.getId(), mockConditionEntity.getId());
        assertEquals(returnedConditionEntity.getValue(), mockConditionDTO.value());
        assertEquals(returnedConditionEntity.getAttribute(), mockConditionDTO.attribute());
        assertEquals(returnedConditionEntity.getOperator().getValue(), mockConditionDTO.operator());
        assertEquals(returnedConditionEntity.getRule().getId(), mockRuleEntity.getId());
    }
}
