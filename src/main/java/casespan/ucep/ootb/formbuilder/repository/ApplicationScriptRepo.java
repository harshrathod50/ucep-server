package casespan.ucep.ootb.formbuilder.repository;

import casespan.ucep.ootb.formbuilder.collection.ApplicationScript;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationScriptRepo extends MongoRepository<ApplicationScript, String> {
}
