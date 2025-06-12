package com.example.latihan.service;

import java.util.List;

import com.example.latihan.dto.UserCreateDTO;
import com.example.latihan.dto.UserDTO;
import com.example.latihan.dto.UserUpdateDTO;

public interface UserService {

    List<UserDTO> getAllUsers();

    UserDTO getUserById(Long id);

    UserDTO createUser(UserCreateDTO userCreateDTO);

    UserDTO updateUser(Long id, UserUpdateDTO UserUpdateDTO);

    void deleteUser(Long id);
}
