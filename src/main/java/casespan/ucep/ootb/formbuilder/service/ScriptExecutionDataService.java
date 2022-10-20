package casespan.ucep.ootb.formbuilder.service;

import casespan.ucep.ootb.formbuilder.collection.ScriptExecutionData;

import java.util.Optional;

public interface ScriptExecutionDataService {
    Optional<ScriptExecutionData> readScriptExecutionData(long scriptExecutionId);
    ScriptExecutionData saveScriptExecutionData(ScriptExecutionData scriptExecutionData);
}
