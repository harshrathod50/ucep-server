package casespan.ucep.ootb.mq.service;

import casespan.ucep.ootb.config.RabbitMQConfig;
import casespan.ucep.ootb.formbuilder.collection.*;
import casespan.ucep.ootb.formbuilder.dto.ApplicationKey;
import casespan.ucep.ootb.formbuilder.service.ApplicationScriptService;
import casespan.ucep.ootb.formbuilder.service.ScriptExecutionDataService;
import casespan.ucep.ootb.mq.dto.UCEPFormData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.LinkedTreeMap;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.*;

@Service
public class UCEPFormDataPublisherServiceImpl implements UCEPFormDataPublisherService{

    @Autowired
    private ScriptExecutionDataService scriptExecutionDataService;
    @Autowired
    private ApplicationScriptService applicationScriptService;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public String publishFormData(ApplicationKey applicationKey) {

        //Get Application Script
        Optional<ApplicationScript> applicationScriptOpt =
                applicationScriptService.readApplication(applicationKey);
        ApplicationScript applicationScript = applicationScriptOpt.get();

        //Get Question Answers
        Optional<ScriptExecutionData> scriptExecutionDataOpt =
                scriptExecutionDataService.readScriptExecutionData(
                        applicationKey.getScriptExecutionId());
        LinkedTreeMap<String, LinkedTreeMap<String, String>> questAnsTopLevelMap = null;
        if(scriptExecutionDataOpt.isPresent()) {
            ScriptExecutionData scriptExecutionData = scriptExecutionDataOpt.get();
            questAnsTopLevelMap = scriptExecutionData.getQuestionAnswer();
        }

        // Update Question Answer
        if(questAnsTopLevelMap != null) {
            addAnswerToQuestion(applicationScript, questAnsTopLevelMap);
        }

        //Get JSON from Application Data
        //Gson gson = new GsonBuilder().create();
        Gson gson = new GsonBuilder().setPrettyPrinting().
                disableHtmlEscaping().create();
        String formData = gson.toJson(applicationScript);
        System.out.println(formData);

        UCEPFormData ucepFormData = new UCEPFormData();
        ucepFormData.setMessageId(UUID.randomUUID().toString());
        ucepFormData.setFormData(formData);
        ucepFormData.setMessageDate(new Date());

        // Send Message
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.DATA_MAPPER_EXCHANGE,
                RabbitMQConfig.DATA_MAPPER_ROUTING_KEY,
                ucepFormData);
        return "Message Published";
    }

    private void addAnswerToQuestion(ApplicationScript applicationScript,
                                     LinkedTreeMap<String, LinkedTreeMap<String, String>> answerTopLevelMap) {
        ArrayList<String> questionList = new ArrayList<>();
        for (Section section : applicationScript.getSections()) {
            LinkedTreeMap<String, QuestionPage> questionPagesMap =
                    section.getQuestionPages();
            for (Map.Entry<String, QuestionPage> entryQuestionPage : questionPagesMap.entrySet()) {
                QuestionPage questionPage = entryQuestionPage.getValue();
                LinkedTreeMap<String, QuestionPageJSONSchema> questionPageJSONSchemaMap =
                        questionPage.getJsonSchema();
                for (Map.Entry<String, QuestionPageJSONSchema> questionPageJSONSchemaEntry:
                        questionPageJSONSchemaMap.entrySet()){
                        QuestionPageJSONSchema questionPageJSONSchema =
                                questionPageJSONSchemaEntry.getValue();
                        updateAnswer(questionPageJSONSchema, answerTopLevelMap);
                    }
                }
            }
        }

    private void updateAnswer(QuestionPageJSONSchema questionPageJSONSchema, LinkedTreeMap<String, LinkedTreeMap<String, String>> answerTopLevelMap){
        LinkedTreeMap<String, String> questionAnswerMap =
                answerTopLevelMap.get(questionPageJSONSchema.getName());
        for(Map.Entry<String, Question> questionEntry:
                questionPageJSONSchema.getProperties().entrySet()){
            for(Map.Entry<String, String> questAnsEntry:questionAnswerMap.entrySet()) {
                if(questAnsEntry.getKey().equals(questionEntry.getKey())){
                    Question question = questionEntry.getValue();
                    question.setAnswer(questAnsEntry.getValue());
                    questionEntry.setValue(question);
                    break;
                }
            }
        }
    }
}
