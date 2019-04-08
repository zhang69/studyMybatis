package com.zzy.study.model;

import java.io.Serializable;

/**
 * @AUTHOR zhangzhiyuan
 * @CREATE 2019/4/4 16:28
 */
public class Country{
    private Integer id;
    private String CountryName;
    private String CountryCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountryName() {
        return CountryName;
    }

    public void setCountryName(String countryName) {
        CountryName = countryName;
    }

    public String getCountryCode() {
        return CountryCode;
    }

    public void setCountryCode(String countryCode) {
        CountryCode = countryCode;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", CountryName='" + CountryName + '\'' +
                ", CountryCode='" + CountryCode + '\'' +
                '}';
    }
}
