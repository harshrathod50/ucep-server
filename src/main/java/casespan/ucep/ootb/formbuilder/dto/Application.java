
package casespan.ucep.ootb.formbuilder.dto;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Application implements Serializable {

    @SerializedName("name")
    @Expose
    @Setter
    @Getter
    private String name;
    @SerializedName("title")
    @Expose
    @Setter
    @Getter
    private String title;
    @SerializedName("version")
    @Expose
    @Setter
    @Getter
    private String version;
    @SerializedName("handler")
    @Expose
    @Setter
    @Getter
    private String handler;
    @SerializedName("sections")
    @Expose
    @Setter
    @Getter
    private List<Section> sections = null;
    @SerializedName("landingPage")
    @Expose
    @Setter
    @Getter
    private String landingPage;
    @SerializedName("submitPage")
    @Expose
    @Setter
    @Getter
    private String submitPage;
}
