package casespan.ucep.ootb.formbuilder.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class QuestionPageJson {
    @Getter @Setter
    private String jsonSchema;
    @Getter @Setter
    private String uiSchema;
}
