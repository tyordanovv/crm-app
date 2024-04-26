package eu.elex8x.apicore.core.contact;
public enum PhoneAreaCode {
    BULGARIA("+359");

    private String value;

    PhoneAreaCode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static PhoneAreaCode fromString(String text) {
        for (PhoneAreaCode code : PhoneAreaCode.values()) {
            if (code.value.equalsIgnoreCase(text)) {
                return code;
            }
        }
        throw new IllegalArgumentException("No constant with text " + text + " found");
    }
}
