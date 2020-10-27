package com.app.admin.controller;

import com.app.admin.dao.RoleMapper;
import com.app.admin.dao.UserMapper;
import com.app.admin.model.Role.Role;
import com.app.admin.utils.JwtTokenUtil;
import com.app.admin.utils.RabbitmqUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.Launcher;


import java.io.*;
import java.net.URL;
import java.util.*;


@RequestMapping("/test")
@RestController
public class TestController extends MenuController implements Runnable{
    @Autowired
    JwtTokenUtil jwt;


    @Autowired
    RoleMapper roleMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    RabbitmqUtil mq;


    @Override
    public void run() {

    }

    @RequestMapping("/ggg")
    public void ggg()
    {
        mq.connect();


        String[] message = {"121323"};
        while (true){
            mq.sendMessage(message);

        }
    }


    static HSSFRow row;
    public static void main(String[] args) throws Exception {
        Map<String, String> wholeSchool = new HashMap<>();
        java.lang.String fileName = "sort.xls";

        File xlsFile = new File(fileName);

        // 工作表
        Workbook workbook = WorkbookFactory.create(xlsFile);

        //我们的需求只需要处理一个表，因此不需要遍历
        Sheet sheet = workbook.getSheetAt(0);
        // 行数
        int rowNumbers = sheet.getLastRowNum() + 1;



        for (int row = 1; row < rowNumbers; row++) {

            Row r = sheet.getRow(row);

            Cell schoolCell = r.getCell(3);
            r.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
            r.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
            String first = r.getCell(0).getStringCellValue();
            String forth = r.getCell(3).getStringCellValue();

            wholeSchool.put(forth, first);


//            if(link == null){
//                System.out.println("no");
//            }else{//获取超链接里面内容
//                String url = link.getAddress();
//                Document document =  Jsoup.parse(new URL(url).openStream(), "GBK", url);
//
//                Elements trs = document.select("table").select("tbody").select("tr");
//                Elements tds = document.select("table").select("tbody").select("tr").select("td");
//
//                for(int i = 3;i<trs.size() -2; i++){
//                    Elements rowData = trs.get(i).select("td");
//                    exRow = spreadsheet.createRow(exRowNum++);
//
//                    String data = rowData.text();
//                    Cell cell = exRow.createCell(0);
//                    cell.setCellValue(first);
//                    cell = exRow.createCell(1);
//                    cell.setCellValue(second);
//                    cell = exRow.createCell(2);
//                    cell.setCellValue(third);
//                    cell = exRow.createCell(3);
//                    cell.setCellValue(forth);
//                    cell = exRow.createCell(4);
//                    cell.setCellValue(data);
//                }
//
//            }
        }
        System.out.println(wholeSchool.toString());
        File newFile = new File("本科提前A第一次填报 专科提前普通理科第一次填报 情况统计(最终统计).xls");

        // 工作表
        Workbook newbook = WorkbookFactory.create(newFile);

        //我们的需求只需要处理一个表，因此不需要遍历
        Sheet newSheet = newbook.getSheetAt(0);
        // 行数
        int newRow = newSheet.getLastRowNum() + 1;
        // 读数据，第二行开始读取


        //创建写入文件
        // 工作表
        HSSFWorkbook exBook = new HSSFWorkbook();
        HSSFSheet newbookSheetAt = (HSSFSheet) exBook.createSheet("Employee Info ");


        HSSFRow exRow;
        int exRowNum = 0;


        //Create row object
        for (int row = 4; row < newRow; row++) {

            Row r = newSheet.getRow(row);

            r.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
            r.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
            r.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
            r.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
            r.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
            r.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
            r.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
            r.getCell(7).setCellType(Cell.CELL_TYPE_STRING);
            r.getCell(8).setCellType(Cell.CELL_TYPE_STRING);


            Hyperlink link = r.getCell(3).getHyperlink();
            if (link == null) {
                System.out.println("no");
            } else {
                String url = link.getAddress();
                System.out.println(url);
                Document document = Jsoup.parse(new URL(url).openStream(), "GBK", url);

                Elements trs = document.select("table").select("tbody").select("tr");

                for (int i = 3; i < trs.size() - 2; i++) {
                    Elements rowData = trs.get(i).select("td");
                    exRow = newbookSheetAt.createRow(exRowNum++);

                    System.out.println("row:" + exRowNum);

                    Cell cell = exRow.createCell(0);
                    cell.setCellValue(r.getCell(0).getStringCellValue());

                    cell = exRow.createCell(1);
                    cell.setCellValue(r.getCell(1).getStringCellValue());

                    cell = exRow.createCell(2);
                    cell.setCellValue(r.getCell(2).getStringCellValue());

                    cell = exRow.createCell(3);
                    cell.setCellValue(r.getCell(3).getStringCellValue());

//                    cell = exRow.createCell(4);
//                    cell.setCellValue(r.getCell(4).getStringCellValue());
//
//
//                    cell = exRow.createCell(5);
//                    cell.setCellValue(r.getCell(5).getStringCellValue());
//
//
//                    cell = exRow.createCell(6);
//                    cell.setCellValue(r.getCell(6).getStringCellValue());
//
//
//                    cell = exRow.createCell(7);
//                    cell.setCellValue(r.getCell(7).getStringCellValue());

                    String data = rowData.text();
                    cell = exRow.createCell(4);
                    cell.setCellValue(data);
                    System.out.println(data);
                }


            }

        }

        //Write the workbook in file system
        FileOutputStream out = new FileOutputStream(
                new File("本科提前A第一次填报 专科提前普通理科第一次填报 情况统计(最终统计)1.xls"));
        exBook.write(out);
        out.close();
        System.out.println("Writesheet.xls written successfully");

    }



    @RequestMapping("/test")
    public List<Role> test()
    {
        System.out.println(userMapper.getById(4).toString());
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        List<Integer> collect = new ArrayList<>();

        list1.add(1);
        list1.add(3);
        list1.add(5);
        list1.add(7);

        list2.add(1);
        list2.add(3);
        list2.add(5);
        list2.add(7);


        //交集
        collect.clear();
        collect.addAll(list1);
        collect.retainAll(list2);
        System.out.println("交集"+ collect.toString());
        //并集
        collect.clear();
        collect.addAll(list1);
        collect.addAll(list2);
        System.out.println("并集"+ collect.toString());
        //补集
        collect.clear();
        collect.addAll(list1);
        collect.removeAll(list2);
        System.out.println("1补集合"+collect.toString());

        collect.clear();
        collect.addAll(list2);
        collect.removeAll(list1);
        System.out.println("2补集合"+collect.toString());

        return roleMapper.getAllWithFunc();
    }
}
