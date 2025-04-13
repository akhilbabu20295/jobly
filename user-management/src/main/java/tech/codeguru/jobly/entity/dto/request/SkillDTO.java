package tech.codeguru.jobly.entity.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SkillDTO {
    private String name;
    private String description;
}
