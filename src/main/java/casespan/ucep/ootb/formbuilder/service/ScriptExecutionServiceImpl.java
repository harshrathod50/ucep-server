package casespan.ucep.ootb.formbuilder.service;

import casespan.ucep.ootb.common.service.SequenceGeneratorService;
import casespan.ucep.ootb.formbuilder.collection.ScriptExecution;
import casespan.ucep.ootb.formbuilder.repository.ScriptExecutionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScriptExecutionServiceImpl implements ScriptExecutionService{
    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    private ScriptExecutionRepo scriptExecutionRepo;

    @Override
    public ScriptExecution saveScriptExecution(ScriptExecution scriptExecution) {
        scriptExecution.setScriptExecutionId(
                sequenceGeneratorService.generateSequence(
                        ScriptExecution.SEQUENCE_NAME));
        return scriptExecutionRepo.save(scriptExecution);
    }
}
