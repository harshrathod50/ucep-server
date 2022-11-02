package casespan.ucep.ootb.formbuilder.service;

import casespan.ucep.ootb.formbuilder.collection.*;
import casespan.ucep.ootb.formbuilder.dto.ApplicationKey;
import casespan.ucep.ootb.formbuilder.dto.QuestionPageAnswers;
import casespan.ucep.ootb.formbuilder.dto.QuestionPageData;
import casespan.ucep.ootb.formbuilder.dto.QuestionPageKey;
import casespan.ucep.ootb.formbuilder.repository.ApplicationScriptRepo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.internal.LinkedTreeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
            ArrayList<String> questionList =
                    allQuestionPages(applicationScript);
            questionPageData.setApplicationName(applicationKey.getApplicationName());
            questionPageData.setCurrentPageName(questionList.get(0));
            populateQuestionPageData(applicationScript, questionPageData);
        }
        return questionPageData;
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
            String previousQuestionPage = getPreviousQuestionPage(applicationScript,
                    questionPageData.getCurrentPageName());
            questionPageData.setCurrentPageName(previousQuestionPage);
            populateQuestionPageData(applicationScript, questionPageData);
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
            String nextQuestionPage = getNextQuestionPage(applicationScript,
                    questionPageData.getCurrentPageName());
            questionPageData.setCurrentPageName(nextQuestionPage);
            populateQuestionPageData(applicationScript, questionPageData);
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

    private ArrayList<String> allQuestionPages(ApplicationScript applicationScript) {
        ArrayList<String> questionList = new ArrayList<>();
        for (Section section : applicationScript.getSections()) {
            LinkedTreeMap<String, QuestionPage> questionPagesMap =
                    section.getQuestionPages();
            for (Map.Entry<String, QuestionPage> entry : questionPagesMap.entrySet()) {
                questionList.add(entry.getKey());
            }
        }
        return questionList;
    }
    private String getNextQuestionPage(ApplicationScript applicationScript,
                                     String currentPage){
        String nextQuestionPage = "";
        ArrayList<String> questionList = allQuestionPages(applicationScript);
        int currentPageIndex = -1;
        for(int i=0; i <= questionList.size()-1; i++){
            if(questionList.get(i).equals(currentPage)){
                currentPageIndex = i;
                break;
            }
        }
        if(currentPageIndex != -1 && currentPageIndex <= questionList.size()-2){
            nextQuestionPage = questionList.get(currentPageIndex+1);
        }
        return nextQuestionPage;
    }

    private String getPreviousQuestionPage(ApplicationScript applicationScript,
                                       String currentPage){
        String previousQuestionPage = "";
        ArrayList<String> questionList = allQuestionPages(applicationScript);
        int currentPageIndex = -1;
        for(int i=0; i <= questionList.size()-1; i++){
            if(questionList.get(i).equals(currentPage)){
                currentPageIndex = i;
                break;
            }
        }
        if(currentPageIndex != -1){
            previousQuestionPage = questionList.get(currentPageIndex-1);
        }
        return previousQuestionPage;
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
}
