package eu.elex8x.apicore.core.contact;

import java.util.Map;

public record ContactRequest(
        String email,
        PhoneNumber number,
        Map<String, Object> attributes
) {
}
