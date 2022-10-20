package casespan.ucep.ootb.formbuilder.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ApplicationKey {
    private String applicationName;
    private long scriptExecutionId;
}
