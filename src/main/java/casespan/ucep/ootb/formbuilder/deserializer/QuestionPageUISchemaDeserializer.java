package casespan.ucep.ootb.formbuilder.deserializer;

import casespan.ucep.ootb.formbuilder.collection.QuestionPageUISchema;
import casespan.ucep.ootb.formbuilder.collection.QuestionPageUISchemaProps;
import casespan.ucep.ootb.formbuilder.collection.QuestionUISchema;
import com.google.gson.*;
import com.google.gson.internal.LinkedTreeMap;

import java.lang.reflect.Type;
import java.util.Map;

public class QuestionPageUISchemaDeserializer implements JsonDeserializer<QuestionPageUISchema > {
    @Override
    public QuestionPageUISchema deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        QuestionPageUISchema  questionPageUISchema  = new QuestionPageUISchema ();
        deserializeQuestionPageUISchema(jsonElement, questionPageUISchema );
        return questionPageUISchema ;
    }

    private void deserializeQuestionPageUISchema(JsonElement jsonElement, QuestionPageUISchema  questionPageUISchema ) {
        LinkedTreeMap<String, QuestionUISchema > questionLinkedTreeMap =
                new LinkedTreeMap<>();
        for(Map.Entry<String, JsonElement> uiSchemaEntry :
                jsonElement.getAsJsonObject().entrySet()){
            QuestionUISchema  questionUISchema  = new QuestionUISchema ();
            deserializeQuestionUISchema(uiSchemaEntry.getValue(), questionUISchema );
            questionLinkedTreeMap.put(uiSchemaEntry.getKey(), questionUISchema );

        }
        questionPageUISchema .setQuestionUISchema(questionLinkedTreeMap);

    }

    private void deserializeQuestionUISchema(JsonElement jsonElement,
                                             QuestionUISchema questionUISchema ){
        for(Map.Entry<String, JsonElement> questionUISchemaEntry :
                jsonElement.getAsJsonObject().entrySet()){
            if("ui:title".equals(questionUISchemaEntry.getKey())){
                questionUISchema .setUiTitle(questionUISchemaEntry.getValue().getAsString());
            }
            if("ui:description".equals(questionUISchemaEntry.getKey())){
                questionUISchema .setUiDescription(questionUISchemaEntry.getValue().getAsString());
            }
            if("ui:classNames".equals(questionUISchemaEntry.getKey())){
                questionUISchema .setUiClassNames(questionUISchemaEntry.getValue().getAsString());
            }
            if("ui:autofocus".equals(questionUISchemaEntry.getKey())){
                questionUISchema .setUiAutofocus(questionUISchemaEntry.getValue().getAsBoolean());
            }
            if("ui:emptyValue".equals(questionUISchemaEntry.getKey())){
                questionUISchema .setUiEmptyValue(questionUISchemaEntry.getValue().getAsString());
            }
            if("ui:autocomplete".equals(questionUISchemaEntry.getKey())){
                questionUISchema .setUiAutocomplete(questionUISchemaEntry.getValue().getAsString());
            }
            if("ui:help".equals(questionUISchemaEntry.getKey())){
                questionUISchema .setUiHelp(questionUISchemaEntry.getValue().getAsString());
            }
            if("ui:widget".equals(questionUISchemaEntry.getKey())){
                questionUISchema .setUiWidget(questionUISchemaEntry.getValue().getAsString());
            }
            if("submitText".equals(questionUISchemaEntry.getKey())){
                questionUISchema .setSubmitText(questionUISchemaEntry.getValue().getAsString());
            }
            if("norender".equals(questionUISchemaEntry.getKey())){
                questionUISchema .setNoRender(questionUISchemaEntry.getValue().getAsBoolean());
            }
            if("props".equals(questionUISchemaEntry.getKey())){
                for(Map.Entry<String, JsonElement> questionUISchemaPropsEntry:questionUISchemaEntry.getValue().getAsJsonObject().entrySet()) {
                    QuestionPageUISchemaProps props  =
                            new QuestionPageUISchemaProps ();
                    if("disabled".equals(questionUISchemaPropsEntry.getKey())){
                        props .setDisabled(questionUISchemaPropsEntry.getValue().getAsBoolean());
                    }if("className".equals(questionUISchemaPropsEntry.getKey())){
                        props .setClassName(questionUISchemaPropsEntry.getValue().getAsString());
                    }
                    LinkedTreeMap<String, QuestionPageUISchemaProps > propsMap =
                            new LinkedTreeMap<String, QuestionPageUISchemaProps >();
                    propsMap.put(questionUISchemaPropsEntry.getKey(), props );
                    questionUISchema .setProps(propsMap);
                }
            }
        }
    }
}
