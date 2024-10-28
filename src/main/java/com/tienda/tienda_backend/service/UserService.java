package com.tienda.tienda_backend.service;

import com.tienda.tienda_backend.model.User;
import java.util.Optional;
import java.util.List;

public interface UserService {

    // Método para guardar un usuario
    User saveUser(User user);
    // Método para obtener un usuario por su username
    Optional<User> findByUsername(String username);
    // Método para obtener todos los usuarios
    List<User> getAllUsers();
    // Método para eliminar un usuario
    void deleteUser(Long id);
    // Método para obtener un usuario por su id
    Optional<User> findById(Long id);
    // Método para actualizar un usuario
    Optional<User> updateUser(Long id, User updatedUser); 
    // Método para el login
    Optional<User> login(String username, String password);
}