package com.coliseum.model.entities;

import com.coliseum.model.entities.enums.TicketStatus;
import com.fasterxml.uuid.Generators;

import java.util.UUID;

public class Ticket implements Identified<UUID> {

    public static final String TABLE_NAME = "tickets";
    public static final String ID_COLUMN = "id";
    public static final String USER_COLUMN = "id_user";
    public static final String SESSION_COLUMN = "id_session";
    public static final String ROW_COLUMN = "row";
    public static final String SEAT_COLUMN = "seat";
    public static final String STATUS_COLUMN = "status";
    public static final String COUNT = "count";

    private UUID id;
    private User user;
    private Session session;
    private Integer row;
    private Integer seat;
    private TicketStatus status;

    public Ticket() {
    }

    public Ticket(User user, Session session, Integer row, Integer seat, TicketStatus status) {
        this.id = Generators.timeBasedGenerator().generate();
        this.user = user;
        this.session = session;
        this.row = row;
        this.seat = seat;
        this.status = status;
    }

    @Override
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getSeat() {
        return seat;
    }

    public void setSeat(Integer seat) {
        this.seat = seat;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ticket ticket = (Ticket) o;

        if (!id.equals(ticket.id)) return false;
        if (!user.equals(ticket.user)) return false;
        if (!session.equals(ticket.session)) return false;
        if (!row.equals(ticket.row)) return false;
        if (!seat.equals(ticket.seat)) return false;
        return status == ticket.status;

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + user.hashCode();
        result = 31 * result + session.hashCode();
        result = 31 * result + row.hashCode();
        result = 31 * result + seat.hashCode();
        result = 31 * result + status.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", user=" + user +
                ", session=" + session +
                ", row=" + row +
                ", seat=" + seat +
                ", status=" + status +
                '}';
    }

}
