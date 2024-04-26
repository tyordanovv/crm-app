package eu.elex8x.apicore.core.rule;

import eu.elex8x.apicore.core.condition.ConditionOperator;

public enum RuleType {
    SCHEDULED("SCHEDULED"),
    TEMPLATE("TEMPLATE"),
    SINGLE("SINGLE");

    private String value;

    RuleType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static RuleType fromString(String text) {
        for (RuleType type : RuleType.values()) {
            if (type.value.equalsIgnoreCase(text)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No constant with text " + text + " found");
    }
}
