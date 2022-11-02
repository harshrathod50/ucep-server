package casespan.ucep.ootb.batch.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.batch.item.ResourceAware;
import org.springframework.core.io.Resource;

import java.io.File;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ApplicationJsonFile implements ResourceAware {
    private String inputSrcFileName;
    private Resource resource;
    private File file;

    @Override
    public void setResource(Resource resource) {
        this.resource = resource;
        this.inputSrcFileName = resource.getFilename();
    }
}
