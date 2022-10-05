
package casespan.ucep.ootb.formbuilder.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Section implements Serializable {

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

    @SerializedName("questionPages")
    @Expose
    @Setter
    @Getter
    private List<QuestionPage> questionPages = null;
}
