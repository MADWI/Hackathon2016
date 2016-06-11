
package pl.edu.zut.mad.hackathon2016.model;

import java.util.ArrayList;

public class List {

    private Integer dt;
    private Main main;
    private java.util.List<Weather_> weather = new ArrayList<Weather_>();
    private String dtTxt;

    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public java.util.List<Weather_> getWeather() {
        return weather;
    }

    public void setWeather(java.util.List<Weather_> weather) {
        this.weather = weather;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public String getDtTxt() {
        return dtTxt;
    }

    public void setDtTxt(String dtTxt) {
        this.dtTxt = dtTxt;
    }
}
