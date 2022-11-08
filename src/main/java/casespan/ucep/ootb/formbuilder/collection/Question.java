package casespan.ucep.ootb.formbuilder.collection;

import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.LinkedTreeMap;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    @Field("title")
    public String title;
    @Field("type")
    public String type;
    @Field("enum")
    @SerializedName("enum")
    public List<String> enumList;
    @Field("oneOf")
    public List<OneOf> oneOf;
    @Field("answer")
    public String answer;
}
