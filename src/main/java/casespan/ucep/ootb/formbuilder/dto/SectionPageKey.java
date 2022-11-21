package casespan.ucep.ootb.formbuilder.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SectionPageKey {
    private String sectionName;
    private long scriptExecutionId;
}
