
package casespan.ucep.ootb.formbuilder.collection;

import com.google.gson.internal.LinkedTreeMap;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionPageUISchema {

    private LinkedTreeMap<String, QuestionUISchema> questionUISchema;
}
