package org.c07.movie_booking.dto;

import jakarta.validation.constraints.NotBlank;
import org.c07.movie_booking.model.KindOfFilm;
import org.c07.movie_booking.model.StatusFilm;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;

public class MovieDTO implements Validator {

    private Long id;
    @NotBlank(message = "Tên phim không được để trống")
    private String nameMovie;
    @NotBlank(message = "Ngày phát hành không được để trống")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate releaseDate;
    @NotBlank(message = "Thời lượng phim không được bỏ trống")
    private String durationMovie;
    @NotBlank(message = "Diễn viên không được bỏ trống")
    private String actor;
    @NotBlank(message = "Dạo diễn không được bỏ trống ")
    private String director;
    @NotBlank(message = "phòng chiếu không được bỏ trống")
    private String studio;
    @NotBlank(message = "Nội dung không được bỏ trống")
    private String content;
    @NotBlank(message = "Trailer không được bỏ trống")
    private String trailer;
    @NotBlank(message = "Avatar không được bỏ trống")
    private String avatar;
    @NotBlank(message = "Trạng thái phim không được bỏ trống")
    private StatusFilm statusFilm;
    @NotBlank(message = "Loại phim không được bỏ trống")
    private KindOfFilm kindOfFilm;
    @NotBlank(message = "Poster không được bỏ trống")
    private String poster;
    private Boolean isDelete;


    public MovieDTO() {
    }

    public MovieDTO(Long id, String nameMovie, LocalDate releaseDate, String durationMovie, String actor, String director, String studio, String content, String trailer, String avatar, StatusFilm statusFilm, KindOfFilm kindOfFilm, String poster, Boolean isDelete) {
        this.id = id;
        this.nameMovie = nameMovie;
        this.releaseDate = releaseDate;
        this.durationMovie = durationMovie;
        this.actor = actor;
        this.director = director;
        this.studio = studio;
        this.content = content;
        this.trailer = trailer;
        this.avatar = avatar;
        this.statusFilm = statusFilm;
        this.kindOfFilm = kindOfFilm;
        this.poster = poster;
        this.isDelete = isDelete;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public StatusFilm getStatusFilm() {
        return statusFilm;
    }

    public void setStatusFilm(StatusFilm statusFilm) {
        this.statusFilm = statusFilm;
    }

    public KindOfFilm getKindOfFilm() {
        return kindOfFilm;
    }

    public void setKindOfFilm(KindOfFilm kindOfFilm) {
        this.kindOfFilm = kindOfFilm;
    }

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

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}
