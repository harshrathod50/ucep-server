package casespan.ucep.ootb.formbuilder.service.impl;

import casespan.ucep.ootb.formbuilder.dto.*;
import casespan.ucep.ootb.formbuilder.service.intf.OnlineApplicationService;
import casespan.ucep.ootb.formbuilder.util.FormBuilderEngineUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OnlineApplicationServiceImpl implements OnlineApplicationService {

    @Autowired
    private FormBuilderEngineUtil formBuilderEngineUtil;

    @Override
    public QuestionPageJson getCurrentPage(QuestionPageKey questionPageKey) {
        return formBuilderEngineUtil.getCurrentPage(questionPageKey);
    }

    @Override
    public QuestionPageKey submitQuestionPageAnswer(QuestionPageData questionPageData) {
        return formBuilderEngineUtil.submitQuestionPageAnswer(questionPageData);
    }

    @Override
    public QuestionPageJson startApplication(ApplicationKey applicationKey){
        return formBuilderEngineUtil.startApplication(applicationKey);
    }

    @Override
    public QuestionPageKey submitApplication(QuestionPageData questionPageData) {
        return formBuilderEngineUtil.submitApplication(questionPageData);
    }

}
