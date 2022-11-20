package com.example.studydemo.bean;

public class MainBean {
    private String name;
    private String path;
    // 颜色表(注意: 此处定义颜色使用的是ARGB，带Alpha通道的)
    private int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
