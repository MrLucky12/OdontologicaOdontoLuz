package com.zumaran.odontooga.app.util.paginator;

public class PageItem {
    
    private int number;
    private boolean now;

    
    public PageItem(int number, boolean now) {
        this.number = number;
        this.now = now;
    }


    public int getNumber() {
        return number;
    }


    public void setNumber(int number) {
        this.number = number;
    }


    public boolean isNow() {
        return now;
    }


    public void setNow(boolean now) {
        this.now = now;
    }

    
    
}
