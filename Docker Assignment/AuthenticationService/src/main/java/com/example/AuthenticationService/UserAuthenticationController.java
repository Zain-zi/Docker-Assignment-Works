package com.example.AuthenticationService;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserAuthenticationController {
    private final UserService userService;

    @Autowired
    public UserAuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/authenticate")
    public ResponseEntity<User> authenticate(@RequestBody Credentials credentials) {
        String hashedPassword = DigestUtils.md5Hex(credentials.getPassword());
        Optional<User> optionalUser = userService.getUser(credentials.getEmail(), hashedPassword);
        return optionalUser.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }
}
