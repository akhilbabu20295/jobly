package tech.codeguru.jobly.entity.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String bio;
    private String firstName;
    private String lastName;
    private String email;
    private String designation;
    private String address;
    private String location;
    private String mobile;
    private String dateOfBirth;
}
