package tech.codeguru.jobly.entity.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResumeDTO {
    private String fileName;
    private String fileType;
    private byte[] fileData;
    private Long userProfileId;
}
