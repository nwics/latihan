package com.example.latihan.service.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.latihan.dao.RoleRepository;
import com.example.latihan.dao.UserRepository;
import com.example.latihan.dto.PermissionDTO;
import com.example.latihan.dto.RoleDTO;
import com.example.latihan.dto.UserCreateDTO;
import com.example.latihan.dto.UserDTO;
import com.example.latihan.dto.UserUpdateDTO;
import com.example.latihan.model.Permission;
import com.example.latihan.model.Role;
import com.example.latihan.model.User;
import com.example.latihan.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private UserDTO mapToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setUserId(user.getUserId());
        dto.setUsername(user.getUsername());
        dto.setRoles(user.getRoles() != null
                ? user.getRoles().stream().map(this::mapToDTO).collect(Collectors.toList())
                : null);
        return dto;
    }

    private RoleDTO mapToDTO(Role role) {
        RoleDTO dto = new RoleDTO();
        dto.setRoleId(role.getRoleId());
        dto.setRoleName(role.getRoleName());
        dto.setPermissions(role.getPermissions() != null
                ? role.getPermissions().stream().map(this::mapToDTO).collect(Collectors.toSet())
                : null);
        return dto;
    }

    private PermissionDTO mapToDTO(Permission permission) {
        PermissionDTO dto = new PermissionDTO();
        dto.setPermissionId(permission.getId());
        dto.setPermissionType(permission.getPermissionType());
        return dto;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method
        // 'getAllUsers'");
        return userRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(Long id) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'getUserById'");
        User user = userRepository.findByUserIdWithRolesAndPermissions(Long id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return this.mapToDTO(user);
    }

    @Override
    public UserDTO createUser(UserCreateDTO userCreateDTO) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'createUser'");
        if (userRepository.existsByUsername(userCreateDTO.getUsername())) {
            throw new RuntimeException("Username already exists: " + userCreateDTO.getUsername());
        }

        User user = new User();
        user.setUsername(userCreateDTO.getUsername());
        user.setPassword(userCreateDTO.getPassword());

        User savedUser = userRepository.save(user);
        return this.mapToDTO(savedUser);
    }

    @Override
    public UserDTO updateUser(Long id, UserUpdateDTO UserUpdateDTO) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

    }

    @Override
    public void deleteUser(Long id) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method 'deleteUser'");
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

}
