package com.feian.tms.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * EasyExcel 工具类
 * 
 * @author feian
 * @date 2025-01-23
 */
@Slf4j
public class EasyExcelUtil {

    /**
     * 导出Excel到响应流
     *
     * @param response HTTP响应
     * @param fileName 文件名
     * @param sheetName 表格名
     * @param clazz 数据类型
     * @param data 数据列表
     * @param <T> 泛型
     */
    public static <T> void exportExcel(HttpServletResponse response, String fileName, String sheetName, Class<T> clazz, List<T> data) {
        try {
            // 设置响应头
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            
            // 防止中文乱码
            fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");

            // 创建样式策略
            HorizontalCellStyleStrategy horizontalCellStyleStrategy = createCellStyleStrategy();

            // 写入Excel
            EasyExcel.write(response.getOutputStream(), clazz)
                    .sheet(sheetName)
                    .registerWriteHandler(horizontalCellStyleStrategy)
                    .doWrite(data);
                    
        } catch (IOException e) {
            log.error("导出Excel失败: {}", e.getMessage(), e);
            throw new RuntimeException("导出Excel失败", e);
        }
    }

    /**
     * 读取Excel文件
     *
     * @param inputStream 输入流
     * @param clazz 数据类型
     * @param listener 读取监听器
     * @param <T> 泛型
     */
    public static <T> void readExcel(java.io.InputStream inputStream, Class<T> clazz, com.alibaba.excel.read.listener.ReadListener<T> listener) {
        try {
            EasyExcel.read(inputStream, clazz, listener)
                    .sheet()
                    .doRead();
        } catch (Exception e) {
            log.error("读取Excel失败: {}", e.getMessage(), e);
            throw new RuntimeException("读取Excel失败", e);
        }
    }

    /**
     * 创建单元格样式策略
     */
    private static HorizontalCellStyleStrategy createCellStyleStrategy() {
        // 头部样式
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        // 背景色
        headWriteCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        WriteFont headWriteFont = new WriteFont();
        headWriteFont.setFontName("Arial");
        headWriteFont.setFontHeightInPoints((short) 12);
        headWriteFont.setBold(true);
        headWriteCellStyle.setWriteFont(headWriteFont);
        // 水平居中
        headWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);

        // 内容样式
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        WriteFont contentWriteFont = new WriteFont();
        contentWriteFont.setFontName("Arial");
        contentWriteFont.setFontHeightInPoints((short) 11);
        contentWriteCellStyle.setWriteFont(contentWriteFont);
        // 水平居左
        contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.LEFT);

        return new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);
    }
}