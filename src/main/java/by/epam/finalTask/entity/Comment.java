package by.epam.finalTask.entity;

import java.util.Calendar;

public class Comment {

    private int id;
    private int userId;
    private Calendar date;
    private int trackId;
    private String text;

    public Comment(int id, int userId, Calendar date, int trackId, String text) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.trackId = trackId;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public int getTrackId() {
        return trackId;
    }

    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        if (id != comment.id) return false;
        if (userId != comment.userId) return false;
        if (trackId != comment.trackId) return false;
        if (!date.equals(comment.date)) return false;
        return text.equals(comment.text);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userId;
        result = 31 * result + date.hashCode();
        result = 31 * result + trackId;
        result = 31 * result + text.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", userId=" + userId +
                ", date=" + date +
                ", trackId=" + trackId +
                ", text='" + text + '\'' +
                '}';
    }
}
