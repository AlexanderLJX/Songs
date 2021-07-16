package com.id20048076.songs;

import java.io.Serializable;

public class Song implements Serializable {

    private 	int id;
    private 	String title;
    private String singer;
    private int year;
    private int star;

    public Song( int id, String title, String singer, int year, int star ) {
        this.id = id;
        this.title = title;
        this.singer = singer;
        this.year = year;
        this.star = star;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    @Override
    public String toString() {
        String stars = "";
        for(int i = 0;i<star;i++){
            stars += "*";
        }
        return title + "\n" + singer + " - " + year +"\n"+stars;
    }
}
