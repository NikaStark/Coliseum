package com.coliseum.model.entities;

import com.coliseum.model.entities.enums.SessionStatus;
import com.fasterxml.uuid.Generators;

import java.util.Date;
import java.util.UUID;

public class Session implements Identified<UUID> {

    public static final String TABLE_NAME = "sessions";
    public static final String ID_COLUMN = "id";
    public static final String MOVIE_COLUMN = "id_movie";
    public static final String HALL_COLUMN = "id_hall";
    public static final String START_TIME_COLUMN = "start_time";
    public static final String PRICE_COLUMN = "price";
    public static final String STATUS_COLUMN = "status";
    public static final String COUNT = "count";

    private UUID id;
    private Movie movie;
    private Hall hall;
    private Date startTime;
    private Float price;
    private SessionStatus status;

    public Session() {
    }

    public Session(Movie movie, Hall hall, Date startTime, Float price, SessionStatus status) {
        this.id = Generators.timeBasedGenerator().generate();
        this.movie = movie;
        this.hall = hall;
        this.startTime = startTime;
        this.price = price;
        this.status = status;
    }

    @Override
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public SessionStatus getStatus() {
        return status;
    }

    public void setStatus(SessionStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Session session = (Session) o;

        if (!id.equals(session.id)) return false;
        if (!movie.equals(session.movie)) return false;
        if (!hall.equals(session.hall)) return false;
        if (!startTime.equals(session.startTime)) return false;
        if (!price.equals(session.price)) return false;
        return status == session.status;

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + movie.hashCode();
        result = 31 * result + hall.hashCode();
        result = 31 * result + startTime.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + status.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", movie=" + movie +
                ", hall=" + hall +
                ", startTime=" + startTime +
                ", price=" + price +
                ", status=" + status +
                '}';
    }

}
