package com.baizhi.cmfz_xie;

import com.baizhi.cmfz_xie.entity.Student;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.util.CellRangeAddress;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=CmfzXieApplication.class)
public class TestPoi {

    @Test
    public void test1(){
        //创建一个excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建一个工作薄   参数：工作薄名字(sheet1,shet2....)
        //不指定工作簿名    默认按照 sheet0，sheet1 命名
        HSSFSheet sheet = workbook.createSheet("学生信息");
        //创建一行  参数：行下标(下标从0开始)
        HSSFRow row = sheet.createRow(0);
        //创建一个单元格  参数：单元格下标(下标从0开始)
        HSSFCell cell = row.createCell(0);
        //给单元格设置内容
        cell.setCellValue("这是第一行第一列");

        //导出单元格
        try {
            workbook.write(new FileOutputStream(new File("c://TestPoi.xls")));

            //释放资源
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void  testexports(){
        Student stu1 = new Student("1","小王",17,new Date());
        Student stu2 = new Student("2", "小唐", 18, new Date());
        List<Student> students = Arrays.asList(stu1, stu2);
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("学生信息");
        //设置宽度  参数：列索引，宽度
        sheet.setColumnWidth(13,15*256);
        HSSFRow row = sheet.createRow(0);

        row.createCell(0).setCellValue("学生信息");
        //合并单元格
        CellRangeAddress cellAddresses = new CellRangeAddress(0, 5, 6, 6);

        String[] titles={"Id","名字","年龄","生日"};
        //设置行高
        row.setHeight((short)(20*20));

        for(int i=0;i<titles.length;i++){
            String title=titles[i];
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(titles[i]);

        }
        //处理日期格式
        HSSFDataFormat dataFormat = workbook.createDataFormat();
        short format = dataFormat.getFormat("yyyy-MM-dd");
        //处理样式
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setDataFormat(format);
        //处理文字样式
        HSSFFont font = workbook.createFont();
        font.setBold(true);//加粗
        font.setColor(font.COLOR_RED);//颜色
        font.setFontName("楷体");//设置字体
        font.setFontHeight((short)30);//字体高度

        //处理数据行数据
        for(int i=0;i<students.size();i++){

            HSSFRow row1 = sheet.createRow(i + 1);
            Cell cell = row1.createCell(0);
            cell.setCellValue(students.get(i).getId());

            row1.createCell(1).setCellValue(students.get(i).getName());
            row1.createCell(2).setCellValue(students.get(i).getAge());
            Cell cell1 = row1.createCell(3);
            cell1.setCellStyle(cellStyle);


        }
    }

    @Test
    public void testinport(){
        try {
            //读取工作簿
            HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(new File("c://TestPoi.xls")));
            //获取工作簿
            HSSFSheet sheet = workbook.getSheet("学生信息");
            for(int i=0;i<sheet.getLastRowNum();i++){
                HSSFRow row = sheet.getRow(i + 2);

                HSSFCell cell = row.getCell(0);
                String id = cell.getStringCellValue();
                Student student = new Student();
                student.setId(id);

                row.getCell(1).getStringCellValue();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
