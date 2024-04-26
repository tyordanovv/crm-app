package eu.elev8x.ruleservice.service.implementation;

import eu.elev8x.ruleservice.persistance.condition.ConditionEntity;
import eu.elev8x.ruleservice.persistance.rule.RuleEntity;
import eu.elev8x.ruleservice.persistance.rule.RuleRepository;
import eu.elex8x.apicore.composite.AggregatedRuleCreateRequest;
import eu.elex8x.apicore.composite.AggregatedRuleDTO;
import eu.elex8x.apicore.core.condition.ConditionDTO;
import eu.elex8x.apicore.core.condition.ConditionOperator;
import eu.elex8x.apicore.core.rule.RuleCreateRequest;
import eu.elex8x.apicore.core.rule.RuleDTO;
import eu.elex8x.apicore.core.rule.RuleType;
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
public class RuleServiceTest {
    @Mock
    private RuleRepository repository;
    @InjectMocks
    private DefaultRuleService ruleService;

    @Test
    void createRule_ShouldReturnRuleEntity() {
        // Arrange
        LocalDateTime time = LocalDateTime.now();
        RuleEntity mockRuleEntity = new RuleEntity(1L, "user1", "Test Rule", "Test Description", time, new HashSet<>(), RuleType.SCHEDULED);
        RuleCreateRequest ruleCreateRequest = new RuleCreateRequest("Test Rule", "Test Description", "user1");

        when(repository.save(any()))
                .thenReturn(mockRuleEntity);

        // Act
        RuleEntity returnedRuleEntity = ruleService.createRule(ruleCreateRequest);

        // Assert
        assertNotNull(returnedRuleEntity);
        assertEquals(returnedRuleEntity.getId(), mockRuleEntity.getId());
        assertEquals(returnedRuleEntity.getName(), mockRuleEntity.getName());
        assertEquals(returnedRuleEntity.getDescription(), mockRuleEntity.getDescription());
        assertEquals(returnedRuleEntity.getUpdatedAt(), time);
        assertEquals(returnedRuleEntity.getConditions().size(), 0);
    }
}
