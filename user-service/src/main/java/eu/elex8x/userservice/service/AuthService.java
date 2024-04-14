package eu.elex8x.userservice.service;

import eu.elex8x.apicore.core.user.UserLoginRequest;
import eu.elex8x.apicore.core.user.UserRegistrationRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminInitiateAuthResponse;

public interface AuthService {
    void registerUser(UserRegistrationRequest request);
    AdminInitiateAuthResponse loginUser(UserLoginRequest request);
}
