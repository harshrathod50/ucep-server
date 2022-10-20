package casespan.ucep.ootb.formbuilder.collection;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.internal.LinkedTreeMap;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "scriptexecutiondata")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScriptExecutionData {
    @Id @Field("scriptexecutionid")
    private long scriptExecutionId;

    private LinkedTreeMap<String, LinkedTreeMap<String, String>> questionAnswer;
}
