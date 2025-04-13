package tech.codeguru.jobly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.codeguru.jobly.entity.User;
import tech.codeguru.jobly.entity.dto.request.AuthenticationRequest;
import tech.codeguru.jobly.entity.dto.response.AuthenticationResponse;
import tech.codeguru.jobly.service.JwtUtilService;
import tech.codeguru.jobly.service.UserProfileService;

@RestController()
@RequestMapping("/api/v1")
public class AuthController {

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private JwtUtilService jwtService;


    @PostMapping("/user/register")
    public ResponseEntity<String> createUser(@RequestBody AuthenticationRequest authenticationRequest) {
        User createdUser = userProfileService.createUser(authenticationRequest);
        var jwtToken = jwtService.generateToken(createdUser);
        return new ResponseEntity<>(jwtToken, HttpStatus.CREATED);
    }

    @PostMapping("/user/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest authenticationRequest
    ) {
        return ResponseEntity.ok(userProfileService.authenticate(authenticationRequest));
    }


}
