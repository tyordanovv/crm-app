package eu.elex8x.apicore.core.condition;

public enum ConditionOperator {
    EQUAL("EQUAL"),
    NOT_EQUAL("NOT_EQUAL"),
    GREATER_THAN("GREATER_THAN"),
    LESS_THAN("LESS_THAN"),
    GREATER_OR_EQUAL("GREATER_OR_EQUAL"),
    LESS_OR_EQUAL("LESS_OR_EQUAL");

    private String value;

    ConditionOperator(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ConditionOperator fromString(String text) {
        for (ConditionOperator operator : ConditionOperator.values()) {
            if (operator.value.equalsIgnoreCase(text)) {
                return operator;
            }
        }
        throw new IllegalArgumentException("No constant with text " + text + " found");
    }
}
