package casespan.ucep.ootb.formbuilder.collection;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "database_sequences")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DatabaseSequence {
    @Id
    private String id;
    private long seq;
}
