package casespan.ucep.ootb.mq.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UCEPFormData {
    private String messageId;
    private String formData;
    private Date messageDate;
}
