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
public class Question {
    @Field("title")
    public String title;
    @Field("type")
    public String type;
    @Field("items")
    public LinkedTreeMap<String, QuestionItems> items;
}
