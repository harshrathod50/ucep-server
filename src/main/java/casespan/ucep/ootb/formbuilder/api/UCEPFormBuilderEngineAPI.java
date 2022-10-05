package casespan.ucep.ootb.formbuilder.api;


import casespan.ucep.ootb.formbuilder.dto.*;
import casespan.ucep.ootb.formbuilder.service.intf.OnlineApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/forms")
@CrossOrigin("*")
public class UCEPFormBuilderEngineAPI {

    @Autowired
    private OnlineApplicationService onlineAppService;


    @PostMapping (value ="/startApplication", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public QuestionPageJson startApplication(ApplicationKey applicationKey) {
        return onlineAppService.startApplication(applicationKey);
    }

    @GetMapping(value ="/currentPage", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public QuestionPageJson getQuestionPage(QuestionPageKey questionPageKey) {
        return onlineAppService.getCurrentPage(questionPageKey);
    }

    @PutMapping(value ="/submitForm", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public QuestionPageKey submitQuestionPage(QuestionPageData questionPageData) {
        return onlineAppService.submitQuestionPageAnswer(questionPageData);
    }

    @PostMapping(value ="/submitApplication", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public QuestionPageKey submitApplication(QuestionPageData questionPageData) {
        return onlineAppService.submitApplication(questionPageData);
    }
}
