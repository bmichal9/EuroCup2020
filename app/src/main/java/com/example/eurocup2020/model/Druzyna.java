package com.example.eurocup2020.model;

public class Druzyna {

    private String name;

    private int played;

    private int win;

    private int draw;

    private int lost;

    private int points;

    private int gole;

    public Druzyna(String n, int p, int w, int d, int l, int pts, int g){
        name = n;
        played = p;
        win = w;
        draw = d;
        lost = l;
        points = pts;
        gole=g;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPlayed() {
        return played;
    }

    public void setPlayed(int played) {
        this.played = played;
    }

    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getLost() {
        return lost;
    }

    public void setLost(int lost) {
        this.lost = lost;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getGole() {
        return gole;
    }

    public void setGole(int gole) {
        this.gole = gole;
    }

    public void wygral(){
        played=getPlayed()+1;
        points=getPoints()+3;
        win=getWin()+1;
    }

    public void remis(){
        played=getPlayed()+1;
        points=getPoints()+1;
        draw=getDraw()+1;
    }

    public void przegral(){
        played=getPlayed()+1;
        lost=getLost()+1;
    }
}
