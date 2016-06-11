package pl.edu.zut.mad.hackathon2016;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.Condition;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.structure.BaseModel;

import java.util.ArrayList;
import java.util.List;

@Table(database = AppDatabase.class)
public class Reservation extends BaseModel {

    @PrimaryKey
    private int id;

    @Column
    private String day;

    private List<Entry> entries = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public List<Entry> getEntries() {
        if (entries.isEmpty()) {
            entries = new Select()
                    .from(Entry.class)
                    .where(Condition.column(Entry_Table.reservationId.getNameAlias()).eq(id))
                    .queryList();
        }

        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }
}
