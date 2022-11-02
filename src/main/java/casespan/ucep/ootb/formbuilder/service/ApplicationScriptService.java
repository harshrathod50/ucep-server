package casespan.ucep.ootb.formbuilder.service;

import casespan.ucep.ootb.formbuilder.collection.ApplicationScript;
import casespan.ucep.ootb.formbuilder.dto.ApplicationKey;

import java.util.Optional;

public interface ApplicationScriptService {
    ApplicationScript saveApplicationScript(ApplicationScript applicationScript);
    Optional<ApplicationScript> readApplication(ApplicationKey applicationKey);
}
