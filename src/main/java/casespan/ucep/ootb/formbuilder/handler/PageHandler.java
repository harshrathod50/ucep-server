package casespan.ucep.ootb.formbuilder.handler;

import casespan.ucep.ootb.formbuilder.collection.ApplicationScript;

public interface PageHandler {
    boolean isPageToBeDisplayed(ApplicationScript applicationScript,
                                String currentPageName);


}
