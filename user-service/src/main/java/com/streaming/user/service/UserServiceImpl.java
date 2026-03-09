package com.streaming.user.service;
import com.streaming.user.dto.UserRequestDTO;
import com.streaming.user.dto.UserResponseDTO;
import com.streaming.user.entity.User;
import com.streaming.user.exception.UserAlreadyExistsException;
import com.streaming.user.exception.UserNotFoundException;
import com.streaming.user.mapper.UserMapper;
import com.streaming.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toResponseDTO).toList();
    }
    @Override
    @Transactional(readOnly = true)
    public UserResponseDTO getUserById(Long id) {
        return userMapper.toResponseDTO(
            userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Utilisateur non trouve: id=" + id))
        );
    }
    @Override
    public UserResponseDTO createUser(UserRequestDTO dto) {
        if (userRepository.existsByUsername(dto.getUsername()))
            throw new UserAlreadyExistsException("Username deja utilise: " + dto.getUsername());
        if (userRepository.existsByEmail(dto.getEmail()))
            throw new UserAlreadyExistsException("Email deja utilise: " + dto.getEmail());
        User user = userMapper.toEntity(dto);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        return userMapper.toResponseDTO(userRepository.save(user));
    }
    @Override
    public UserResponseDTO updateUser(Long id, UserRequestDTO dto) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("Utilisateur non trouve: id=" + id));
        userMapper.updateEntityFromDTO(dto, user);
        if (dto.getPassword() != null && !dto.getPassword().isBlank())
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
        return userMapper.toResponseDTO(userRepository.save(user));
    }
    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) throw new UserNotFoundException("Utilisateur non trouve: id=" + id);
        userRepository.deleteById(id);
    }
}