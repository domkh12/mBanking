package edu.npic.sps.features.auth;

import edu.npic.sps.features.auth.dto.JwtResponse;
import edu.npic.sps.features.auth.dto.LoginRequest;
import edu.npic.sps.features.auth.dto.RefreshTokenRequest;
import edu.npic.sps.features.auth.dto.VerifyRequest;
import edu.npic.sps.features.user.dto.CreateUserRegister;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/refresh")
    JwtResponse refreshToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest){
        return authService.refreshToken(refreshTokenRequest);
    }

    @PostMapping("/verify")
    void verify(@Valid @RequestBody VerifyRequest verifyRequest){
        authService.verify(verifyRequest);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/registers")
    void register(@Valid @RequestBody CreateUserRegister createUserRegister) throws MessagingException {
        authService.register(createUserRegister);
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/login")
    JwtResponse login(@Valid @RequestBody LoginRequest loginRequest){
        return authService.login(loginRequest);
    }

}
