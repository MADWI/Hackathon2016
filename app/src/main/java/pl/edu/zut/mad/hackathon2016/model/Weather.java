
package pl.edu.zut.mad.hackathon2016.model;

import java.util.ArrayList;

public class Weather {

    private City city;
    private String cod;
    private Double message;
    private Integer cnt;
    private java.util.List<pl.edu.zut.mad.hackathon2016.model.List> list = new ArrayList<pl.edu.zut.mad.hackathon2016.model.List>();

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public Double getMessage() {
        return message;
    }

    public void setMessage(Double message) {
        this.message = message;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public java.util.List<List> getList() {
        return list;
    }

    public void setList(java.util.List<List> list) {
        this.list = list;
    }
}
