package com.example.movie_review.application.service;

import com.example.movie_review.application.dto.user.CreateUserDTO;
import com.example.movie_review.application.dto.user.EditUserDTO;
import com.example.movie_review.domain.user.User;
import com.example.movie_review.domain.user.UserRepositoryI;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepositoryI userRepository;

    public void createUser(final CreateUserDTO dto){

        this.checkEmail(dto.getEmail());
        this.checkPassword(dto.getPassword());
        this.checkName(dto.getName());


        final User user = new User(
                UUID.randomUUID().toString(),
                dto.getName(),
                dto.getEmail(),
                dto.getPassword()
        );

        this.userRepository.saveUser(user);
    }



    public User getById(final String id){
        return this.userRepository.getById(id);
    }


    public void deleteUser(final String id){
        this.userRepository.deleteUser(id);
    }


    public void editUser(final String id, EditUserDTO dto){
        final User user = this.userRepository.getById(id);
        user.edit(dto.getName(), dto.getPassword());
        this.userRepository.saveUser(user);
    }


    private void checkEmail(final String email){
        if (this.userRepository.checkEmail(email)){
            throw new RuntimeException("Email already in the system");
        }

        if (email.length() < 3 || email.length() > 50 || !email.contains("@")){
            throw new RuntimeException("Email form not suitable");
        }
    }

    private void checkPassword(final String password){
        if (password.length() < 8 || password.length() > 30){
            throw new RuntimeException("Incorrect length of password");
        }

        if (!password.matches(".*\\d+.*")){
            throw new RuntimeException("Password needs to contain at least 1 number");
        }
    }

    private void checkName(final String name){
        if (name.length() > 30){
            throw new RuntimeException("Name is too long");
        }
    }
}
