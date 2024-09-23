package edu.npic.sps.features.auth;

import edu.npic.sps.features.auth.dto.JwtResponse;
import edu.npic.sps.features.auth.dto.LoginRequest;
import edu.npic.sps.features.auth.dto.RefreshTokenRequest;
import edu.npic.sps.features.auth.dto.VerifyRequest;
import edu.npic.sps.features.user.dto.CreateUserRegister;
import jakarta.mail.MessagingException;

public interface AuthService {

    JwtResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

    JwtResponse login(LoginRequest loginRequest);

    void register(CreateUserRegister createUserRegister) throws MessagingException;

    void verify(VerifyRequest verifyRequest);
}
