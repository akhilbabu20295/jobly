package tech.codeguru.jobly.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tech.codeguru.jobly.entity.RecruiterProfile;
import tech.codeguru.jobly.entity.Role;
import tech.codeguru.jobly.entity.User;
import tech.codeguru.jobly.entity.UserProfile;
import tech.codeguru.jobly.entity.dto.request.AuthenticationRequest;
import tech.codeguru.jobly.entity.dto.request.UserRequest;
import tech.codeguru.jobly.entity.dto.response.AuthenticationResponse;
import tech.codeguru.jobly.repository.RecruiterProfileRepository;
import tech.codeguru.jobly.repository.UserProfileRepository;
import tech.codeguru.jobly.repository.UserRepository;

@Service
public class UserProfileService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProfileRepository userProfileRepository;


    @Autowired
    private RecruiterProfileRepository recruiterProfileRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private JwtUtilService jwtService;


    public User convertToEntity(AuthenticationRequest authenticationRequest) {
        User user = new User();
        user.setEmail(authenticationRequest.getEmail());
        user.setPassword(passwordEncoder.encode(authenticationRequest.getPassword()));
        user.setRole(Role.USER);
        return user;
    }

    public User createUser(AuthenticationRequest authenticationRequest) {
        User user = convertToEntity(authenticationRequest);
        userRepository.save(user);

        Role userType = Role.valueOf(authenticationRequest.getUserType());

        switch (userType) {
            case USER -> {
                UserProfile userProfile = new UserProfile();
                userProfile.setEmail(authenticationRequest.getEmail());
                userProfile.setUser(user);
                userProfileRepository.save(userProfile);
            }
            case RECRUITER -> {
                RecruiterProfile recruiterProfile = new RecruiterProfile();
                recruiterProfile.setEmail(authenticationRequest.getEmail());
                recruiterProfile.setUser(user);
                recruiterProfileRepository.save(recruiterProfile);
            }
            default -> throw new IllegalArgumentException("Unsupported user type: " + userType);
        }
        return user;
    }

    public UserProfile getUsers(Long Id) {
        return userProfileRepository.findByUser_Id(Id);
    }

    public UserProfile updateUserProfile(Long Id, UserRequest userRequest) {
        UserProfile profile = userProfileRepository.findByUser_Id(Id);
        profile.setFullName(userRequest.getFullName());
        profile.setAddress(userRequest.getAddress());
        profile.setPhoneNumber(userRequest.getPhoneNumber());
        profile.setMobile(userRequest.getMobile());
        profile.setDateOfBirth(userRequest.getDateOfBirth());
        profile.setLocation(userRequest.getLocation());
        userProfileRepository.save(profile);
        return profile;
    }

    public void updateExperience(Long id, Integer newExperience) {
        float updated = userProfileRepository.updateExperienceById(id, newExperience);
        if (updated == 0) {
            throw new RuntimeException("User not found or update failed");
        }
    }


    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

}
