package org.example.ecommerce.application.useCases.login.doLogin;

import org.example.ecommerce.domain.repositories.user.UserRepository;
import org.example.ecommerce.domain.security.cryptography.PasswordEncripter;
import org.example.ecommerce.domain.security.tokens.AccessTokenGenerator;
import org.example.ecommerce.shared.communication.request.user.RequestLoginJson;
import org.example.ecommerce.shared.communication.response.ResponseTokensJson;
import org.example.ecommerce.shared.communication.response.user.ResponseRegisteredUserJson;
import org.example.ecommerce.shared.exception.InvalidLoginException;
import org.springframework.stereotype.Service;

@Service
public class DoLoginUseCase {

    private final UserRepository userRepository;
    private final PasswordEncripter passwordEncoder;
    private final AccessTokenGenerator accessTokenGenerator;

    public DoLoginUseCase(
            UserRepository userRepository,
            PasswordEncripter passwordEncoder,
            AccessTokenGenerator accessTokenGenerator
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.accessTokenGenerator = accessTokenGenerator;
    }

    public ResponseRegisteredUserJson execute(RequestLoginJson request){
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new InvalidLoginException());

        if(!passwordEncoder.checkPassword(request.getPassword(), user.getPassword())){
            throw new InvalidLoginException();
        }

        return ResponseRegisteredUserJson.builder()
                .email(request.getEmail())
                .tokens(
                        ResponseTokensJson.builder()
                                .accessToken(accessTokenGenerator.generateToken(user))
                                .build())
                .build();
    }
}
