package org.c07.movie_booking.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;

@Entity
public class StatusFilm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    public StatusFilm() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    // Constructor từ chuỗi JSON
    public StatusFilm(String jsonString) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            StatusFilm status = mapper.readValue(jsonString, StatusFilm.class);
            this.id = status.getId();
            this.name = status.getName();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}