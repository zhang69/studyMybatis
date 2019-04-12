package com.zzy.study.model;

public enum Enabled {
    ENABLE(1),DISENBALE(0);

    private Enabled(int values) {
        this.values = values;
    }
    private final int values;

    public int getValues() {
        return values;
    }

}
