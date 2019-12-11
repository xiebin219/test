package com.baizhi.cmfz_xie.controller;


import com.baizhi.cmfz_xie.entity.City;
import com.baizhi.cmfz_xie.entity.ProDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

@RestController
@RequestMapping("echarts")
public class EchartsController {

    @RequestMapping("queryByUser")
    public HashMap<String,Object> queryByUser(){

        HashMap<String, Object> map = new HashMap<>();

        map.put("month", Arrays.asList("1月","2月","3月","4月","5月","6月"));

        map.put("boys",Arrays.asList(100, 200, 360, 100, 900, 200));
        map.put("girls",Arrays.asList(100, 200, 360, 100, 900, 200));

        return map;

    }

    @RequestMapping("queryByUserChina")
    public ArrayList<Object> queryByUserChina(){

        ArrayList<Object> lists = new ArrayList<>();

        ArrayList<City> citiesBoy = new ArrayList<>();
        citiesBoy.add(new City("北京","200"));
        citiesBoy.add(new City("天津","300"));
        citiesBoy.add(new City("上海","900"));
        citiesBoy.add(new City("南京","100"));
        citiesBoy.add(new City("广东","500"));

        ArrayList<City> citiesGirl = new ArrayList<>();
        citiesGirl.add(new City("北京","100"));
        citiesGirl.add(new City("天津","900"));
        citiesGirl.add(new City("重庆","200"));
        citiesGirl.add(new City("四川","600"));
        citiesGirl.add(new City("山西","100"));


        ProDTO proDTO1 = new ProDTO("小男孩",citiesBoy);
        ProDTO proDTO2 = new ProDTO("小女孩",citiesGirl);

        lists.add(proDTO1);
        lists.add(proDTO2);

        return lists;
    }

}
