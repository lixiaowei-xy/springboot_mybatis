package com.lxiaow.utils.excelUtil;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ReadExcel
 * @Description: TODO
 * @Author lxw
 * @Date 2020/1/10
 **/
public class ReadExcelUtil {

    private static final String EXCEL_XLS = "xls";
    private static final String EXCEL_XLSX = "xlsx";

    //判断版本获取Wordboook
    private static Workbook getWorkbook(InputStream in, String fileName) throws IOException {
        Workbook wbook = null;
        if (fileName.endsWith(EXCEL_XLS)) {
            wbook = new HSSFWorkbook(in);
        } else if (fileName.endsWith(EXCEL_XLSX)) {
            wbook = new XSSFWorkbook(in);
        }
        return wbook;
    }

    public static void getExcelData( MultipartFile file){
        String fileName = "热点问题.xls";
        List<List<String>> lists = new ArrayList<List<String>>();
        try {
            Workbook workbook = getWorkbook(file.getInputStream(), fileName);
            //获取Sheet的数量
            int sheetCount = workbook.getNumberOfSheets();
            // 第一个Sheet
            Sheet sheet = workbook.getSheetAt(0);
            //表头
            Row rowHead = sheet.getRow(0);
            //总列数
            int columns = rowHead.getPhysicalNumberOfCells();
            //总行数
            int lines = sheet.getPhysicalNumberOfRows();
            //循环获取每行数据
            for (int i = 0; i < lines; i++) {
                //循环获取每列
                List<String> list = new ArrayList<String>();
                for (int j = 0; j < columns; j++) {
                    if (sheet.getRow(i).getCell(j) != null){
                        Object obj = sheet.getRow(i).getCell(j);
                        list.add(String.valueOf(obj));
                    }else {
                        list.add(null);
                    }
                }

                lists.add(list);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(lists.size());

        dataIntoHotQuestion(lists);
    }

    private static void dataIntoHotQuestion(List<List<String>> lists) {
        List<String> dataList = new ArrayList<String>();
        HotQuestion htq = new HotQuestion();
        for(int i = 1; i < lists.size(); i++){
            dataList = lists.get(i);
            for(int j = 0;j<dataList.size(); j++){
                switch(j){
                    case 0:
                        htq.setQuest_province(dataList.get(0));
                        System.out.println(j);
                        break;
                    case 1:
                        htq.setQuest_province_name(dataList.get(1));
                        break;
                    case 2:
                        htq.setQuest_title(dataList.get(2));
                        break;
                    case 3:
                        htq.setQuest_sort(dataList.get(3));
                        break;
                    case 4:
                        htq.setQuest_count(dataList.get(4));
                        break;
                    case 5:
                        htq.setQuest_interaction_date(dataList.get(5));
                        break;
                    case 6:
                        htq.setQuest_proportion(dataList.get(6));
                        break;
                    default:
                        System.out.println("出错");
                        break;
                }
            }
            System.out.println(htq.toString());
        }
    }
}