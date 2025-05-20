package tech.codeguru.jobly.entity.dto.request;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyDTO {
    private String name;

    private String logoUrl;
    private String website;
    private String industry;
    private String location;
    private String description;
}
