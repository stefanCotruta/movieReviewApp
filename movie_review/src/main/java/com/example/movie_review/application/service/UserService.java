package com.example.movie_review.application.service;

import com.example.movie_review.application.dto.user.CreateUserDTO;
import com.example.movie_review.application.dto.user.EditUserDTO;
import com.example.movie_review.domain.user.User;
import com.example.movie_review.domain.user.UserRepositoryI;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepositoryI userRepository;

    private void createUser(final CreateUserDTO dto){
        final User user = new User(
                UUID.randomUUID().toString(),
                dto.getName(),
                dto.getEmail(),
                dto.getPassword()
        );

        this.userRepository.saveUser(user);
    }


    private List<User> getUsers(){
        return this.userRepository.getUsers();
    }

    private void deleteUser(final String id){
        this.userRepository.deleteUser(id);
    }

    private void editUser(final String id, EditUserDTO dto){
        final User user = this.userRepository.getById(id);
        user.edit(dto.getName(), dto.getPassword());
        this.userRepository.saveUser(user);
    }
}
