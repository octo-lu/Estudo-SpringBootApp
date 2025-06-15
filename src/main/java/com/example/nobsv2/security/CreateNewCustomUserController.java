package com.example.nobsv2.security;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class CreateNewCustomUserController {

    private final PasswordEncoder encoder;

    private final CustomUserRepository customUserRepository;


    public CreateNewCustomUserController(PasswordEncoder encoder, CustomUserRepository customUserRepository) {
        this.encoder = encoder;
        this.customUserRepository = customUserRepository;
    }

    @PostMapping("/createnewuser")
    public ResponseEntity<String> createNewUser(@RequestBody CustomUser user) {
        //THIS WOULD GO ON A SERVICE CLASS, BUT ITS DEFINED HERE FOR LEARNING PURPOSES ONLY
        Optional<CustomUser> optionalUser = customUserRepository.findById(user.getUsername());
        if (!optionalUser.isPresent()) {
            customUserRepository.save(new CustomUser(
                    user.getUsername(),
                    encoder.encode(user.getPassword()),
                    user.getRole()));
            return ResponseEntity.ok("Success");
        }

        return ResponseEntity.badRequest().body("Failure");
    }
}
