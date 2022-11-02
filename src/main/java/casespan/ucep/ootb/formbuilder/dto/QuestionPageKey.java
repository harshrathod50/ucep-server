package casespan.ucep.ootb.formbuilder.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class QuestionPageKey {
    private String applicationName;
    private String currentQuestionPage;
    private long scriptExecutionId;
}
