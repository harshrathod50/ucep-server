package casespan.ucep.ootb.formbuilder.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class QuestionPageKey {
    private String applicationId;
    private String currentQuestionPage;
}
