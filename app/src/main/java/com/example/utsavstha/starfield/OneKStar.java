package com.example.utsavstha.starfield;

import java.util.List;

/**
 * Created by utsavstha on 2/20/17.
 */

public class OneKStar {

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
         * galX : 0
         * galY : 0
         * galZ : 0
         * dist : 0
         * color : 16774636
         * value : 0
         * label : 0
         */

        private String starName;
        private int id;
        private float galX;
        private float galY;
        private float galZ;
        private float dist;
        private float color;
        private float value;
        private int label;

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

        public float getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }

        public float getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public int getLabel() {
            return label;
        }

        public void setLabel(int label) {
            this.label = label;
        }
    }
}
