package casespan.ucep.ootb.batch.formdataloader;

import casespan.ucep.ootb.batch.dto.ApplicationJsonFile;
import casespan.ucep.ootb.batch.util.UCEPFormBuilderEngineUtil;
import casespan.ucep.ootb.formbuilder.collection.ApplicationScript;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

public class UCEPFormBuilderEngineProcessor
        implements ItemProcessor<ApplicationJsonFile, ApplicationScript> {

    @Autowired
    private UCEPFormBuilderEngineUtil ucepFormBuilderEngineUtil;

    @Override
    public ApplicationScript process(ApplicationJsonFile applicationJsonFile) throws Exception {
        ApplicationScript applicationScript = new ApplicationScript();
        applicationScript =
                ucepFormBuilderEngineUtil.loadApplicationSchema(applicationJsonFile.getFile());
        return applicationScript;
    }
}
