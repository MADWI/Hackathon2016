package pl.edu.zut.mad.hackathon2016;

import java.util.ArrayList;
import java.util.List;

public class Reservation {

    private String day;
    private List<Entry> entries = new ArrayList<>();

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

}
