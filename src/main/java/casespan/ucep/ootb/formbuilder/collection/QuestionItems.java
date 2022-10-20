package casespan.ucep.ootb.formbuilder.collection;

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
public class QuestionItems {
    @Field("enum")
    public List<String> enumList;
    @Field("type")
    public String type;
}
