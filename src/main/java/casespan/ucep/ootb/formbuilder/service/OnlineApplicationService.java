package casespan.ucep.ootb.formbuilder.service;

import casespan.ucep.ootb.formbuilder.collection.Application;
import casespan.ucep.ootb.formbuilder.collection.QuestionPage;
import casespan.ucep.ootb.formbuilder.dto.*;

import java.util.List;

public interface OnlineApplicationService {
    QuestionPageData startApplication(ApplicationKey applicationKey);

    List<Application> listApplications();

    QuestionPageData previousActionHandler(QuestionPageKey questionPageKey);

    QuestionPageData nextActionHandler(QuestionPageAnswers questionPageAnswers);

    void submitApplication(QuestionPageData questionPageData);

    QuestionPageData sectionHandler(SectionPageKey sectionPageKey);
}
