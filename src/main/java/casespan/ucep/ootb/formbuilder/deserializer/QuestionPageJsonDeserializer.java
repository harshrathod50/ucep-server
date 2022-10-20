package casespan.ucep.ootb.formbuilder.deserializer;

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
                    if("array".equals(questionEntry.getKey())){
                        LinkedTreeMap<String, QuestionItems> itemsTreeMap =
                                new LinkedTreeMap<>();
                        QuestionItems questionItems = new QuestionItems();
                        for(Map.Entry<String, JsonElement> itemsEntry:questionEntry.getValue().getAsJsonObject()
                                .get("items").getAsJsonObject().entrySet()){
                            if("type".equals(itemsEntry.getKey())){
                                questionItems.setType(itemsEntry.getValue().getAsString());
                            }
                            if("enum".equals(itemsEntry.getKey())){
                                String[] enumArr = itemsEntry.getValue().getAsString().split(
                                        ",");
                                questionItems.setEnumList(Arrays.asList(enumArr));
                            }
                        }
                        itemsTreeMap.put("items", questionItems);
                        question.setItems(itemsTreeMap);
                    }
                }
                questionLinkedTreeMap.put(propertiesEntry.getKey(),
                        question );
            }
            questionPage.setProperties(questionLinkedTreeMap);
        }
    }
}
