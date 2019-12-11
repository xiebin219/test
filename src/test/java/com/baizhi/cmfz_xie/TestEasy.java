package com.baizhi.cmfz_xie;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.cmfz_xie.entity.Student;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=CmfzXieApplication.class)
public class TestEasy {

    @Test
    public void testexport(){

        ArrayList<Student> students = new ArrayList<Student>();
        students.add(new Student("1", "小黄", 23, new Date()));
        students.add(new Student("2", "小刘", 26, new Date()));
        students.add(new Student("3", "小黑", 24, new Date()));
        students.add(new Student("4", "小张", 18, new Date()));

        ExportParams exportParams = new ExportParams("计算机一班学生", "学生版");
        Workbook sheets = ExcelExportUtil.exportExcel(exportParams, Student.class, students);

        try{
            sheets.write(new FileOutputStream(new File("c://daima/TestPoi.xls")));
        }catch (Exception e ){
            e.printStackTrace();
        }
    }

   /* @Test
    public void testinport(){
        ImportParams importParams = new ImportParams();
        importParams.setTitleRows(1);
        importParams.setHeadRows(1);
        //导入 参数：获取要导入的文件
        ExcelImportUtil.importExcel(new FileInputStream(new File("c://Test.xls")),Teacher.class)
    }*/


}
