package com.baizhi.cmfz_xie.service;

import com.baizhi.cmfz_xie.dao.BannerMapper;
import com.baizhi.cmfz_xie.entity.Banner;
import com.baizhi.cmfz_xie.entity.BannerExample;
import com.baizhi.cmfz_xie.util.UUIDUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class BannerServiceImpl implements BannerService {

    @Resource
    BannerMapper bannerMapper;



    //查所有
    public List<Banner> queryAll(Integer page,Integer rows){
        List<Banner> banners = bannerMapper.queryAll(page, rows);
        return banners;
    }
    //查页码
    public Integer queryOne(){
        Integer totalcount = bannerMapper.totalcount();
        return totalcount;
    }
    //添加
    @Override
    public String add(Banner banner) {

        String uuid = UUIDUtil.getUUID();
        banner.setId(uuid);
        banner.setStatus("1");
        banner.setUpload_time(new Date());
        System.out.println(banner+"tttt");
        bannerMapper.insert(banner);


        return uuid;
    }
    //删除
    public void del(Banner banner){
        //因为需要根据id进行删除,因此要获取id
        BannerExample example = new BannerExample();
        example.createCriteria().andIdEqualTo(banner.getId());
        bannerMapper.deleteByExample(example);
    }

    //修改
    @Override
    public void update(Banner banner) {
        //updateByExampleSelective可以不用含主键
        //因为没有图片上传，会给你给为空串，此时设置为空值，就不会帮你进行修改
        if(banner.getSrc_img()==""){
            banner.setSrc_img(null);
        }
        BannerExample example = new BannerExample();
        example.createCriteria().andIdEqualTo(banner.getId());//获取id
        bannerMapper.updateByExampleSelective(banner,example);
    }

    //上传并修改路径
    @Override
    public void bannerUpload(MultipartFile srcImg, String id, HttpServletRequest request) {
        //1.根据相对路径获取绝对路径
        String realPath = request.getServletContext().getRealPath("/upload/photo");

        File file = new File(realPath);
        if(!file.exists()){
            file.mkdirs();
        }

        //2.获取文件名
        String filename = srcImg.getOriginalFilename();
        //从新给图片命名
        String newName = new Date().getTime()+"-"+filename;

        //3.文件上传
        try {
            srcImg.transferTo(new File(realPath,newName));
            //修改轮播图信息
            Banner banner = new Banner();
            banner.setId(id);
            banner.setSrc_img(newName);
            System.out.println(banner);
            //调用修改方法
            //BannerExample example = new BannerExample();
            //System.out.println(example);
            //bannerMapper.updateByPrimaryKeySelective(banner);
            BannerExample example = new BannerExample();
            example.createCriteria().andIdEqualTo(banner.getId());

            bannerMapper.updateByExampleSelective(banner,example);
//            bannerMapper.update(banner);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
