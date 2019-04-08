package com.zzy.study.dao;

import com.zzy.study.model.Country;

import java.util.List;

/**
 * Created by root on 19-4-8.
 */
public interface CountryMapper {
    List<Country> selectCountrys();
}
