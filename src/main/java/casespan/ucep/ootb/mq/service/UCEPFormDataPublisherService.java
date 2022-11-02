package casespan.ucep.ootb.mq.service;

import casespan.ucep.ootb.formbuilder.dto.ApplicationKey;

public interface UCEPFormDataPublisherService {
    public String publishFormData(ApplicationKey applicationKey);
}
