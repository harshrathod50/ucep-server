package casespan.ucep.ootb.formbuilder.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class QuestionPageAnswers {
    private String applicationName;
    private String currentPageName;
    private String formAnswers;
    private String jwtToken;
    private long scriptExecutionId;
}
