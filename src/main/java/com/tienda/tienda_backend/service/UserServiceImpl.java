package com.tienda.tienda_backend.service;
import com.tienda.tienda_backend.model.User;
import com.tienda.tienda_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tienda.tienda_backend.exception.UsernameAlreadyExistsException;


import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    
    @Override
    public User saveUser(User user) {
        Optional<User> existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser.isPresent()) {
            throw new UsernameAlreadyExistsException("El nombre de usuario ya existe");
        }
        return userRepository.save(user);
    }
    
    // Método para obtener un usuario por su username
    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    
    // Método para obtener todos los usuarios
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    // Método para obtener un usuario por su id
    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
    
    // Método para eliminar un usuario
    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // Método para actualizar un usuario
    @Override
    public Optional<User> updateUser(Long id, User updatedUser) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setNombre(updatedUser.getNombre());
            user.setUsername(updatedUser.getUsername());
            user.setPassword(updatedUser.getPassword());
            user.setRole(updatedUser.getRole());
            return Optional.of(userRepository.save(user));
        }
        return Optional.empty();
    }
    
    // Método para el login
    @Override
    public Optional<User> login(String username, String password) {
        try {
            System.out.println("Intentando encontrar el usuario: " + username);
            Optional<User> user = userRepository.findByUsername(username);
    
            if (user.isPresent()) {
                System.out.println("Usuario encontrado: " + user.get().getUsername());
                if (user.get().getPassword().equals(password)) {
                    System.out.println("Contraseña correcta");
                    return user;
                } else {
                    System.out.println("Contraseña incorrecta");
                }
            } else {
                System.out.println("Usuario no encontrado");
            }
            return Optional.empty();
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
}   }