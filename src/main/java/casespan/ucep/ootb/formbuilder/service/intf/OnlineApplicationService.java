package casespan.ucep.ootb.formbuilder.service.intf;
import casespan.ucep.ootb.formbuilder.dto.*;

public interface OnlineApplicationService {
   QuestionPageJson getCurrentPage(QuestionPageKey questionPageKey);

   QuestionPageKey submitQuestionPageAnswer(
           QuestionPageData questionPageData);

   QuestionPageJson startApplication(ApplicationKey applicationKey);

   QuestionPageKey submitApplication(QuestionPageData questionPageData);
}
