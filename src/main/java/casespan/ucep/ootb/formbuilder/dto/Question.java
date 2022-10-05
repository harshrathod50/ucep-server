
package casespan.ucep.ootb.formbuilder.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Question implements Serializable {

    @SerializedName("id")
    @Expose @Setter @Getter
    public String id;
    @SerializedName("entity")
    @Expose @Setter @Getter
    public String entity;
    @SerializedName("title")
    @Expose @Setter @Getter
    public String title;
    @SerializedName("type")
    @Expose @Setter @Getter
    public String type;
}
