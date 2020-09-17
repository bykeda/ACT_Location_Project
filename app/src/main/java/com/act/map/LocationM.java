package com.act.map;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.SimpleDateFormat;

@Entity(tableName = "Location")
public class LocationM {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="locid")
    private Integer locId;
    @ColumnInfo(name="locName")
    private String locName;
    @ColumnInfo(name="locDesc")
    private String locDesc;
    @ColumnInfo(name="locLong")
    private Double locLong;
    @ColumnInfo(name="locLatt")
    private Double locLatt;
    @ColumnInfo(name="locDateTime")
    private String locDateTime;

    public Integer getLocId() {
        return locId;
    }

    public void setLocId(Integer locId) {
        this.locId = locId;
    }

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

    public Double getLocLong() {
        return locLong;
    }

    public void setLocLong(Double locLong) {
        this.locLong = locLong;
    }

    public Double getLocLatt() {
        return locLatt;
    }

    public void setLocLatt(Double locLatt) {
        this.locLatt = locLatt;
    }

    public String getLocDateTime() {
        return locDateTime;
    }

    public void setLocDateTime(String locDateTime) {
        this.locDateTime = locDateTime;
    }


}
