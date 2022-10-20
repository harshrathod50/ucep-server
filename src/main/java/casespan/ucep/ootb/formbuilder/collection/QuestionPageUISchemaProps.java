
package casespan.ucep.ootb.formbuilder.collection;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Field;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionPageUISchemaProps {

    @Field("disabled")
    private boolean disabled;
    @Field("className")
    private String className;
}
