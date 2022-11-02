package casespan.ucep.ootb.batch.formdataloader;

import java.util.List;

import casespan.ucep.ootb.formbuilder.collection.ApplicationScript;
import casespan.ucep.ootb.formbuilder.repository.ApplicationScriptRepo;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;

public class UCEPFormBuilderEngineItemWriter
        implements ItemWriter<ApplicationScript> {

    @Autowired
    private ApplicationScriptRepo applicationScriptRepo;

    @Override
    public void write(List<? extends ApplicationScript> items) throws Exception {
        for (ApplicationScript applicationScript:items){
            loadForm(applicationScript);
            break;
        }
    }

    private void loadForm(ApplicationScript applicationScript) {
        Optional<ApplicationScript> applicationScriptOpt =
                applicationScriptRepo.findById(applicationScript.getName());
        if (!applicationScriptOpt.isPresent()) {
            applicationScriptRepo.save(applicationScript);
        }
    }
}
