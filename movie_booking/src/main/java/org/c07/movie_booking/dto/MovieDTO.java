package org.c07.movie_booking.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import java.time.LocalDate;
import java.util.List;

public class MovieDTO implements Validator {

    private Long id;
    @NotBlank(message = "Tên phim không được để trống")
    private String nameMovie;
    @NotBlank(message = "Ngày phát hành không được để trống")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate releaseDate;
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
    @NotBlank(message = "Poster không được bỏ trống")
    private String poster;
    private Boolean isDelete = true;
    @NotBlank(message = "Trạng thái phim không được bỏ trống")
    private Long statusFilm;
    @NotBlank(message = "Loại phim không được bỏ trống")
    private List<KindOfFilmDTO> kindOfFilm;




    public MovieDTO() {
    }

    public MovieDTO(Long id, String nameMovie, LocalDate releaseDate, String durationMovie, String actor, String director, String studio, String content, String trailer, String avatar, String poster, Boolean isDelete, Long statusFilm, List<KindOfFilmDTO> kindOfFilm) {
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
        this.poster = poster;
        this.isDelete = isDelete;
        this.statusFilm = statusFilm;
        this.kindOfFilm = kindOfFilm;
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

    public Long getStatusFilm() {
        return statusFilm;
    }

    public void setStatusFilm(Long statusFilm) {
        this.statusFilm = statusFilm;
    }

    public List<KindOfFilmDTO> getKindOfFilm() {
        return kindOfFilm;
    }

    public void setKindOfFilm(List<KindOfFilmDTO> kindOfFilm) {
        this.kindOfFilm = kindOfFilm;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {

    }
}

