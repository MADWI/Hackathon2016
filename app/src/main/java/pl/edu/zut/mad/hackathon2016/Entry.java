package pl.edu.zut.mad.hackathon2016;

public class Entry {

    private String time;
    private Boolean isReserved;
    private String timeForRegisteringReservation;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Boolean getIsReserved() {
        return isReserved;
    }

    public void setIsReserved(Boolean isReserved) {
        this.isReserved = isReserved;
    }

    public String getTimeForRegisteringReservation() {
        return timeForRegisteringReservation;
    }

    public void setTimeForRegisteringReservation(String timeForRegisteringReservation) {
        this.timeForRegisteringReservation = timeForRegisteringReservation;
    }
}