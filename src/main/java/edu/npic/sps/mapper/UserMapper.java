package edu.npic.sps.mapper;

import edu.npic.sps.domain.User;
import edu.npic.sps.features.user.dto.CreateUserRegister;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User fromCreateUserRegister(CreateUserRegister createUserRegister);

}
