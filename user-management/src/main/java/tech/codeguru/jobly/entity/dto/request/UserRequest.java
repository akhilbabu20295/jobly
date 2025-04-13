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
    private String fullName;
    private String email;
    private String phoneNumber;
    private String address;
    private String location;
    private String mobile;
    private String dateOfBirth;
}
