package eu.elex8x.userservice.service.implementation;

import eu.elex8x.apicore.core.user.UserLoginRequest;
import eu.elex8x.apicore.core.user.UserRegistrationRequest;
import eu.elex8x.userservice.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.*;
import software.amazon.awssdk.services.secretsmanager.endpoints.internal.Value;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class AuthServiceImplementation implements AuthService {
    private static final String clientId = "clientId";
    private static final String secretKey = "secretKey";
    private static final String userPoolId = "userPoolId";
    private static CognitoIdentityProviderClient identityProviderClient;


    public void registerUser(UserRegistrationRequest userRequest) {
        identityProviderClient = CognitoIdentityProviderClient.builder()
                .region(Region.US_EAST_1)
                .build();

        AttributeType attributeType = AttributeType.builder()
                .name("email")
                .value(userRequest.email())
                .build();

        List<AttributeType> attrs = new ArrayList<>();
        attrs.add(attributeType);
        try {
            String secretVal = calculateSecretHash(clientId, secretKey, userRequest.username());
            SignUpRequest signUpRequest = SignUpRequest.builder()
                    .userAttributes(attrs)
                    .username(userRequest.username())
                    .clientId(clientId)
                    .password(userRequest.password())
                    .secretHash(secretVal)
                    .build();

            identityProviderClient.signUp(signUpRequest);
            System.out.println("User has been signed up");

        } catch (CognitoIdentityProviderException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }

        identityProviderClient.close();
    }

    public AdminInitiateAuthResponse loginUser(UserLoginRequest userRequest) {
        identityProviderClient = CognitoIdentityProviderClient.builder()
                .region(Region.US_EAST_1)
                .build();

        try {
            Map<String, String> authParameters = new HashMap<>();
            authParameters.put("USERNAME", userRequest.username());
            authParameters.put("PASSWORD", userRequest.password());

            AdminInitiateAuthRequest authRequest = AdminInitiateAuthRequest.builder()
                    .clientId(clientId)
                    .userPoolId(userPoolId)
                    .authParameters(authParameters)
                    .authFlow(AuthFlowType.ADMIN_USER_PASSWORD_AUTH)
                    .build();

            AdminInitiateAuthResponse response = identityProviderClient.adminInitiateAuth(authRequest);
            System.out.println("Result Challenge is : " + response.challengeName());

            identityProviderClient.close();
            return response;

        } catch (CognitoIdentityProviderException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
        }
        identityProviderClient.close();
        return null;
    }

    public static String calculateSecretHash(String userPoolClientId, String userPoolClientSecret, String userName)
            throws NoSuchAlgorithmException, InvalidKeyException {
        final String HMAC_SHA256_ALGORITHM = "HmacSHA256";

        SecretKeySpec signingKey = new SecretKeySpec(
                userPoolClientSecret.getBytes(StandardCharsets.UTF_8),
                HMAC_SHA256_ALGORITHM);

        Mac mac = Mac.getInstance(HMAC_SHA256_ALGORITHM);
        mac.init(signingKey);
        mac.update(userName.getBytes(StandardCharsets.UTF_8));
        byte[] rawHmac = mac.doFinal(userPoolClientId.getBytes(StandardCharsets.UTF_8));
        return java.util.Base64.getEncoder().encodeToString(rawHmac);
    }
}
