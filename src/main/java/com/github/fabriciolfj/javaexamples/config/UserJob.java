package com.github.fabriciolfj.javaexamples.config;

import com.github.fabriciolfj.javaexamples.entity.UserRegistration;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class UserJob {

    private static final String INSERT_REGISTRATION_QUERY = """
            insert into USER_REGISTRATION (FIRST_NAME, LAST_NAME, COMPANY, ADDRESS,CITY,STATE,ZIP,COUNTY,URL,PHONE_NUMBER,FAX)
            values
            (:firstName,:lastName,:company,:address,:city,:state,:zip,:county,:url,:phoneNumber,:fax)""";
    private final JobRepository jobRepository;
    @Value("file:/home/spark/registrations.csv")
    private Resource input;

    public UserJob(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    //minha job
    @Bean(name = "userjob")
    public Job insertIntoDbFromCsvJob(Step step1) {
        var name = "User Registration Import Job";
        var builder = new JobBuilder(name, jobRepository);
        return builder.start(step1).build();
    }

    //descricao das etapas
    @Bean
    public Step step1(ItemReader<UserRegistration> reader,
                      ItemWriter<UserRegistration> writer,
                      PlatformTransactionManager txManager) {
        var name = "User Registration CSV To DB Step";
        var builder = new StepBuilder(name, jobRepository);
        return builder
                .<UserRegistration, UserRegistration>chunk(5, txManager)
                .reader(reader)
                .writer(writer)
                .build();
    }


    //configuracao das linhas, recebendo o separador com os campos e o pojo
    @Bean
    public DefaultLineMapper<UserRegistration> lineMapper(LineTokenizer tokenizer,
                                                          FieldSetMapper<UserRegistration> mapper) {
        var lineMapper = new DefaultLineMapper<UserRegistration>();
        lineMapper.setLineTokenizer(tokenizer);
        lineMapper.setFieldSetMapper(mapper);
        return lineMapper;
    }

    //recebendo a linha mapeada e o arquivo de entrada
    @Bean
    public FlatFileItemReader<UserRegistration> csvFileReader(
            LineMapper<UserRegistration> lineMapper) {
        var itemReader = new FlatFileItemReader<UserRegistration>();
        itemReader.setLineMapper(lineMapper);
        itemReader.setResource(input);
        return itemReader;
    }

    //configuracao do pojo que recebera os campos
    @Bean
    public BeanWrapperFieldSetMapper<UserRegistration> fieldSetMapper() {
        var fieldSetMapper = new BeanWrapperFieldSetMapper<UserRegistration>();
        fieldSetMapper.setTargetType(UserRegistration.class);
        return fieldSetMapper;
    }

    //configuracao do token (separador) e o nome de cada separacao
    @Bean
    public DelimitedLineTokenizer tokenizer() {
        var tokenizer = new DelimitedLineTokenizer();
        tokenizer.setDelimiter(",");
        tokenizer.setNames("firstName", "lastName", "company", "address", "city",
                "state", "zip", "county", "url", "phoneNumber", "fax");
        return tokenizer;
    }

    //configuracao da escrita na base de dados
    @Bean
    public JdbcBatchItemWriter<UserRegistration> jdbcItemWriter(DataSource dataSource) {
        var provider = new BeanPropertyItemSqlParameterSourceProvider<UserRegistration>();
        var itemWriter = new JdbcBatchItemWriter<UserRegistration>();
        itemWriter.setDataSource(dataSource);
        itemWriter.setSql(INSERT_REGISTRATION_QUERY);
        itemWriter.setItemSqlParameterSourceProvider(provider);
        return itemWriter;
    }
}
