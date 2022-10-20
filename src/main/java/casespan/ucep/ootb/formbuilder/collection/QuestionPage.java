package casespan.ucep.ootb.formbuilder.collection;

import com.google.gson.internal.LinkedTreeMap;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionPage {
    @Field(name="jsonSchema")
    private LinkedTreeMap<String, QuestionPageJSONSchema> jsonSchema;
    @Field(name="uiSchema")
    private LinkedTreeMap<String, QuestionPageUISchema> uiSchema;
}
