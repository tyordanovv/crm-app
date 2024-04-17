package eu.elex8x.apicore.core.condition;

public record ConditionDTO(
        Long conditionId,
        String attribute,
        String operator,
        String value
) {
}
