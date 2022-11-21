package casespan.ucep.ootb.formbuilder.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class QuestionPageData {

    private String applicationName;
    private String currentPageName;
    private String formAnswers;
    private String jsonSchema;
    private String uiSchema;
    private long scriptExecutionId;
    private boolean isSubmitPage;
    private boolean isPreviousButtonEnabled;
    private ArrayList<String> sectionNames;
    private String currentSection;
}
