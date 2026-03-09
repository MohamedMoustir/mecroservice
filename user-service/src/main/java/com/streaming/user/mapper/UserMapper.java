package com.streaming.user.mapper;
import com.streaming.user.dto.UserRequestDTO;
import com.streaming.user.dto.UserResponseDTO;
import com.streaming.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {
    UserResponseDTO toResponseDTO(User user);
    User toEntity(UserRequestDTO dto);
    void updateEntityFromDTO(UserRequestDTO dto, @MappingTarget User user);
}