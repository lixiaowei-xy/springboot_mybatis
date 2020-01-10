package com.lxiaow.utils.excelUtil;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder.BorderSide;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

/**
 * @ClassName excelUtils
 * @Description: 导出Excel工具类
 * @Author lxw
 * @Date 2019/12/6
 **/
@Component
public class ExcelUtils {

    
    /**
     * @MethodName: exportExcel
     * @Description: 使用浏览器下载
     * @Param: 
     * @Return: 
     * @Author: lxw
     * @Date: 13:56
    **/
    public static void exportExcel(HttpServletResponse response, String fileName, ExcelData data) throws Exception {
        // 告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName + ".xls", "utf-8"));
        exportExcel(data, response.getOutputStream());
    }

    
    /**
     * @MethodName: exportExcel
     * @Description: TODO
     * @Param:
     * @Return: 
     * @Author: lxw
     * @Date: 13:57
    **/
    private static int exportExcel(ExcelData data, OutputStream out) throws Exception {
        XSSFWorkbook wb = new XSSFWorkbook();
        int rowIndex = 0;
        try {
            String sheetName = data.getName();
            if (null == sheetName) {
                sheetName = "Sheet1";
            }
            XSSFSheet sheet = wb.createSheet(sheetName);
            rowIndex = writeExcel(wb, sheet, data);
            wb.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //此处需要关闭 wb 变量
            out.close();
        }
        return rowIndex;
    }


    /**
     * @MethodName: writeExcel
     * @Description: 表显示的表头和数据
     * @Param: XSSFWorkbook wb, XSSFSheet sheet, ExcelData data
     * @Return:
     * @Author: lxw
     * @Date: 13:57
    **/
    private static int writeExcel(XSSFWorkbook wb, XSSFSheet sheet, ExcelData data) {
        int rowIndex = 0;
        rowIndex = writeTitlesToExcel(wb, sheet, data.getTitles());
        rowIndex = writeRowsToExcel(wb, sheet, data.getRows(), rowIndex);
        autoSizeColumns(sheet, data.getTitles().size() + 1);
        return rowIndex;
    }


    /**
     * @MethodName: writeTitlesToExcel
     * @Description: 表头
     * @Param:
     * @Return:
     * @Author: lxw
     * @Date: 13:58
    **/
    private static int writeTitlesToExcel(XSSFWorkbook wb, XSSFSheet sheet, List<String> titles) {
        int rowIndex = 0;
        int colIndex = 0;
        Font titleFont = wb.createFont();
        //设置字体
        titleFont.setFontName("simsun");
        //设置粗体
        titleFont.setBoldweight(Short.MAX_VALUE);
        //设置字号
        titleFont.setFontHeightInPoints((short) 14);
        //设置字体颜色
        titleFont.setColor(IndexedColors.BLUE.index);
        XSSFCellStyle titleStyle = wb.createCellStyle();
        //水平居中
        titleStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        //垂直居中
        titleStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        //设置图案颜色
        titleStyle.setFillForegroundColor(new XSSFColor(new Color(182, 184, 192)));
        //设置图案样式
        titleStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        titleStyle.setFont(titleFont);
        setBorder(titleStyle, BorderStyle.THIN, new XSSFColor(new Color(0, 0, 0)));
        Row titleRow = sheet.createRow(rowIndex);
        titleRow.setHeightInPoints(25);
        colIndex = 0;
        for (String field : titles) {
            Cell cell = titleRow.createCell(colIndex);
            cell.setCellValue(field);
            cell.setCellStyle(titleStyle);
            colIndex++;
        }
        rowIndex++;
        return rowIndex;
    }

    /**
     * @MethodName: setBorder
     * @Description: 设置边框
     * @Param:
     * @Return:
     * @Author: lxw
     * @Date: 13:59
    **/
    private static void setBorder(XSSFCellStyle style, BorderStyle border, XSSFColor xssfColor) {
        style.setBorderTop(border);
        style.setBorderLeft(border);
        style.setBorderRight(border);
        style.setBorderBottom(border);
        style.setBorderColor(BorderSide.TOP, xssfColor);
        style.setBorderColor(BorderSide.LEFT, xssfColor);
        style.setBorderColor(BorderSide.RIGHT, xssfColor);
        style.setBorderColor(BorderSide.BOTTOM, xssfColor);
    }


    /**
     * @MethodName: writeRowsToExcel
     * @Description: 表数据
     * @Param:
     * @Return:
     * @Author: lxw
     * @Date: 13:59
    **/
    private static int writeRowsToExcel(XSSFWorkbook wb, XSSFSheet sheet, List<List<Object>> rows, int rowIndex) {
        int colIndex;
        Font dataFont = wb.createFont();
        dataFont.setFontName("simsun");
        dataFont.setFontHeightInPoints((short) 14);
        dataFont.setColor(IndexedColors.BLACK.index);

        XSSFCellStyle dataStyle = wb.createCellStyle();
        dataStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        dataStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        dataStyle.setFont(dataFont);
        setBorder(dataStyle, BorderStyle.THIN, new XSSFColor(new Color(0, 0, 0)));
        for (List<Object> rowData : rows) {
            Row dataRow = sheet.createRow(rowIndex);
            dataRow.setHeightInPoints(25);
            colIndex = 0;
            for (Object cellData : rowData) {
                Cell cell = dataRow.createCell(colIndex);
                if (cellData != null) {
                    cell.setCellValue(cellData.toString());
                } else {
                    cell.setCellValue("");
                }
                cell.setCellStyle(dataStyle);
                colIndex++;
            }
            rowIndex++;
        }
        return rowIndex;
    }


    /**
     * @MethodName: autoSizeColumns
     * @Description: 自定义列宽和高
     * @Param:
     * @Return:
     * @Author: lxw
     * @Date: 14:00
    **/
    private static void autoSizeColumns(XSSFSheet sheet, int columnNumber) {
        for (int i = 0; i < columnNumber; i++) {
            int orgWidth = sheet.getColumnWidth(i);
            sheet.autoSizeColumn(i, true);
            int newWidth = (int) (sheet.getColumnWidth(i) + 100);
            if (newWidth > orgWidth) {
                sheet.setColumnWidth(i, newWidth);
            } else {
                sheet.setColumnWidth(i, orgWidth);
            }
        }
    }

    public  int generateExcel(ExcelData excelData, String path) throws Exception {
        File f = new File(path);
        FileOutputStream out = new FileOutputStream(f);
        return exportExcel(excelData, out);
    }

}
