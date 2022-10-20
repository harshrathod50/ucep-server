package casespan.ucep.ootb.formbuilder.service;

import casespan.ucep.ootb.formbuilder.collection.Application;
import casespan.ucep.ootb.formbuilder.collection.QuestionPage;
import casespan.ucep.ootb.formbuilder.dto.ApplicationKey;
import casespan.ucep.ootb.formbuilder.dto.QuestionPageAnswers;
import casespan.ucep.ootb.formbuilder.dto.QuestionPageData;
import casespan.ucep.ootb.formbuilder.dto.QuestionPageKey;

import java.util.List;

public interface OnlineApplicationService {
    QuestionPageData startApplication(ApplicationKey applicationKey);

    List<Application> listApplications();

    QuestionPageData previousActionHandler(QuestionPageKey questionPageKey);

    QuestionPageData nextActionHandler(QuestionPageAnswers questionPageAnswers);

    void submitApplication(QuestionPageData questionPageData);
}
