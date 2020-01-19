package com.baizhi.cmfz_xie.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Albu {
    private String id;

    private String title;

    private String cover_img;

    private Double score;

    private String author;

    private String broadcast;

    private Integer count;

    private String content;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date pub_date;

}