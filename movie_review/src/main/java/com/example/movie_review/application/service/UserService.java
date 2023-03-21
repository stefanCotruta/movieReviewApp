package com.example.movie_review.application.service;

import com.example.movie_review.application.dto.user.CreateUserDTO;
import com.example.movie_review.application.dto.user.EditUserDTO;
import com.example.movie_review.domain.ValidationHelper;
import com.example.movie_review.domain.user.User;
import com.example.movie_review.domain.user.UserRepositoryI;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@AllArgsConstructor
public class UserService {
    private UserRepositoryI userRepository;
    private ValidationHelper validationHelper;

    public void createUser(final CreateUserDTO dto){

        this.checkIfEmailIsInUse(dto.getEmail());
        this.validationHelper.validateEmail(dto.getEmail());
        this.validationHelper.validateName(dto.getPassword());
        this.validationHelper.validatePassword(dto.getName());


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


    private void checkIfEmailIsInUse(final String email){
        if (this.userRepository.checkEmail(email)){
            throw new RuntimeException("Email already in the system");
        }
    }


}
