
package pl.edu.zut.mad.hackathon2016.model;

import android.app.Activity;

import pl.edu.zut.mad.hackathon2016.R;

public class Weather_ {

    private Integer id;
    private String main;
    private String description;
    private String icon;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setWeatherIconFromId(int actualId, Activity activity){
        int id = actualId / 100;
        String icon = "";
        if(actualId == 800){
            icon = activity.getString(R.string.weather_sunny);
        } else {
            switch(id) {
                case 2 : icon = activity.getString(R.string.weather_thunder);
                    break;
                case 3 : icon = activity.getString(R.string.weather_drizzle);
                    break;
                case 7 : icon = activity.getString(R.string.weather_foggy);
                    break;
                case 8 : icon = activity.getString(R.string.weather_cloudy);
                    break;
                case 6 : icon = activity.getString(R.string.weather_snowy);
                    break;
                case 5 : icon = activity.getString(R.string.weather_rainy);
                    break;
            }
        }
        this.icon = icon;
    }
}
