package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;
/*    private BCryptPasswordEncoder encoder;
    public void setPasswordEncoder(BCryptPasswordEncoder encoder) {
        this.encoder = encoder;
    }*/
    @Autowired
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public User findById(Long id){
        return userRepository.getById(id);
    }
    @Transactional(readOnly = true)
    public List<User> findAll(){
        return userRepository.findAll();
    }
    @Transactional
    public User saveUser(User user) {
        user.setPassword(password().encode(user.getPassword()));
        return userRepository.save(user);
    }
    @Transactional
    public void deleteById(Long id){
        userRepository.deleteById(id);
    }
    @Transactional(readOnly = true)
    public User findByName(String name) {
        return userRepository.findByName(name);
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
    public PasswordEncoder password() {
        return new BCryptPasswordEncoder(12);
    }
}
