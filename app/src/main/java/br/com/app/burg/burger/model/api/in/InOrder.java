package br.com.app.burg.burger.model.api.in;

import java.util.Date;
import java.util.List;

public class InOrder {

    private int id;

    private String id_sandwich;

    private List<Integer> extras;

    private Date date;


    public List<Integer> getExtras() {
        return extras;
    }

    public void setExtras(List<Integer> extras) {
        this.extras = extras;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getId_sandwich() {
        return id_sandwich;
    }

    public void setId_sandwich(String id_sandwich) {
        this.id_sandwich = id_sandwich;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
