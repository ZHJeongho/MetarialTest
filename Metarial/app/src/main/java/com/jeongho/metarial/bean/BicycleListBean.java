package com.jeongho.metarial.bean;

import java.util.LinkedList;

/**
 * Created by Jeongho on 2016/9/12.
 */
public class BicycleListBean {

    public String result;
    public LinkedList<BicycleBean> bikes = new LinkedList<>();

    public class BicycleBean{
        private String bikeId;
        private String brand;
        private String price;
        private String bikeType;
        private String model;
        private String pic1;
        private String pic2;
        private String pic3;
        private String roadType;
        private String level;

        public String getBikeId() {
            return bikeId;
        }

        public void setBikeId(String bikeId) {
            this.bikeId = bikeId;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getBikeType() {
            return bikeType;
        }

        public void setBikeType(String bikeType) {
            this.bikeType = bikeType;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getPic1() {
            return pic1;
        }

        public void setPic1(String pic1) {
            this.pic1 = pic1;
        }

        public String getPic2() {
            return pic2;
        }

        public void setPic2(String pic2) {
            this.pic2 = pic2;
        }

        public String getPic3() {
            return pic3;
        }

        public void setPic3(String pic3) {
            this.pic3 = pic3;
        }

        public String getRoadType() {
            return roadType;
        }

        public void setRoadType(String roadType) {
            this.roadType = roadType;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }
    }

}
