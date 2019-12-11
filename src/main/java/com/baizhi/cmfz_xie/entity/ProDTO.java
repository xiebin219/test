package com.baizhi.cmfz_xie.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProDTO {

    private String sex;
    private ArrayList<City> citys;
}
