package com.acfun.common.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Excel 工具类
 *
 * @author lmay.Zhou
 * @date 2017/10/11 17:33
 * @qq 379839355
 * @email Java_zlm@163.com
 */
public class ExcelUtils {
	/**
	 * 是否是2003的Excel，是则true
	 *
	 * @param filePath 文件路径
	 * @return true | false
	 */
	public static boolean isExcel2003(String filePath) {
		return filePath.matches("^.+\\.(?i)(xls)$");
	}

	/**
	 * 是否是2007的Excel，是则true
	 *
	 * @param filePath 文件路径
	 * @return true | false
	 */
	public static boolean isExcel2007(String filePath) {
		return filePath.matches("^.+\\.(?i)(xlsx)$");
	}

	/**
	 * 读取Excel的所有内容
	 *
	 * @param fileName 文件名
	 * @param inputStream 文件流
	 * @return List<Map<String, Object>>
	 */
	public static List<Map<String, Object>> readExcel(String fileName, InputStream inputStream) {
		List<Map<String, Object>> contents = new ArrayList<>();
		//根据版本选择创建Workbook的方式
		Workbook wb = null;
		try (InputStream is = inputStream) {
			if(ExcelUtils.isExcel2007(fileName)) {
				wb = new XSSFWorkbook(is);
			} else {
				wb = new HSSFWorkbook(is);
			}
			//得到第一个shell
			Sheet sheet = wb.getSheetAt(0);
			//得到Excel的行数
			int totalRows = sheet.getPhysicalNumberOfRows();
			//总列数
			int totalCells = 0;
			//得到Excel的列数(前提是有行数)，从第二行算起
			if (totalRows >= 2 && sheet.getRow(1) != null) {
				// 从表头获取总列数
				totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
			}
			// Keys
			String[] keys = new String[totalCells];
			//循环Excel行数,从第二行开始。标题不入库
			for (int r = 0; r < totalRows; r++) {
				Map<String, Object> content = new HashMap<>();
				Row row = sheet.getRow(r);
				//循环Excel的列
				for (int x = 0; x < totalCells; x++) {
					if(0 == r) {
						Cell title = row.getCell(x);
						keys[x] = title.getStringCellValue();
						continue;
					}
					Cell cell = row.getCell(x);
					if(cell==null){
						content.put(keys[x], cell);
					}else{
						//设置单元格读取为字符串读取
						if(wb instanceof  XSSFWorkbook){
							cell.setCellType(XSSFCell.CELL_TYPE_STRING);
						}
						if(wb instanceof  HSSFWorkbook){
							cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						}
						content.put(keys[x], cell);
					}
				}
				if(0 == r){
					continue;
				}
				contents.add(content);
			}
		} catch (FileNotFoundException e) {
			System.err.println(e);
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println(e);
			e.printStackTrace();
		} finally {
			return contents;
		}
	}

	public static void main(String[] args) throws Exception{
		String fileName = "C:\\Users\\anqing.xie\\Desktop\\房间交易档案导入模板.xlsx";
		File file = new File(fileName);
		InputStream inputStream = new FileInputStream(file);
		List<Map<String, Object>> contents = ExcelUtils.readExcel(fileName, inputStream);
		contents.forEach(map ->{
			System.out.println(map);
		});
	}
}