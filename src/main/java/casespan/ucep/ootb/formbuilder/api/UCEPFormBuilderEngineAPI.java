package casespan.ucep.ootb.formbuilder.api;


import casespan.ucep.ootb.formbuilder.collection.Application;
import casespan.ucep.ootb.formbuilder.collection.QuestionPage;
import casespan.ucep.ootb.formbuilder.dto.*;
import casespan.ucep.ootb.formbuilder.service.OnlineApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/forms")
@CrossOrigin("*")
public class UCEPFormBuilderEngineAPI {

    @Autowired
    private OnlineApplicationService onlineAppService;


    @PostMapping (value ="/startApplication", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public QuestionPageData startApplication(@RequestBody ApplicationKey applicationKey) {
        applicationKey = new ApplicationKey();
        applicationKey.setApplicationName("MainApplication");
        return onlineAppService.startApplication(applicationKey);
    }

    @GetMapping (value ="/list", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<Application> listApplications() {
        return onlineAppService.listApplications();
    }

    @PostMapping(value ="/nextActionHandler", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public QuestionPageData nextActionHandler(@RequestBody QuestionPageAnswers questionPageAnswers) {
        return onlineAppService.nextActionHandler(questionPageAnswers);
    }

    @PostMapping(value ="/previousActionHandler", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public QuestionPageData previousActionHandler(QuestionPageKey questionPageKey) {
        return onlineAppService.previousActionHandler(questionPageKey);
    }

    @PostMapping(value ="/submitHandler", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public void submitApplication(QuestionPageData questionPageData) {
        onlineAppService.submitApplication(questionPageData);
    }
}
