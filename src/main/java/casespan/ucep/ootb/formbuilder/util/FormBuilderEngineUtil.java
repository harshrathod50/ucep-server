package casespan.ucep.ootb.formbuilder.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import casespan.ucep.ootb.formbuilder.dto.*;

@Component
public class FormBuilderEngineUtil {

    public QuestionPageJson startApplication(ApplicationKey applicationKey) {
        String questionPagesDir = "Application/mainapplication/pages-uischema";
        QuestionPageKey questionPageKey = new QuestionPageKey();
        questionPageKey.setCurrentQuestionPage("PersonalInfoQuestionPage");
        QuestionPageJson questionPageJson = readQuestionPageData(questionPageKey,
                questionPagesDir);
        return questionPageJson;
    }

    public QuestionPageJson getCurrentPage(QuestionPageKey questionPageKey) {

        mainApplicationReader();
        QuestionPageJson questionPageJson = new QuestionPageJson();
        return questionPageJson;
    }

    public QuestionPageKey submitQuestionPageAnswer(QuestionPageData questionPageData) {
        return null;
    }


    public QuestionPageKey submitApplication(QuestionPageData questionPageData) {
        return null;
    }

    public void mainApplicationReader() {
        Gson gson = new GsonBuilder().create();

        try {

            File file = new ClassPathResource("Application/mainapplication/MainApplication.json").getFile();
            if (file.length() == 0) {
                System.out.println("File is empty ...");
            } else {
                System.out.println("File is not empty ...");
            }
            Reader reader = new FileReader(file);
            Application application = gson.fromJson(reader, Application.class);
            for (Section section : application.getSections()) {
                for (QuestionPage questionPage : section.getQuestionPages()) {
                    System.out.println(questionPage.getName());
                }
            }
            Gson gsonPr =
                    new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();

            //System.out.println(gsonPr.toJson(application));
            String questionPagesDir = "Application/mainapplication/pages-uischema";
            QuestionPageKey questionPageKey = new QuestionPageKey();
            questionPageKey.setCurrentQuestionPage("PersonalInfoQuestionPage");
            readQuestionPageData(questionPageKey,
                    questionPagesDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private QuestionPageJson readQuestionPageData(QuestionPageKey questionPageKey,
                                      String questionPagesDir){
        List<Path> pathList = new ArrayList<Path>();
        QuestionPageJson questionPageJson = new QuestionPageJson();

        try (Stream<Path> fileStream = Files.walk(
                new ClassPathResource(questionPagesDir).getFile().toPath())) {
            pathList = fileStream.map(Path::normalize)
                    .filter(Files::isRegularFile)
                    .filter(path -> path.getFileName().toString().endsWith(".json"))
                    .collect(Collectors.toList());
        }catch (IOException ex) {
            ex.printStackTrace();
        }
        pathList.forEach(jsonFile -> {
            if(jsonFile.getFileName().toString().contains(questionPageKey.getCurrentQuestionPage())){
                System.out.println(jsonFile.toFile().getName());
                if(jsonFile.toFile().getName().equals(
                        questionPageKey.getCurrentQuestionPage()+"JSONSchema.json")){
                    questionPageJson.setJsonSchema(readJsonFile(jsonFile));
                }else if(jsonFile.toFile().getName().equals(
                        questionPageKey.getCurrentQuestionPage()+"UISchema.json")){
                    questionPageJson.setUiSchema(readJsonFile(jsonFile));
                }
            }
        });
        return questionPageJson;
    }

    private String readJsonFile(Path jsonFilePath){
        String jsonFileData = "";
        try {
            Stream<String> lines = Files.lines(jsonFilePath);
            jsonFileData = lines.collect(Collectors.joining("\n"));
            lines.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonFileData;
    }

}
