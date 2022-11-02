package casespan.ucep.ootb.batch;

import casespan.ucep.ootb.batch.dto.ApplicationJsonFile;
import casespan.ucep.ootb.batch.formdataloader.UCEPFormBuilderEngineItemReader;
import casespan.ucep.ootb.batch.formdataloader.UCEPFormBuilderEngineItemWriter;
import casespan.ucep.ootb.batch.formdataloader.UCEPFormBuilderEngineProcessor;
import casespan.ucep.ootb.formbuilder.collection.ApplicationScript;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Configuration
@EnableBatchProcessing
@Slf4j
public class BatchConfig {
    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean(name = "applicationFormJobLauncher")
    public JobLauncher simpleJobLauncher() throws Exception {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(jobRepository);
        jobLauncher.setTaskExecutor(new SimpleAsyncTaskExecutor());
        jobLauncher.afterPropertiesSet();
        return jobLauncher;
    }
    @Bean(name = "loadApplicationFormJob")
    public Job loadApplicationFormJob(JobCompletionNotificationListener listener,
                                          @Qualifier("processApplicationFormStep") Step processApplicationFormStep) {
        return jobBuilderFactory.get("loadApplicationFormJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(processApplicationFormStep)
                .end()
                .build();
    }

    @Bean(name = "processApplicationFormStep")
    public Step processApplicationFormStep() {
        return stepBuilderFactory.get("applicationFormStep")
                .<ApplicationJsonFile, ApplicationScript>chunk(1)
                .reader(jsonFileReader())
                .processor(formBuilderEngineProcessor())
                .writer(formBuilderEngineItemWriter())
                .build();
    }

    @Bean
    public MultiResourceItemReader<ApplicationJsonFile> jsonFileReader() {
        MultiResourceItemReader<ApplicationJsonFile> itemReader
                = new MultiResourceItemReader<>();
        List<FileSystemResource> fileSystemResources
                = new ArrayList<>();
        try {
            List<File> formsList = listFiles();
            formsList.stream().forEach(x ->
                fileSystemResources.
                        add(new FileSystemResource(x)));
            /*Stream<Path> stream = Files.list
                    (Paths.get("classpath:Application/MainApplication"
                            +"/MainApplication.json"));
            stream.forEach(x -> {
                fileSystemResources.
                        add(new FileSystemResource(x.toFile()));
            });*/
            // My Changes
            /*File file = new ClassPathResource("Application/"
                    +"MainApplication"
                    +"/MainApplication.json").getFile();
            fileSystemResources.
                    add(new FileSystemResource(file));*/
            // Ends Here
            Resource[] resources = {};
            resources = fileSystemResources.toArray(resources);
            itemReader.setResources(resources);
            itemReader.setDelegate(formBuilderEngineReader());
            itemReader.setStrict(Boolean.FALSE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return itemReader;
    }

    @Bean
    @StepScope
    public UCEPFormBuilderEngineItemReader formBuilderEngineReader() {
        return new UCEPFormBuilderEngineItemReader();
    }

    @Bean
    public UCEPFormBuilderEngineProcessor formBuilderEngineProcessor() {
        return new UCEPFormBuilderEngineProcessor();
    }

    @Bean
    public UCEPFormBuilderEngineItemWriter formBuilderEngineItemWriter() {
        return new UCEPFormBuilderEngineItemWriter();
    }

    private List<File> listFiles() throws IOException {
        File f = new ClassPathResource("Application").getFile();
        File[] arr = f.listFiles();
        List<File> formsList = new ArrayList<>();
        for (File file : arr) {
            if (file.isDirectory()) {
                File formFile = new ClassPathResource("Application/"
                        +file.getName()
                        +"/"+file.getName()+".json").getFile();
                formsList.add(formFile);
            }
        }
        return formsList;
    }
}
