package com.example.week_test.entity;

import java.util.List;

public class HomeEntity {
    public String message;
    public String status;
    public List<Home> result;

    public static class Home {
        public String commodityName;
        public String masterPic;
    }
}
