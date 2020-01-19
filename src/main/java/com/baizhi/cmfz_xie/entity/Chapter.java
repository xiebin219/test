package com.baizhi.cmfz_xie.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chapter {
    private String id;

    private String title;

    private String src;

    private String duration;

    private String size;

    private Date upload_time;

    private String album_id;

}