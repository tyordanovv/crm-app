package eu.elex8x.apicore.core.rule;

import java.time.LocalDateTime;

public record RuleDTO (
        Long id,
        String name,
        String description,
        LocalDateTime lastUpdate,
        String userId
){}
