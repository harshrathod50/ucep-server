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
@Document(collection = "applicationscript")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApplicationScript {
    @Id @Field("name")
    private String name;
    @Field("title")
    private String title;
    @Field("version")
    private String version;
    @Field("handler")
    private String handler;
    @Field("sections")
    private List<Section> sections = null;
    @Field("landingPage")
    private String landingPage;
    @Field("submitPage")
    private String submitPage;
}
