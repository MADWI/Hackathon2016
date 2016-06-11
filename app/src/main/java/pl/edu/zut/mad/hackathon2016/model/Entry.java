package pl.edu.zut.mad.hackathon2016.model;

import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = AppDatabase.class, allFields = true)
public class Entry extends BaseModel {

    @PrimaryKey
    private int id;

    private String time;
    private boolean isReserved;
    private String timeForRegisteringReservation;
    private int reservationId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }

    public String getTimeForRegisteringReservation() {
        return timeForRegisteringReservation;
    }

    public void setTimeForRegisteringReservation(String timeForRegisteringReservation) {
        this.timeForRegisteringReservation = timeForRegisteringReservation;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }
}