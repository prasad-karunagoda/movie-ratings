package prasad.movieratings.movies;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Movie {

    @Id
    private String imdbTitleId;

    private String title;
    private String originalTitle;
    private String year;
    private String datePublished;
    private String genre;
    private String duration;
    private String country;
    private String language;
    private String director;
    private String writer;
    private String productionCompany;

    @Lob
    @Column(length = 500)
    private String actors;

    @Lob
    @Column(length = 500)
    private String description;

    private String averageVote;
    private String votes;
    private String budget;
    private String usaGrossIncome;
    private String worlwideGrossIncome;
    private String metascore;
    private String reviewsFromUsers;
    private String reviewsFromCritics;

    public String getImdbTitleId() {
        return imdbTitleId;
    }

    public void setImdbTitleId(String imdbTitleId) {
        this.imdbTitleId = imdbTitleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(String datePublished) {
        this.datePublished = datePublished;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getProductionCompany() {
        return productionCompany;
    }

    public void setProductionCompany(String productionCompany) {
        this.productionCompany = productionCompany;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAverageVote() {
        return averageVote;
    }

    public void setAverageVote(String averageVote) {
        this.averageVote = averageVote;
    }

    public String getVotes() {
        return votes;
    }

    public void setVotes(String votes) {
        this.votes = votes;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getUsaGrossIncome() {
        return usaGrossIncome;
    }

    public void setUsaGrossIncome(String usaGrossIncome) {
        this.usaGrossIncome = usaGrossIncome;
    }

    public String getWorlwideGrossIncome() {
        return worlwideGrossIncome;
    }

    public void setWorlwideGrossIncome(String worlwideGrossIncome) {
        this.worlwideGrossIncome = worlwideGrossIncome;
    }

    public String getMetascore() {
        return metascore;
    }

    public void setMetascore(String metascore) {
        this.metascore = metascore;
    }

    public String getReviewsFromUsers() {
        return reviewsFromUsers;
    }

    public void setReviewsFromUsers(String reviewsFromUsers) {
        this.reviewsFromUsers = reviewsFromUsers;
    }

    public String getReviewsFromCritics() {
        return reviewsFromCritics;
    }

    public void setReviewsFromCritics(String reviewsFromCritics) {
        this.reviewsFromCritics = reviewsFromCritics;
    }
}
