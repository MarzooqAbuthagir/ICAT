package com.maria.pastelhub.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Results {


    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private Data data;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Image {
        String img="";

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }

    public class Data {

        @SerializedName("lesson1")
        @Expose
        private List<String> lesson1 = null;
        @SerializedName("lesson2")
        @Expose
        private List<String> lesson2 = null;
        @SerializedName("lesson3")
        @Expose
        private List<String> lesson3 = null;
        @SerializedName("lesson4")
        @Expose
        private List<String> lesson4 = null;
        @SerializedName("lesson5")
        @Expose
        private List<String> lesson5 = null;
        @SerializedName("lesson6")
        @Expose
        private List<String> lesson6 = null;
        @SerializedName("lesson7")
        @Expose
        private List<String> lesson7 = null;
        @SerializedName("lesson8")
        @Expose
        private List<String> lesson8 = null;


        public List<String> getLesson8() {
            return lesson8;
        }

        public void setLesson8(List<String> lesson8) {
            this.lesson8 = lesson8;
        }

        public List<String> getLesson1() {
            return lesson1;
        }

        public void setLesson1(List<String> lesson1) {
            this.lesson1 = lesson1;
        }

        public List<String> getLesson2() {
            return lesson2;
        }

        public void setLesson2(List<String> lesson2) {
            this.lesson2 = lesson2;
        }

        public List<String> getLesson3() {
            return lesson3;
        }

        public void setLesson3(List<String> lesson3) {
            this.lesson3 = lesson3;
        }

        public List<String> getLesson4() {
            return lesson4;
        }

        public void setLesson4(List<String> lesson4) {
            this.lesson4 = lesson4;
        }

        public List<String> getLesson5() {
            return lesson5;
        }

        public void setLesson5(List<String> lesson5) {
            this.lesson5 = lesson5;
        }

        public List<String> getLesson6() {
            return lesson6;
        }

        public void setLesson6(List<String> lesson6) {
            this.lesson6 = lesson6;
        }

        public List<String> getLesson7() {
            return lesson7;
        }

        public void setLesson7(List<String> lesson7) {
            this.lesson7 = lesson7;
        }
    }
}
