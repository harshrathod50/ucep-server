
package casespan.ucep.ootb.formbuilder.dto;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class QuestionPage implements Serializable {

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

    @SerializedName("handler")
    @Expose
    @Setter
    @Getter
    private String handler;
}
