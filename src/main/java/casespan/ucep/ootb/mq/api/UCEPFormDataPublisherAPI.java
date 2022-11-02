package casespan.ucep.ootb.mq.api;

import casespan.ucep.ootb.formbuilder.dto.ApplicationKey;
import casespan.ucep.ootb.mq.service.UCEPFormDataPublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messagequeue")
@CrossOrigin("*")
public class UCEPFormDataPublisherAPI {

    @Autowired
    private UCEPFormDataPublisherService ucepFormDataPublisherService;

    @PostMapping("/publishData")
    public String publishFormMessage(@RequestBody ApplicationKey applicationKey){
        ucepFormDataPublisherService.publishFormData(applicationKey);
        return "";
    }
}
