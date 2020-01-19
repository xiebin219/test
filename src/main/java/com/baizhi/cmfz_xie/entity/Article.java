package com.baizhi.cmfz_xie.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    private String id;

    private String title;

    private String author;

    private String content;

    private Date crea_date;

    private String guru_id;

}