package casespan.ucep.ootb.batch.util;

import casespan.ucep.ootb.formbuilder.collection.*;
import casespan.ucep.ootb.formbuilder.deserializer.ApplicationJsonDeserializer;
import casespan.ucep.ootb.formbuilder.deserializer.QuestionPageJsonDeserializer;
import casespan.ucep.ootb.formbuilder.deserializer.QuestionPageUISchemaDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

@Component
public class UCEPFormBuilderEngineUtil {

    public ApplicationScript loadApplicationSchema(File formFile) {
        ApplicationScript applicationScript = null;
        if (formFile != null && formFile.length() != 0) {
            try {
                Gson gson = new GsonBuilder()
                        .registerTypeAdapter(ApplicationScript.class, new ApplicationJsonDeserializer())
                        .create();
                applicationScript = gson.fromJson(new FileReader(formFile), ApplicationScript.class);
                String applicationName =
                        formFile.getName().split(".json")[0];
                for (Section section : applicationScript.getSections()) {
                    LinkedTreeMap<String, QuestionPage>
                            topQuestionPagesMap = section.getQuestionPages();
                    for (Map.Entry<String, QuestionPage> questionPageEntry :
                            topQuestionPagesMap.entrySet()) {

                        String questionPageJsonFilePath = "Application/"
                                + applicationName + "/pages/"
                                + questionPageEntry.getKey() + "JSONSchema.json";
                        QuestionPageJSONSchema questionPageJSONSchema =
                                deserializeQuestionPage(questionPageJsonFilePath);
                        LinkedTreeMap<String, QuestionPageJSONSchema> questionJsonPageMap =
                                new LinkedTreeMap<>();
                        questionJsonPageMap.put(questionPageEntry.getKey() + "JSONSchema",
                                questionPageJSONSchema);

                        QuestionPage questionPage = new QuestionPage();
                        questionPage.setJsonSchema(questionJsonPageMap);

                        String schemaPageJsonFilePath = "Application/"
                                + applicationName + "/pages/"
                                + questionPageEntry.getKey() + "UISchema.json";
                        QuestionPageUISchema questionPageUISchema =
                                deserializeQuestionUISchemaPage(schemaPageJsonFilePath);
                        LinkedTreeMap<String, QuestionPageUISchema> uiSchemaJsonPageMap =
                                new LinkedTreeMap<>();
                        uiSchemaJsonPageMap.put(questionPageEntry.getKey() + "UISchema",
                                questionPageUISchema);

                        questionPage.setUiSchema(uiSchemaJsonPageMap);

                        questionPageEntry.setValue(questionPage);
                    }
                }
                prettyPrint(applicationScript);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return applicationScript;
    }

    private QuestionPageJSONSchema deserializeQuestionPage(
                                         String questionPageJsonFilePath){
        QuestionPageJSONSchema questionPageJSONSchema  =
                new QuestionPageJSONSchema();
        try {
            File file = new ClassPathResource(questionPageJsonFilePath).getFile();
            if (file.length() == 0) {
                System.out.println("File is empty ...");
            } else {
                System.out.println("File is not empty ...");
            }
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(QuestionPageJSONSchema.class, new QuestionPageJsonDeserializer())
                    .create();
            questionPageJSONSchema  = gson.fromJson(new FileReader(file), QuestionPageJSONSchema .class);
        }catch (IOException e) {
            e.printStackTrace();
        }
        return questionPageJSONSchema ;
    }

    private QuestionPageUISchema  deserializeQuestionUISchemaPage(
            String questionPageJsonFilePath){
        QuestionPageUISchema  questionPageUISchema  =
                new QuestionPageUISchema ();
        try {
            File file = new ClassPathResource(questionPageJsonFilePath).getFile();
            if (file.length() == 0) {
                System.out.println("File is empty ...");
            } else {
                System.out.println("File is not empty ...");
            }
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(QuestionPageUISchema .class, new QuestionPageUISchemaDeserializer())
                    .create();
            questionPageUISchema  = gson.fromJson(new FileReader(file), QuestionPageUISchema .class);
        }catch (IOException e) {
            e.printStackTrace();
        }
        return questionPageUISchema ;
    }

    private void prettyPrint(ApplicationScript application){
        Gson gsonPrint = new GsonBuilder().setPrettyPrinting().
                disableHtmlEscaping().create();
        System.out.println(gsonPrint.toJson(application));
    }


}
