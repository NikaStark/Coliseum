package com.coliseum.model.entities;

import com.coliseum.model.entities.enums.MovieStatus;
import com.fasterxml.uuid.Generators;

import java.util.UUID;

public class Movie implements Identified<UUID> {

    public static final String TABLE_NAME = "movies";
    public static final String ID_COLUMN = "id";
    public static final String TITLE_COLUMN = "title";
    public static final String DURATION_COLUMN = "duration";
    public static final String STATUS_COLUMN = "status";
    public static final String COUNT = "count";

    private UUID id;
    private String title;
    private Integer duration;
    private MovieStatus status;

    public Movie() {
    }

    public Movie(String title, Integer duration, MovieStatus status) {
        this.id = Generators.timeBasedGenerator().generate();
        this.title = title;
        this.duration = duration;
        this.status = status;
    }

    @Override
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public MovieStatus getStatus() {
        return status;
    }

    public void setStatus(MovieStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        if (!id.equals(movie.id)) return false;
        if (!title.equals(movie.title)) return false;
        if (!duration.equals(movie.duration)) return false;
        return status == movie.status;

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + duration.hashCode();
        result = 31 * result + status.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", duration=" + duration +
                ", status=" + status +
                '}';
    }

}
