package casespan.ucep.ootb.formbuilder.repository;

import casespan.ucep.ootb.formbuilder.collection.ScriptExecutionData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScriptExecutionDataRepo extends MongoRepository<ScriptExecutionData, Long> {
}
