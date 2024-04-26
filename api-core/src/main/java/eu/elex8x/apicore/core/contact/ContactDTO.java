package eu.elex8x.apicore.core.contact;

import java.util.Map;

public record ContactDTO(
        String id,
        String firstName,
        String lastName,
        String email,
        PhoneNumber number
//        ,
//        Map<String, Object> attributes
) {
}
