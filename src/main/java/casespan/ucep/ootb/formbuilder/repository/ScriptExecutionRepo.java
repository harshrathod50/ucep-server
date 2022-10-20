package casespan.ucep.ootb.formbuilder.repository;

import casespan.ucep.ootb.formbuilder.collection.ScriptExecution;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScriptExecutionRepo extends MongoRepository<ScriptExecution, String> {
}
