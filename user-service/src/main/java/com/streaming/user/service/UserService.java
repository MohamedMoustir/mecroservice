package com.streaming.user.service;
import com.streaming.user.dto.UserRequestDTO;
import com.streaming.user.dto.UserResponseDTO;
import java.util.List;
public interface UserService {
    List<UserResponseDTO> getAllUsers();
    UserResponseDTO getUserById(Long id);
    UserResponseDTO createUser(UserRequestDTO dto);
    UserResponseDTO updateUser(Long id, UserRequestDTO dto);
    void deleteUser(Long id);
}