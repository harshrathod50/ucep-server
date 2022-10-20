package casespan.ucep.ootb.formbuilder.handler;

import casespan.ucep.ootb.formbuilder.collection.ApplicationScript;

public class RegularBenefitPageHandler implements PageHandler{
    @Override
    public boolean isPageToBeDisplayed(ApplicationScript applicationScript, String currentPageName) {
        return true;
    }
}
