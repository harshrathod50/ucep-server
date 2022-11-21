package casespan.ucep.ootb.formbuilder.service;

import casespan.ucep.ootb.formbuilder.collection.*;
import casespan.ucep.ootb.formbuilder.dto.*;
import casespan.ucep.ootb.formbuilder.repository.ApplicationScriptRepo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.internal.LinkedTreeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OnlineApplicationServiceImpl implements OnlineApplicationService{
    @Autowired
    private ApplicationScriptRepo applicationScriptRepo;
    @Autowired
    private ScriptExecutionService scriptExecutionService;
    @Autowired
    private ScriptExecutionDataService scriptExecutionDataService;

    public QuestionPageData startApplication(ApplicationKey applicationKey) {
        QuestionPageData questionPageData = new QuestionPageData();
        ApplicationScript applicationScript = null;
        Optional<ApplicationScript> applicationScriptOpt =
                applicationScriptRepo.findById(applicationKey.getApplicationName());
        if(applicationScriptOpt.isPresent()){
            applicationScript = applicationScriptOpt.get();
        }
        if(applicationScript != null) {
            // Script Execution
            if (applicationKey.getScriptExecutionId() == 0) {
                ScriptExecution scriptExecution = new ScriptExecution();
                scriptExecution.setName(applicationKey.getApplicationName());
                scriptExecution =
                        scriptExecutionService.saveScriptExecution(scriptExecution);
                questionPageData.setScriptExecutionId(
                        scriptExecution.getScriptExecutionId());
                // Script Execution Data
                ScriptExecutionData scriptExecutionData = new ScriptExecutionData();
                LinkedTreeMap<String, LinkedTreeMap<String, String>> topLevelMap = new LinkedTreeMap<>();
                scriptExecutionData.setScriptExecutionId(scriptExecution.getScriptExecutionId());
                scriptExecutionData.setQuestionAnswer(topLevelMap);
                scriptExecutionDataService.saveScriptExecutionData(scriptExecutionData);
            }
            // Populate Start Question Page
            LinkedHashMap<String, Integer> questionNameMap =
                    allQuestionPages(applicationScript);
            questionPageData.setCurrentPageName(questionNameMap.entrySet().stream()
                    .findFirst().get().getKey());
            questionPageData.setApplicationName(applicationKey.getApplicationName());
            populateQuestionPageData(applicationScript, questionPageData);

            // All Section names
            questionPageData.setSectionNames(allSectionNames(applicationScript));

            // Set Current Section name
            questionPageData.setCurrentSection(getSectionFromQuestionPage(applicationScript,
                    questionPageData.getCurrentPageName()));

            // Previous Button Indicator
            questionPageData.setPreviousButtonEnabled(false);
        }
        return questionPageData;
    }

    @Override
    public QuestionPageData sectionHandler(SectionPageKey sectionPageKey) {
        return null;
    }

    @Override
    public List<Application> listApplications() {
        return null;
    }

    @Override
    public QuestionPageData previousActionHandler(QuestionPageKey questionPageKey) {
        QuestionPageData questionPageData = new QuestionPageData();
        // Get Next Page
        Optional<ApplicationScript> applicationScriptOpt =
                applicationScriptRepo.findById(questionPageKey.getApplicationName());
        if(applicationScriptOpt.isPresent()){
            ApplicationScript applicationScript = applicationScriptOpt.get();
            questionPageData.setCurrentPageName(
                    questionPageKey.getCurrentQuestionPage());
            questionPageData.setApplicationName(
                    applicationScript.getName());

            String previousQuestionPage = "";
            // Populate Start Question Page
            LinkedHashMap<String, Integer> questionNameMap =
                    allQuestionPages(applicationScript);
            int previousIndex = 0;
            if (questionNameMap.containsKey(questionPageData.getCurrentPageName())) {
                int index = questionNameMap.get(
                        questionPageData.getCurrentPageName());
                previousIndex = index-1;
                if(previousIndex >= 0 && questionNameMap.size()-1 >= previousIndex) {
                    String key = getLinkedHashMapKeyAt(questionNameMap, previousIndex);
                    previousQuestionPage = key;
                }
            }
            questionPageData.setCurrentPageName(previousQuestionPage);
            populateQuestionPageData(applicationScript, questionPageData);

            // All Section names
            questionPageData.setSectionNames(allSectionNames(applicationScript));

            // Previous Button Condition
            if (previousIndex > 0) {
                questionPageData.setPreviousButtonEnabled(true);
            } else {
                questionPageData.setPreviousButtonEnabled(false);
            }

            // Set Current Section name
            questionPageData.setCurrentSection(getSectionFromQuestionPage(applicationScript,
                    questionPageData.getCurrentPageName()));
        }
        questionPageData.setScriptExecutionId(
                questionPageKey.getScriptExecutionId());
        return questionPageData;
    }

    @Override
    public QuestionPageData nextActionHandler(QuestionPageAnswers questionPageAnswers) {
        QuestionPageData questionPageData = new QuestionPageData();
        // Save Answers
        saveQuestionPageAnswers(questionPageAnswers);

        // Get Next Page
        Optional<ApplicationScript> applicationScriptOpt =
                applicationScriptRepo.findById(questionPageAnswers.getApplicationName());
        if(applicationScriptOpt.isPresent()){
            ApplicationScript applicationScript = applicationScriptOpt.get();
            questionPageData.setCurrentPageName(
                    questionPageAnswers.getCurrentPageName());
            questionPageData.setApplicationName(
                    applicationScript.getName());

            String nextQuestionPage = "";
            // Populate Start Question Page
            LinkedHashMap<String, Integer> questionNameMap =
                    allQuestionPages(applicationScript);
            int nextIndex = 0;
            if (questionNameMap.containsKey(questionPageData.getCurrentPageName())) {
                int index = questionNameMap.get(
                        questionPageData.getCurrentPageName());
                nextIndex = index + 1;
                if(nextIndex >= 0 && questionNameMap.size()-1 >= nextIndex) {
                    String key = getLinkedHashMapKeyAt(questionNameMap, nextIndex);
                    nextQuestionPage = key;
                }
            }
            questionPageData.setCurrentPageName(nextQuestionPage);
            populateQuestionPageData(applicationScript, questionPageData);

            // All Section names
            questionPageData.setSectionNames(allSectionNames(applicationScript));

            // Previous Button Condition
            if (nextIndex > 0) {
                questionPageData.setPreviousButtonEnabled(true);
            } else {
                questionPageData.setPreviousButtonEnabled(false);
            }

            // Set Current Section name
            questionPageData.setCurrentSection(getSectionFromQuestionPage(applicationScript,
                    questionPageData.getCurrentPageName()));
        }
        questionPageData.setScriptExecutionId(
                questionPageAnswers.getScriptExecutionId());
        return questionPageData;
    }

    @Override
    public void submitApplication(QuestionPageData questionPageData) {

    }

    private void saveQuestionPageAnswers(QuestionPageAnswers questionPageAnswers){
        Optional<ScriptExecutionData> scriptExecutionDataOpt =
                scriptExecutionDataService.readScriptExecutionData(
                        questionPageAnswers.getScriptExecutionId());
        ScriptExecutionData scriptExecutionData = null;
        LinkedTreeMap<String, LinkedTreeMap<String, String>> topLevelMap = null;
        if(scriptExecutionDataOpt.isPresent()) {
            scriptExecutionData = scriptExecutionDataOpt.get();
            topLevelMap = scriptExecutionData.getQuestionAnswer();
        }else {
            topLevelMap =
                    new LinkedTreeMap<>();
            scriptExecutionData = new ScriptExecutionData();
            scriptExecutionData.setScriptExecutionId(
                    questionPageAnswers.getScriptExecutionId());
        }

        LinkedTreeMap<String, String> questionAnswerMap =
                deserializeScriptExecutionData(questionPageAnswers.getFormAnswers());
        topLevelMap.put(questionPageAnswers.getCurrentPageName(), questionAnswerMap);
        scriptExecutionData.setQuestionAnswer(topLevelMap);
        scriptExecutionDataService.saveScriptExecutionData(
                scriptExecutionData);
    }

    private LinkedHashMap<String, Integer> allQuestionPages(ApplicationScript applicationScript) {
        LinkedHashMap<String, Integer> questionNameMap = new LinkedHashMap<>();
        int index = 0;
        for (Section section : applicationScript.getSections()) {
            LinkedTreeMap<String, QuestionPage> questionPagesMap =
                    section.getQuestionPages();
            for (Map.Entry<String, QuestionPage> entry : questionPagesMap.entrySet()) {
                questionNameMap.put(entry.getKey(), index);
                index = index + 1;
            }
        }
        return questionNameMap;
    }

    private void populateQuestionPageData(ApplicationScript applicationScript, QuestionPageData questionPageData) {
        if(questionPageData.getCurrentPageName().length() > 0) {
            String jsonSchema = "";
            String uiSchema = "";
            for (Section section : applicationScript.getSections()) {
                LinkedTreeMap<String, QuestionPage> questionPagesMap =
                        section.getQuestionPages();
                for (Map.Entry<String, QuestionPage> entry : questionPagesMap.entrySet()) {
                    if (entry.getValue().getJsonSchema().containsKey(
                            questionPageData.getCurrentPageName() + "JSONSchema")) {
                        jsonSchema = deserializeQuestionPage(
                                entry.getValue().getJsonSchema().get(
                                        questionPageData.getCurrentPageName() + "JSONSchema"));
                    }
                    if (entry.getValue().getUiSchema().containsKey(
                            questionPageData.getCurrentPageName() + "UISchema")) {
                        uiSchema = deserializeQuestionUISchema(
                                entry.getValue().getUiSchema().get(
                                        questionPageData.getCurrentPageName() + "UISchema"));
                    }
                }
            }
            questionPageData.setJsonSchema(jsonSchema);
            questionPageData.setUiSchema(uiSchema);
        }
    }

    private String deserializeQuestionPage(QuestionPageJSONSchema auestionPageJSONSchema){
        Gson gson = new GsonBuilder().create();
        String jsonSchema = gson.toJson(auestionPageJSONSchema);
        return jsonSchema;
    }

    private String deserializeQuestionUISchema(QuestionPageUISchema questionPageUISchema){
        Gson gson = new GsonBuilder().create();
        String uiSchema = gson.toJson(questionPageUISchema);
        return uiSchema;
    }

    private LinkedTreeMap<String, String>
        deserializeScriptExecutionData(String pageAnswers) {
        JsonObject jsonObject = new Gson().fromJson(pageAnswers, JsonObject.class);
        LinkedTreeMap<String, String> questionAnswerMap =
                new LinkedTreeMap<>();
        for (Map.Entry<String, JsonElement> questionAnswerEntry :
                jsonObject.getAsJsonObject().entrySet()) {
            questionAnswerMap.put(questionAnswerEntry.getKey(),
                    questionAnswerEntry.getValue().getAsString());
        }
        return questionAnswerMap;
    }

    private ArrayList<String> allSectionNames(ApplicationScript applicationScript) {
        ArrayList<String> sectionNameList = new ArrayList<>();
        for (Section section : applicationScript.getSections()) {
            sectionNameList.add(section.getName());
        }
        return sectionNameList;
    }

    private String getSectionFromQuestionPage(
            ApplicationScript applicationScript, String currentPageName) {
        String sectionName = "";
        if (currentPageName.length() > 0) {
            outer: for (Section section : applicationScript.getSections()) {
                LinkedTreeMap<String, QuestionPage> questionPagesMap =
                        section.getQuestionPages();
                for (Map.Entry<String, QuestionPage> entry : questionPagesMap.entrySet()) {
                    if (entry.getKey().equals(currentPageName)) {
                        sectionName = section.getName();
                        break outer;
                    }
                }
            }
        }
        return sectionName;
    }

    private String getLinkedHashMapKeyAt(
            LinkedHashMap<String, Integer> hashMap, int index){
        Map.Entry<String, Integer> entry =
                (Map.Entry<String, Integer>) hashMap.entrySet().toArray()[index];
        return entry.getKey();
    }
}
