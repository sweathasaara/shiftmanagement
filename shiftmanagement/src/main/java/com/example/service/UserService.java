package com.example.service;

import com.example.dto.UserDTO;
import com.example.entity.User;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    private UserDTO convertToDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getFullName(),
                user.getEmail(),
                user.isActive()
        );
    }

    // Convert DTO → Entity
    private User convertToEntity(UserDTO dto) {
        return new User(
                dto.getId(),
                dto.getUsername(),
                dto.getFullName(),
                dto.getEmail(),
                dto.isActive()
        );
    }
    public UserDTO addUser(UserDTO dto) {
        User user = convertToEntity(dto);
        return convertToDTO(userRepository.save(user));
    }
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    public UserDTO getUserById(Long id) {
        return userRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }
    public UserDTO updateUser(Long id, UserDTO dto) {
        User user = userRepository.findById(id).orElse(null);

        if (user != null) {
            user.setUsername(dto.getUsername());
            user.setFullName(dto.getFullName());
            user.setEmail(dto.getEmail());
            user.setActive(dto.isActive());

            return convertToDTO(userRepository.save(user));
        }
        return null;
    }
    public String deleteUser(Long id) {
        userRepository.deleteById(id);
        return "User deleted successfully";
    }
}