package casespan.ucep.ootb.formbuilder.deserializer;

import casespan.ucep.ootb.formbuilder.collection.ApplicationScript;
import casespan.ucep.ootb.formbuilder.collection.QuestionPage;
import casespan.ucep.ootb.formbuilder.collection.Section;
import com.google.gson.*;
import com.google.gson.internal.LinkedTreeMap;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

public class ApplicationJsonDeserializer implements JsonDeserializer<ApplicationScript > {

    @Override
    public ApplicationScript deserialize(JsonElement jAppElement, Type type, JsonDeserializationContext jdc) throws JsonParseException {
        final JsonObject applicationJsonObject = jAppElement.getAsJsonObject();
        final ApplicationScript  application = new ApplicationScript ();
        deserializeApplication(application, applicationJsonObject);
        final JsonArray sectionsJsonArrayElement = applicationJsonObject.getAsJsonArray("sections");
        deserializeSectionArray(application, sectionsJsonArrayElement);
        return application;
    }

    private void deserializeApplication(final ApplicationScript  application, final JsonObject applicationJsonObject) {
        final String name = applicationJsonObject.get("name").getAsString();
        application.setName(name);
        final String title = applicationJsonObject.get("title").getAsString();
        application.setTitle(title);
        final String version = applicationJsonObject.get("version").getAsString();
        application.setVersion(version);
        final String handler = applicationJsonObject.get("handler").getAsString();
        application.setHandler(handler);
        final String landingPage = applicationJsonObject.get("landingPage").getAsString();
        application.setLandingPage(landingPage);
        final String submitPage = applicationJsonObject.get("submitPage").getAsString();
        application.setSubmitPage(submitPage);
    }

    private void deserializeSectionArray(final ApplicationScript  application, final JsonArray sectionsJsonArrayElement) {
        if (sectionsJsonArrayElement.isJsonArray()) {
            ArrayList<Section > sectionArrayList = new ArrayList<Section >();
            for (final JsonElement sectionElement : sectionsJsonArrayElement.getAsJsonArray()) {
                final Section  section = new Section ();
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
        final String title = sectionJsonObject.get("title").getAsString();
        section.setTitle(title);
        final String handler = sectionJsonObject.get("handler").getAsString();
        section.setHandler(handler);
        LinkedTreeMap<String, QuestionPage> questionPagesTreeMap =
                new LinkedTreeMap<>();
        for(Map.Entry<String, JsonElement> questionPagesEntry :sectionElement.getAsJsonObject()
                .get("questionPages").getAsJsonObject().entrySet()){
            QuestionPage  questionPage  = new QuestionPage ();
            questionPagesTreeMap.put(questionPagesEntry.getKey(), questionPage );
        }
        section.setQuestionPages(questionPagesTreeMap);
    }
}
