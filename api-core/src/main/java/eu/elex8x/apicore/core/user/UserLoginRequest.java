package eu.elex8x.apicore.core.user;

public record UserLoginRequest(
        String username,
        String password
) {
}
