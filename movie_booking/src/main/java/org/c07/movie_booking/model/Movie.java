package org.c07.movie_booking.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String nameMovie;
    private LocalDate releaseDate;
    private String durationMovie;
    private String actor;
    private String director;
    private String studio;
    @Column(columnDefinition = "TEXT")
    private String content;
    private String trailer;
    private String avatar;
    private String poster;
    private boolean isDelete;
    @ManyToOne
    @JoinColumn(name = "status_movie_id")
    private StatusFilm statusFilmId;

    @ManyToOne
    @JoinColumn(name = "kind_of_movie_id")
    private KindOfFilm kindOfFilm;
    @ManyToMany
    @JoinTable(
    name = "movie_kind_of_film",
    joinColumns = @JoinColumn(name = "movie_id"),
    inverseJoinColumns = @JoinColumn(name = "kind_of_film_id")
    )
    private Set<KindOfFilm> kindOfFilms = new HashSet<>();
    public Movie() {
    }

    public Set<KindOfFilm> getKindOfFilms() {
        return kindOfFilms;
    }

    public void setKindOfFilms(Set<KindOfFilm> kindOfFilms) {
        this.kindOfFilms = kindOfFilms;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public StatusFilm getStatusFilmId() {
        return statusFilmId;
    }

    public void setStatusFilmId(StatusFilm statusFilmId) {
        this.statusFilmId = statusFilmId;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameMovie() {
        return nameMovie;
    }

    public void setNameMovie(String nameMovie) {
        this.nameMovie = nameMovie;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
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

    public StatusFilm getMovieStatusId() {
        return statusFilmId;
    }

    public void setMovieStatusId(StatusFilm statusFilmId) {
        this.statusFilmId = statusFilmId;
    }

    public KindOfFilm getKindOfFilm() {
        return kindOfFilm;
    }

    public void setKindOfFilm(KindOfFilm kindOfFilm) {
        this.kindOfFilm = kindOfFilm;
    }
}