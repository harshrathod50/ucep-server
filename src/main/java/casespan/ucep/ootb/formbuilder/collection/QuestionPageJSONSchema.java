
package casespan.ucep.ootb.formbuilder.collection;

import com.google.gson.internal.LinkedTreeMap;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Field;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionPageJSONSchema {

    @Field("name")
    private String name;

    @Field("title")
    private String title;

    @Field("handler")
    private String handler;

    @Field("description")
    private String description;

    @Field("type")
    private String type;

    @Field("required")
    private List<String> requiredList;

    @Field("properties")
    private LinkedTreeMap<String, Question> properties;
}
