package prasad.movieratings.movies;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchProcessingConfig {

    private static final String[] FIELDS = new String[] {"imdbTitleId", "title", "originalTitle", "year",
            "datePublished", "genre", "duration", "country", "language", "director", "writer", "productionCompany",
            "actors", "description", "averageVote", "votes", "budget", "usaGrossIncome", "worlwideGrossIncome",
            "metascore", "reviewsFromUsers", "reviewsFromCritics"};

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public FlatFileItemReader<Movie> reader() {
        FlatFileItemReader<Movie> flatFileItemReader = new FlatFileItemReaderBuilder<Movie>()
                .name("movieItemReader")
                .resource(new ClassPathResource("IMDb_movies.csv"))
                .delimited()
                .names(FIELDS)
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Movie>() {
                    { setTargetType(Movie.class); }
                })
                .build();

        flatFileItemReader.setLinesToSkip(1);
        return flatFileItemReader;
    }

    @Bean
    public JdbcBatchItemWriter<Movie> writer(DataSource dataSource) {
        String insertStatement = "INSERT INTO movie(imdb_title_id, title, original_title, year, date_published, " +
                " genre, duration, country, language, director, writer, production_company, actors, description, " +
                " average_vote, votes, budget, usa_gross_income, worlwide_gross_income, metascore, reviews_from_users, " +
                " reviews_from_critics) " +
                " VALUES(:imdbTitleId, :title, :originalTitle, :year, :datePublished, " +
                " :genre, :duration, :country, :language, :director, :writer, :productionCompany, :actors, :description, " +
                " :averageVote, :votes, :budget, :usaGrossIncome, :worlwideGrossIncome, :metascore, :reviewsFromUsers, " +
                " :reviewsFromCritics)";

        return new JdbcBatchItemWriterBuilder<Movie>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql(insertStatement)
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public Job importMoviesJob(JobCompletionNotificationListener listener, Step step) {
        return jobBuilderFactory.get("importMoviesJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step)
                .end()
                .build();
    }

    @Bean
    public Step step(JdbcBatchItemWriter<Movie> writer) {
        return stepBuilderFactory.get("step")
                .<Movie, Movie> chunk(10)
                .reader(reader())
                .writer(writer)
                .build();
    }
}
