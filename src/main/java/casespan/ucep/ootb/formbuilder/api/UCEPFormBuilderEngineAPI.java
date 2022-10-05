package casespan.ucep.ootb.formbuilder.api;


import casespan.ucep.ootb.formbuilder.dto.*;
import casespan.ucep.ootb.formbuilder.service.intf.OnlineApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/forms")
public class UCEPFormBuilderEngineAPI {

    @Autowired
    private OnlineApplicationService onlineAppService;


    @PostMapping("/startApplication")
    @ResponseStatus(HttpStatus.OK)
    public QuestionPageJson startApplication(ApplicationKey applicationKey) {
        return onlineAppService.startApplication(applicationKey);
    }

    @GetMapping("/currentPage")
    @ResponseStatus(HttpStatus.OK)
    public QuestionPageJson getQuestionPage(QuestionPageKey questionPageKey) {
        return onlineAppService.getCurrentPage(questionPageKey);
    }

    @PutMapping("/submitForm")
    @ResponseStatus(HttpStatus.OK)
    public QuestionPageKey submitQuestionPage(QuestionPageData questionPageData) {
        return onlineAppService.submitQuestionPageAnswer(questionPageData);
    }

    @PostMapping("/submitApplication")
    @ResponseStatus(HttpStatus.OK)
    public QuestionPageKey submitApplication(QuestionPageData questionPageData) {
        return onlineAppService.submitApplication(questionPageData);
    }
}
