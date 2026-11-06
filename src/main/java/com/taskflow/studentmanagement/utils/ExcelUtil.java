package com.taskflow.studentmanagement.utils;

import org.apache.poi.ss.usermodel.*;

public class ExcelUtil {

    public static void setBorder(CellStyle style) {
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
    }

    public static CellStyle createHeaderStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        font.setColor(IndexedColors.WHITE.getIndex());
        style.setFont(font);
        style.setFillBackgroundColor(IndexedColors.AQUA.getIndex());
        setBorder(style);
        return style;
    }

    public static CellStyle createBodyStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        setBorder(style);
        return null;
    }
}
