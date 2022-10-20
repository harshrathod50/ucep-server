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
public class Section {
    @Field("name")
    private String name;

    @Field("title")
    private String title;

    @Field("handler")
    private String handler;

    @Field("questionPages")
    private LinkedTreeMap<String, QuestionPage> questionPages;
}
