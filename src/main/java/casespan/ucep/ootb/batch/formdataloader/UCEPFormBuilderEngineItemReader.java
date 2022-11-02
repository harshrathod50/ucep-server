package casespan.ucep.ootb.batch.formdataloader;

import casespan.ucep.ootb.batch.dto.ApplicationJsonFile;
import org.springframework.batch.item.*;
import org.springframework.batch.item.file.ResourceAwareItemReaderItemStream;
import org.springframework.core.io.Resource;
import java.io.File;
import java.io.IOException;

public class UCEPFormBuilderEngineItemReader
        implements ResourceAwareItemReaderItemStream<ApplicationJsonFile> {

    private Resource resource;
    private File file = null;
    private ApplicationJsonFile applicationJsonFile;

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void open(ExecutionContext executionContext)
            throws ItemStreamException {
        try {
            file = resource.getFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ApplicationJsonFile read() throws Exception {
        if(file != null) {
            applicationJsonFile = new ApplicationJsonFile();
            applicationJsonFile.setFile(file);
            file = null;
            return applicationJsonFile;
        }else {
            return null;
        }
    }

    @Override
    public void close() throws ItemStreamException {
        resource = null;
        file = null;
    }

    @Override
    public void update
            (ExecutionContext executionContext)
            throws ItemStreamException {
    }
}
