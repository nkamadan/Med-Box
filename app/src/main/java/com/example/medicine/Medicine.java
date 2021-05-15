package com.example.medicine;

import java.util.Date;

public class Medicine {
    String name, color, kacdefa, kapsul;
    int durum; //0 for ac, 1 for tok.

    public Medicine() {
        name = "";
        color = "";
        kacdefa = "";
        kapsul = "";
        durum = -1;
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", kacdefa='" + kacdefa + '\'' +
                ", kapsul='" + kapsul + '\'' +
                ", durum=" + durum +
                '}';
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
