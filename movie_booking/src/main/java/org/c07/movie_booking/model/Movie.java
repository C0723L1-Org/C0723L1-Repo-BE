package org.c07.movie_booking.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "movie_name")
    private String nameMovie;
    @Column(name = "release_date")
    private String releaseDate;
    @Column(name = "duration")
    private String durationMovie;
    @Column(name = "actor")
    private String actor;
    @Column(name = "director")
    private String director;
    @Column(name = "studio")
    private String studio;
    @Column(name = "content")
    private String content;
    @Column(name = "trailer")
    private String trailer;
    @Column(name = "avatar")
    private String avatar;
    @ManyToOne
    @JoinColumn(name = "status_movie_id")
    private MovieStatus movieStatusId;
    @ManyToOne
    @JoinColumn(name = "type_movie_id")
    private TypeMovie typeMovieId;
    @OneToMany(mappedBy = "movieId")
    private List<TimeLine> timeLine;
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNameMovie() {
        return nameMovie;
    }

    public void setNameMovie(String nameMovie) {
        this.nameMovie = nameMovie;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDurationMovie() {
        return durationMovie;
    }

    public void setDurationMovie(String durationMovie) {
        this.durationMovie = durationMovie;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
