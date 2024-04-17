package eu.elex8x.apicore.composite;

import eu.elex8x.apicore.core.condition.ConditionDTO;

import java.util.Set;

public record AggregatedRuleUpdateRequest(
        Long id,
        String name,
        String description,
        Set<ConditionDTO> conditions
) {}
