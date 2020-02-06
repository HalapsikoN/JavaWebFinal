package by.epam.finalTask.entity;

import java.util.Calendar;

public class Credit {

    private int id;
    private double amount;
    private Calendar date;
    private int userId;

    public Credit() {
    }

    public Credit(double amount, Calendar date, int userId) {
        this.amount = amount;
        this.date = date;
        this.userId = userId;
    }

    public Credit(int id, double amount, Calendar date, int userId) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
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

        Credit credit = (Credit) o;

        if (id != credit.id) return false;
        if (Double.compare(credit.amount, amount) != 0) return false;
        if (userId != credit.userId) return false;
        return date.equals(credit.date);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        temp = Double.doubleToLongBits(amount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + date.hashCode();
        result = 31 * result + userId;
        return result;
    }

    @Override
    public String toString() {
        return "Credit{" +
                "id=" + id +
                ", amount=" + amount +
                ", date=" + date +
                ", userId=" + userId +
                '}';
    }
}
