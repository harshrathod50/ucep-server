package casespan.ucep.ootb.formbuilder.collection;

import com.google.gson.internal.LinkedTreeMap;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuestionUISchema {
    @Field(name = "ui:title")
    private String uiTitle;
    @Field("ui:description")
    private String uiDescription;
    @Field("ui:classNames")
    private String uiClassNames;
    @Field("ui:autofocus")
    private boolean uiAutofocus;
    @Field("ui:emptyValue")
    private String uiEmptyValue;
    @Field("ui:autocomplete")
    private String uiAutocomplete;
    @Field("ui:widget")
    private String uiWidget;
    @Field("ui:help")
    private String uiHelp;
    @Field("submitText")
    private String submitText;
    @Field("norender")
    private boolean noRender;
    @Field("props")
    private LinkedTreeMap<String, QuestionPageUISchemaProps> props;
}
