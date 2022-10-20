package casespan.ucep.ootb.formbuilder.util;

import casespan.ucep.ootb.formbuilder.collection.ApplicationScript;
import casespan.ucep.ootb.formbuilder.collection.Section;
import casespan.ucep.ootb.formbuilder.deserializer.ApplicationJsonDeserializer;
import casespan.ucep.ootb.formbuilder.deserializer.QuestionPageJsonDeserializer;
import casespan.ucep.ootb.formbuilder.deserializer.QuestionPageUISchemaDeserializer;
import casespan.ucep.ootb.formbuilder.dto.ApplicationKey;
import casespan.ucep.ootb.formbuilder.dto.QuestionPageData;
import casespan.ucep.ootb.formbuilder.dto.QuestionPageKey;
import casespan.ucep.ootb.formbuilder.service.ApplicationScriptService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import casespan.ucep.ootb.formbuilder.collection.*;

@Component
public class FormBuilderEngineUtil {

    @Autowired
    private ApplicationScriptService applicationScriptService;

    public ApplicationScript startApplication(ApplicationKey applicationKey) {
        if (applicationKey == null || applicationKey.getApplicationName() == null){
            applicationKey = new ApplicationKey();
            applicationKey.setApplicationName("MainApplication");
        }
        ApplicationScript applicationScript =
                loadApplicationSchema(applicationKey);
        return applicationScript;
    }

    public QuestionPage getCurrentPage(QuestionPageKey questionPageKey) {
        QuestionPage questionPageJson = new QuestionPage();
        return questionPageJson;
    }

    public QuestionPageKey submitQuestionPageAnswer(QuestionPageData questionPageData) {
        return null;
    }


    public QuestionPageKey submitApplication(QuestionPageData questionPageData) {
        return null;
    }

    public QuestionPageJSONSchema deserializeQuestionPage(
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

    public ApplicationScript loadApplicationSchema(ApplicationKey applicationKey){
        ApplicationScript applicationScript = applicationReader(applicationKey);
        applicationScript =
                applicationScriptService.saveApplicationScript(applicationScript);
        return applicationScript;
    }
    private ApplicationScript applicationReader(ApplicationKey applicationKey) {
        ApplicationScript applicationScript = null;
        try {
            File file = new ClassPathResource("Application/"
                    +applicationKey.getApplicationName()
                        +"/"+applicationKey.getApplicationName()+".json").getFile();
            if (file.length() == 0) {
                System.out.println("File is empty ...");
            } else {
                System.out.println("File is not empty ...");
            }
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(ApplicationScript .class, new ApplicationJsonDeserializer())
                    .create();
            applicationScript = gson.fromJson(new FileReader(file), ApplicationScript .class);

            for (Section  section : applicationScript.getSections()) {
                LinkedTreeMap<String, QuestionPage >
                        topQuestionPagesMap = section.getQuestionPages();
                for(Map.Entry<String, QuestionPage > questionPageEntry:
                        topQuestionPagesMap.entrySet()){
                        String questionPageJsonFilePath = "Application/"
                                +applicationKey.getApplicationName()+"/pages/"
                                +questionPageEntry.getKey()+"JSONSchema.json";
                        QuestionPageJSONSchema questionPageJSONSchema  =
                                deserializeQuestionPage(questionPageJsonFilePath);
                        LinkedTreeMap<String, QuestionPageJSONSchema > questionJsonPageMap =
                                new LinkedTreeMap<>();
                        questionJsonPageMap.put(questionPageEntry.getKey()+"JSONSchema",
                                questionPageJSONSchema );

                        QuestionPage  questionPage  = new QuestionPage ();
                        questionPage .setJsonSchema(questionJsonPageMap);

                        String schemaPageJsonFilePath = "Application/"
                                +applicationKey.getApplicationName()+"/pages/"
                                +questionPageEntry.getKey()+"UISchema.json";
                        QuestionPageUISchema  questionPageUISchema  =
                                deserializeQuestionUISchemaPage(schemaPageJsonFilePath);
                        LinkedTreeMap<String, QuestionPageUISchema > uiSchemaJsonPageMap =
                                new LinkedTreeMap<>();
                        uiSchemaJsonPageMap.put(questionPageEntry.getKey()+"UISchema",
                                questionPageUISchema );

                        questionPage .setUiSchema(uiSchemaJsonPageMap);

                        questionPageEntry.setValue(questionPage );
                    }
            }
            prettyPrint(applicationScript);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return applicationScript;
    }

    private void prettyPrint(ApplicationScript application){
        Gson gsonPrint = new GsonBuilder().setPrettyPrinting().
                disableHtmlEscaping().create();
        System.out.println(gsonPrint.toJson(application));
    }


}
