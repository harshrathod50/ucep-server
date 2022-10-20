package casespan.ucep.ootb.formbuilder.service;

import casespan.ucep.ootb.formbuilder.collection.ScriptExecutionData;
import casespan.ucep.ootb.formbuilder.repository.ScriptExecutionDataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ScriptExecutionDataServiceImpl implements ScriptExecutionDataService{

    @Autowired
    private ScriptExecutionDataRepo scriptExecutionDataRepo;

    @Override
    public Optional<ScriptExecutionData> readScriptExecutionData(long scriptExecutionId) {
        Optional<ScriptExecutionData> scriptExecutionDataOpt =
                scriptExecutionDataRepo.findById(scriptExecutionId);
        return scriptExecutionDataOpt;
    }

    @Override
    public ScriptExecutionData
        saveScriptExecutionData(ScriptExecutionData scriptExecutionData) {
        return scriptExecutionDataRepo.save(scriptExecutionData);
    }
}
