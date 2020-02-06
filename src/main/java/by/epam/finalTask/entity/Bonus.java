package by.epam.finalTask.entity;

import java.util.Calendar;

public class Bonus {

    private int id;
    private String name;
    private int discount;
    private Calendar startDate;
    private Calendar endDate;
    private int userId;

    public Bonus() {
    }

    public Bonus(String name, int discount, Calendar startDate, Calendar endDate, int userId) {
        this.name = name;
        this.discount = discount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.userId = userId;
    }

    public Bonus(int id, String name, int discount, Calendar startDate, Calendar endDate, int userId) {
        this.id = id;
        this.name = name;
        this.discount = discount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bonus bonus = (Bonus) o;

        if (id != bonus.id) return false;
        if (discount != bonus.discount) return false;
        if (userId != bonus.userId) return false;
        if (!name.equals(bonus.name)) return false;
        if (!startDate.equals(bonus.startDate)) return false;
        return endDate.equals(bonus.endDate);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + discount;
        result = 31 * result + startDate.hashCode();
        result = 31 * result + endDate.hashCode();
        result = 31 * result + userId;
        return result;
    }

    @Override
    public String toString() {
        return "Bonus{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", discount=" + discount +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", userId=" + userId +
                '}';
    }
}
