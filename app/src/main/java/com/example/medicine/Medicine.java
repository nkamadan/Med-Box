package com.example.medicine;

import android.app.PendingIntent;
import android.content.Intent;

import java.util.Date;

public class Medicine {
    String name, color, kacdefa, kapsul;
    int durum,hour,minute, alarm_durum; //0 for ac, 1 for tok.
    long alarm_id;

    public Medicine() {
        name = "";
        color = "";
        kacdefa = "";
        kapsul = "";
        durum = -1;
        hour = 0;
        minute = 0;
        alarm_durum = -1;
        alarm_id = -1;
    }

    public long getAlarm_id() {
        return alarm_id;
    }

    public void setAlarm_id(long alarm_id) {
        this.alarm_id = alarm_id;
    }

    public int getAlarm_durum() {
        return alarm_durum;
    }

    public void setAlarm_durum(int alarm_durum) {
        this.alarm_durum = alarm_durum;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getKacdefa() {
        return kacdefa;
    }

    public void setKacdefa(String kacdefa) {
        this.kacdefa = kacdefa;
    }

    public String getKapsul() {
        return kapsul;
    }

    public void setKapsul(String kapsul) {
        this.kapsul = kapsul;
    }

    public int getDurum() {
        return durum;
    }

    public void setDurum(int durum) {
        this.durum = durum;
    }
}
