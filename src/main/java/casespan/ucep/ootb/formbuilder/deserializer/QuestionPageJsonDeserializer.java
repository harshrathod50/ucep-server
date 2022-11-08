package casespan.ucep.ootb.formbuilder.deserializer;

import casespan.ucep.ootb.formbuilder.collection.OneOf;
import casespan.ucep.ootb.formbuilder.collection.Question;
import casespan.ucep.ootb.formbuilder.collection.QuestionItems;
import casespan.ucep.ootb.formbuilder.collection.QuestionPageJSONSchema;
import com.google.gson.*;
import com.google.gson.internal.LinkedTreeMap;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class QuestionPageJsonDeserializer implements JsonDeserializer<QuestionPageJSONSchema > {
    @Override
    public QuestionPageJSONSchema deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        QuestionPageJSONSchema  questionPage  = new QuestionPageJSONSchema ();
        deserializeQuestionPage(jsonElement, questionPage );
        return questionPage ;
    }

    private void deserializeQuestionPage(JsonElement jsonElement, QuestionPageJSONSchema  questionPage ) {
        if (jsonElement.getAsJsonObject().get("name") != null) {
            questionPage .setName(
                    jsonElement.getAsJsonObject().get("name").getAsString());
        }
        if (jsonElement.getAsJsonObject().get("title") != null) {
            questionPage .setTitle(
                    jsonElement.getAsJsonObject().get("title").getAsString());
        }
        if (jsonElement.getAsJsonObject().get("description") != null) {
            questionPage .setDescription(
                    jsonElement.getAsJsonObject().get("description").getAsString());
        }
        if (jsonElement.getAsJsonObject().get("handler") != null) {
            questionPage .setHandler(
                    jsonElement.getAsJsonObject().get("handler").getAsString());
        }
        if (jsonElement.getAsJsonObject().get("type") != null) {
            questionPage .setType(
                    jsonElement.getAsJsonObject().get("type").getAsString());
        }
        // Required List
        if (jsonElement.getAsJsonObject().getAsJsonArray("required") != null) {
            JsonArray jsonArray =
                    jsonElement.getAsJsonObject().getAsJsonArray("required");
            List<String> reqPropsList = new ArrayList<String>();
            for (JsonElement jElement : jsonArray) {
                reqPropsList.add(jElement.getAsString());
            }
            questionPage .setRequiredList(reqPropsList);
        }

        deserializeQuestionProperties(jsonElement, questionPage );
    }
    private void deserializeQuestionProperties(JsonElement jsonElement, QuestionPageJSONSchema  questionPage ){
        if (jsonElement.getAsJsonObject().get("properties") != null) {
            LinkedTreeMap<String, Question> questionLinkedTreeMap =
                    new LinkedTreeMap<>();
            for(Map.Entry<String, JsonElement> propertiesEntry :jsonElement.getAsJsonObject()
                    .get("properties").getAsJsonObject().entrySet()){
                Question question = new Question();
                for(Map.Entry<String, JsonElement> questionEntry :
                        propertiesEntry.getValue().getAsJsonObject().entrySet()){
                    if("title".equals(questionEntry.getKey())){
                        question.setTitle(questionEntry.getValue().getAsString());
                    }
                    if("type".equals(questionEntry.getKey())){
                        question.setType(questionEntry.getValue().getAsString());
                    }
                    if("enum".equals(questionEntry.getKey())){
                        List<String> valuesList = new ArrayList<>();
                        for(JsonElement jElm:questionEntry.getValue().getAsJsonArray()){
                            valuesList.add(jElm.getAsString());
                        }
                        question.setEnumList(valuesList);
                    }
                    if ("oneOf".equals(questionEntry.getKey())) {
                        List<OneOf> selectOptions = new ArrayList<>();
                        for (JsonElement jElm: questionEntry.getValue().getAsJsonArray()) {
                            OneOf oneOf = new OneOf();
                            for (Entry<String, JsonElement> oneOfEntry: jElm.getAsJsonObject().entrySet()) {
                                if ("const".equals(oneOfEntry.getKey())) {
                                    oneOf.setConstant(oneOfEntry.getValue().getAsString());
                                }
                                if ("title".equals(oneOfEntry.getKey())) {
                                    oneOf.setTitle(oneOfEntry.getValue().getAsString());
                                }
                            }
                            selectOptions.add(oneOf);
                        }
                        question.setOneOf(selectOptions);
                    }
                }
                questionLinkedTreeMap.put(propertiesEntry.getKey(),
                        question );
            }
            questionPage.setProperties(questionLinkedTreeMap);
        }
    }
}
