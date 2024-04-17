package eu.elex8x.apicore.core.rule;

public record RuleCreateRequest(
        String name,
        String description,
        String userId

) {
}
