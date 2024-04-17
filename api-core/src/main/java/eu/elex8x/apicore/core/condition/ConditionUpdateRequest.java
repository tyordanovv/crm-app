package eu.elex8x.apicore.core.condition;

public record ConditionUpdateRequest(
        Long id,
        String attribute,
        String operator,
        String value
) {
}
