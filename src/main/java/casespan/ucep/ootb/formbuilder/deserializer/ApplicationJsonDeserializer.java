package casespan.ucep.ootb.formbuilder.deserializer;

import casespan.ucep.ootb.formbuilder.dto.Application;
import casespan.ucep.ootb.formbuilder.dto.Question;
import casespan.ucep.ootb.formbuilder.dto.QuestionPage;
import casespan.ucep.ootb.formbuilder.dto.Section;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class ApplicationJsonDeserializer implements JsonDeserializer<Application> {

    @Override
    public Application deserialize(JsonElement jAppElement, Type type, JsonDeserializationContext jdc) throws JsonParseException {
        final JsonObject applicationJsonObject = jAppElement.getAsJsonObject();
        final Application application = new Application();

        //deserializeApplication(application, applicationJsonObject);
        final JsonArray sectionsJsonArrayElement = applicationJsonObject.getAsJsonArray("sections");
        //deserializeSectionArray(application, sectionsJsonArrayElement);
        return application;
    }

    /*private void deserializeApplication(final Application application, final JsonObject applicationJsonObject) {
        final String name = applicationJsonObject.get("name").getAsString();
        application.setName(name);
        final String callbackFunction = applicationJsonObject.get("callbackFunction").getAsString();
        application.setCallbackFunction(callbackFunction);
        final String landingPage = applicationJsonObject.get("landingPage").getAsString();
        application.setLandingPage(landingPage);
        final String submitPage = applicationJsonObject.get("submitPage").getAsString();
        application.setSubmitPage(submitPage);
    }

    private void deserializeSectionArray(final Application application, final JsonArray sectionsJsonArrayElement) {
        if (sectionsJsonArrayElement.isJsonArray()) {
            ArrayList<Section> sectionArrayList = new ArrayList<Section>();
            for (final JsonElement sectionElement : sectionsJsonArrayElement.getAsJsonArray()) {
                final Section section = new Section();
                deserializeSection(section, sectionElement);
                sectionArrayList.add(section);
            }
            application.setSections(sectionArrayList);
        }
    }

    private void deserializeSection(final Section section, final JsonElement sectionElement) {
        final JsonObject sectionJsonObject = sectionElement.getAsJsonObject();
        final String name = sectionJsonObject.get("name").getAsString();
        section.setName(name);
        if(sectionJsonObject.get("callbackFunction") != null) {
            final String callbackFunction = sectionJsonObject.get("callbackFunction").getAsString();
            section.setCallbackFunction(callbackFunction);
        }
        final JsonArray questionPagesJsonArrayElement = sectionJsonObject.getAsJsonArray("questionPages");
        deserializeQuestionPageArray(section, questionPagesJsonArrayElement);
    }

    private void deserializeQuestionPageArray(final Section section, final JsonArray questionPagesJsonArrayElement) {
        if (questionPagesJsonArrayElement.isJsonArray()) {
            ArrayList<QuestionPage> questionPageArrayList = new ArrayList<QuestionPage>();
            for (final JsonElement questionPageElement : questionPagesJsonArrayElement.getAsJsonArray()) {
                final QuestionPage questionPage = new QuestionPage();
                deserializeQuestionPage(questionPage, questionPageElement);
                questionPageArrayList.add(questionPage);
            }
            section.setQuestionPages(questionPageArrayList);
        }
    }

    private void deserializeQuestionPage(final QuestionPage questionPage, final JsonElement questionPageElement) {
        final JsonObject questionPageJsonObject = questionPageElement.getAsJsonObject();

        final String name = questionPageJsonObject.get("name").getAsString();
        questionPage.setName(name);
        final String callbackFunction = questionPageJsonObject.get("callbackFunction").getAsString();
        questionPage.setCallbackFunction(callbackFunction);
        final String title = questionPageJsonObject.get("title").getAsString();
        questionPage.setTitle(title);
        final String description = questionPageJsonObject.get("description").getAsString();
        questionPage.setDescription(description);
        final String validator = questionPageJsonObject.get("validator").getAsString();
        questionPage.setValidator(validator);

        final JsonArray requiredJsonArrayElement = questionPageJsonObject.getAsJsonArray("required");
        if (requiredJsonArrayElement.isJsonArray()) {
            ArrayList<String> requiredList = new ArrayList<String>();
            for (final JsonElement requiredElement : requiredJsonArrayElement.getAsJsonArray()) {
                requiredList.add(requiredElement.getAsJsonObject().getAsString());
            }
            questionPage.setRequired(requiredList);
        }
        final JsonArray questionJsonArrayElement = questionPageJsonObject.getAsJsonArray("required");
        deserializeQuestionArray(questionPage, questionJsonArrayElement);
    }

    private void deserializeQuestionArray(final QuestionPage questionPage, final JsonArray questionJsonArrayElement) {
        if (questionJsonArrayElement.isJsonArray()) {
            ArrayList<Question> questionArrayList = new ArrayList<Question>();
            for (final JsonElement questionElement : questionJsonArrayElement.getAsJsonArray()) {
                final Question question = new Question();
                deserializeQuestion(question, questionElement);
                questionArrayList.add(question);
            }
            questionPage.setQuestions(questionArrayList);
        }
    }

    private void deserializeQuestion(final Question question, final JsonElement questionElement) {
        final JsonObject questionJsonObject = questionElement.getAsJsonObject();
        final String id = questionJsonObject.get("id").getAsString();
        question.setId(id);
        final String entity = questionJsonObject.get("entity").getAsString();
        question.setEntity(entity);
        final String title = questionJsonObject.get("title").getAsString();
        question.setTitle(title);
        final String type = questionJsonObject.get("type").getAsString();
        question.setType(type);
    }*/
}
