package eu.elex8x.apicore.core.user;

public record UserRegistrationRequest(
        String email,
        String username,
        String password
) {
}
