package casespan.ucep.ootb.formbuilder.collection;

import org.springframework.data.mongodb.core.mapping.Field;

import com.google.gson.annotations.SerializedName;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OneOf {
    @Field("const")
    @SerializedName("const")
    public String constant;

    @Field("title")
    public String title;
}
