package com.act.map;

public class InfoWindowData {
    public String getLocName() {
        return locName;
    }

    public void setLocName(String locName) {
        this.locName = locName;
    }

    public String getLocDesc() {
        return locDesc;
    }

    public void setLocDesc(String locDesc) {
        this.locDesc = locDesc;
    }

    public String getLocLongLatt() {
        return locLongLatt;
    }

    public void setLocLongLatt(String locLongLatt) {
        this.locLongLatt = locLongLatt;
    }

    public String getLocDateTime() {
        return locDateTime;
    }

    public void setLocDateTime(String locDateTime) {
        this.locDateTime = locDateTime;
    }

    private String locName;
    private String locDesc;
    private String locLongLatt;
    private String locDateTime;

}
