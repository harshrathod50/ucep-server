package casespan.ucep.ootb.formbuilder.dto;

import casespan.ucep.ootb.formbuilder.collection.QuestionPageJSONSchema;
import casespan.ucep.ootb.formbuilder.collection.QuestionPageUISchema;
import com.google.gson.internal.LinkedTreeMap;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

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
}
