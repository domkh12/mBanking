package edu.npic.sps.features.user;

import edu.npic.sps.domain.Role;
import edu.npic.sps.domain.User;
import edu.npic.sps.features.user.dto.CreateUserRegister;
import edu.npic.sps.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void register(CreateUserRegister createUserRegister) {

        if (userRepository.existsByEmail(createUserRegister.email())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Email already exists!"
            );
        }

        if (!createUserRegister.password().equals(createUserRegister.confirmPassword())){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Passwords and confirm passwords do not match!"
            );
        }

        User user = userMapper.fromCreateUserRegister(createUserRegister);
        user.setUuid(UUID.randomUUID().toString());
        user.setIsVerified(false);
        user.setPassword(passwordEncoder.encode(createUserRegister.password()));
        user.setProfileImage("default-avatar.png");
        user.setCreatedAt(LocalDateTime.now());
        user.setIsAccountNonExpired(true);
        user.setIsAccountNonLocked(true);
        user.setIsCredentialsNonExpired(true);
        user.setIsDeleted(false);

        List<Role> roles = new ArrayList<>();
        roles.add(Role.builder().name("STAFF").build());
        user.setRoles(roles);
        userRepository.save(user);
    }

}
