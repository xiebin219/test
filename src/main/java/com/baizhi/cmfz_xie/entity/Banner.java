package com.baizhi.cmfz_xie.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Banner {

    private String id;

    private String src_img;

    private String description;

    private String status;


    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date upload_time;

}