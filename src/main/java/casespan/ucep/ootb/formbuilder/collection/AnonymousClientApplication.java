package casespan.ucep.ootb.formbuilder.collection;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder
@Document(collation = "AnonymousClientApplication")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnonymousClientApplication {
    @Id @Field(name = "jwtToken")
    private String jwtToken;
    private ApplicationScript applicationScript;
}
