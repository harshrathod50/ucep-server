package casespan.ucep.ootb.formbuilder.collection;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "scriptexecution")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScriptExecution {
    @Transient
    public static final String SEQUENCE_NAME = "users_sequence";

    @Id @Field("scriptexecutionid")
    private long scriptExecutionId;

    @Field("name")
    private String name;
}
