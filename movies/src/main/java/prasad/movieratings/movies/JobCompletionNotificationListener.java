package prasad.movieratings.movies;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCountCallbackHandler;
import org.springframework.stereotype.Component;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            LOGGER.info("Job finished!");
            RowCountCallbackHandler rowCountCallbackHandler = new RowCountCallbackHandler();
            jdbcTemplate.query("SELECT * FROM movie", rowCountCallbackHandler);
            LOGGER.info("Record count: " + rowCountCallbackHandler.getRowCount());

            jdbcTemplate.query("SELECT * FROM movie LIMIT 10",
                    (rs, row) -> rs.getString("imdb_title_id") + " " + rs.getString("title") +
                            " " + rs.getString("original_title") + " " + rs.getString("year") +
                            " " + rs.getString("date_published") + " " + rs.getString("genre") +
                            " " + rs.getString("duration") + " " + rs.getString("country") +
                            " " + rs.getString("language") + " " + rs.getString("director") +
                            " " + rs.getString("writer") + " " + rs.getString("production_company") +
                            " " + rs.getString("actors") + " " + rs.getString("description") +
                            " " + rs.getString("average_vote") + " " + rs.getString("votes") +
                            " " + rs.getString("budget") + " " + rs.getString("usa_gross_income") +
                            " " + rs.getString("worlwide_gross_income") + " " + rs.getString("metascore") +
                            " " + rs.getString("reviews_from_users") + " " + rs.getString("reviews_from_critics"))
                    .forEach(LOGGER::info);
        }
    }
}
