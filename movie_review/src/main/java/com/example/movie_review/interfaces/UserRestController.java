package com.example.movie_review.interfaces;

import com.example.movie_review.application.dto.user.CreateUserDTO;
import com.example.movie_review.application.dto.user.EditUserDTO;
import com.example.movie_review.application.service.UserService;
import com.example.movie_review.domain.user.User;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserRestController {

    private UserService userService;

    @PostMapping
    private ResponseEntity<Void> createUser(final @RequestBody CreateUserDTO dto){
        this.userService.createUser(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    private ResponseEntity<User> getUser(final @PathVariable String id){
        return ResponseEntity.ok(this.userService.getById(id));
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deleteUser(final @PathVariable String id){
        this.userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    private ResponseEntity<Void> editUser(final @PathVariable String id, final @RequestBody EditUserDTO dto){
       this.userService.editUser(id, dto);
       return ResponseEntity.ok().build();
    }
}
