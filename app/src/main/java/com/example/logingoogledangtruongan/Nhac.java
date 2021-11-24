package com.example.logingoogledangtruongan;

import java.io.Serializable;

public class Nhac implements Serializable{
    private String love;
    private String tayloy;
    private int imgtayloy;

    public Nhac(String love, String tayloy, int imgtayloy) {
        this.love = love;
        this.tayloy = tayloy;
        this.imgtayloy = imgtayloy;
    }

    public String getLove() {
        return love;
    }

    public void setLove(String love) {
        this.love = love;
    }

    public String getTayloy() {
        return tayloy;
    }

    public void setTayloy(String tayloy) {
        this.tayloy = tayloy;
    }

    public int getImgtayloy() {
        return imgtayloy;
    }

    public void setImgtayloy(int imgtayloy) {
        this.imgtayloy = imgtayloy;
    }
}
