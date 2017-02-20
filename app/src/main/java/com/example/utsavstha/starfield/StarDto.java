package com.example.utsavstha.starfield;

import java.util.List;

/**
 * Created by utsavstha on 2/20/17.
 */

public class StarDto {


    private List<MyarrayBean> myarray;

    public List<MyarrayBean> getMyarray() {
        return myarray;
    }

    public void setMyarray(List<MyarrayBean> myarray) {
        this.myarray = myarray;
    }

    public static class MyarrayBean {
        /**
         * starName : Sol
         * id : 101
         * mass : 1.9891E30
         * diameter : 1392000
         * galX : 0
         * galY : 0
         * galZ : 0
         * dist : 0
         * starType : G2(V)
         * temp : 5760
         * color : 16774636
         */

        private String starName;
        private int id;
        private double mass;
        private float diameter;
        private float galX;
        private float galY;
        private float galZ;
        private float dist;
        private String starType;
        private float temp;
        private float color;

        public String getStarName() {
            return starName;
        }

        public void setStarName(String starName) {
            this.starName = starName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public double getMass() {
            return mass;
        }

        public void setMass(double mass) {
            this.mass = mass;
        }

        public float getDiameter() {
            return diameter;
        }

        public void setDiameter(int diameter) {
            this.diameter = diameter;
        }

        public float getGalX() {
            return galX;
        }

        public void setGalX(int galX) {
            this.galX = galX;
        }

        public float getGalY() {
            return galY;
        }

        public void setGalY(int galY) {
            this.galY = galY;
        }

        public float getGalZ() {
            return galZ;
        }

        public void setGalZ(int galZ) {
            this.galZ = galZ;
        }

        public float getDist() {
            return dist;
        }

        public void setDist(int dist) {
            this.dist = dist;
        }

        public String getStarType() {
            return starType;
        }

        public void setStarType(String starType) {
            this.starType = starType;
        }

        public float getTemp() {
            return temp;
        }

        public void setTemp(int temp) {
            this.temp = temp;
        }

        public float getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }
    }
}
