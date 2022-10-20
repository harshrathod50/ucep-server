package casespan.ucep.ootb.formbuilder.collection;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

@Data
@Builder
@Document(collation = "application")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Application {
    @Id
    private String applicationId;
    private String applicationDescription;
    private String version;
    private String status;
}
