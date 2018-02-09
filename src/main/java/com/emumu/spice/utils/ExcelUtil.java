package com.emumu.spice.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.ResourceUtils;

public class ExcelUtil {

	public static ArrayList<ArrayList<String>> exportXls(InputStream in) throws IOException {
		HSSFWorkbook wb = new HSSFWorkbook(in);
		HSSFSheet sheet = wb.getSheetAt(0);
		int rows = sheet.getPhysicalNumberOfRows();
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		for (int r = 0; r < rows; r++) {
			HSSFRow row = sheet.getRow(r);
			if (row == null) {
				continue;
			}
			ArrayList<String> rowList = new ArrayList<String>();
			for (int c = 0; c < row.getLastCellNum(); c++) {
				HSSFCell cell = row.getCell(c);
				if (cell != null) {
					switch (cell.getCellType()) {
						case HSSFCell.CELL_TYPE_FORMULA:
							try {
								rowList.add(cell.getStringCellValue());
							}catch(Exception e) {
								try {
									rowList.add(cell.getNumericCellValue() + "");
								}catch(Exception e1) {
									rowList.add(cell.getCellFormula() + "");
								}
							}
							break;
						case HSSFCell.CELL_TYPE_NUMERIC:
							rowList.add(cell.getNumericCellValue() + "");
							break;
						case HSSFCell.CELL_TYPE_STRING:
							rowList.add(cell.getStringCellValue());
							break;
						case HSSFCell.CELL_TYPE_BLANK:
							rowList.add("");
							break;
						case HSSFCell.CELL_TYPE_BOOLEAN:
							rowList.add(cell.getBooleanCellValue() + "");
							break;
						case HSSFCell.CELL_TYPE_ERROR:
							rowList.add(cell.getErrorCellValue() + "");
							break;
						default:
							rowList.add("");
					}
				}
			}
			list.add(rowList);
		}
		wb.close();
		return list;
	}
	
	public static ArrayList<ArrayList<String>> exportXlsx(InputStream in) throws IOException, InvalidFormatException {
		OPCPackage pkg = OPCPackage.open(in);
		XSSFWorkbook wb=new XSSFWorkbook(pkg);  
		XSSFSheet sheet = wb.getSheetAt(0);
		int rows = sheet.getPhysicalNumberOfRows();
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		for (int r = 0; r < rows; r++) {
			XSSFRow row = sheet.getRow(r);
			if (row == null) {
				continue;
			}
			ArrayList<String> rowList = new ArrayList<String>();
			for (int c = 0; c < row.getLastCellNum(); c++) {
				XSSFCell cell = row.getCell(c);
				if (cell != null) {
					switch (cell.getCellType()) {
						case XSSFCell.CELL_TYPE_FORMULA:
							try {
								rowList.add(cell.getStringCellValue());
							}catch(Exception e) {
								try {
									rowList.add(cell.getNumericCellValue() + "");
								}catch(Exception e1) {
									rowList.add(cell.getCellFormula() + "");
								}
							}
							break;
						case XSSFCell.CELL_TYPE_NUMERIC:
							rowList.add(cell.getNumericCellValue() + "");
							break;
						case XSSFCell.CELL_TYPE_STRING:
							rowList.add(cell.getStringCellValue());
							break;
						case XSSFCell.CELL_TYPE_BLANK:
							rowList.add("");
							break;
						case XSSFCell.CELL_TYPE_BOOLEAN:
							rowList.add(cell.getBooleanCellValue() + "");
							break;
						case XSSFCell.CELL_TYPE_ERROR:
							rowList.add(cell.getErrorCellValue() + "");
							break;
						default:
							rowList.add("");
					}
				}
			}
			list.add(rowList);
		}
		wb.close();
		return list;
	}
	
	public static ArrayList<ArrayList<String>> exportExcel(InputStream in, String type) throws IOException, InvalidFormatException {
		if("xls".equals(type)) {
			return ExcelUtil.exportXls(in);
		}else if("xlsx".equals(type)) {
			return ExcelUtil.exportXlsx(in);
		}else {
			return null;
		}
	}

	public static void main(String[] args) throws FileNotFoundException, IOException, InvalidFormatException {
//		File file = new File(ResourceUtils.getURL("classpath:111.xls").getFile());
//		ExcelUtil.exportExcel(new FileInputStream(file), "xls");
//		String fileName = "31223 香茅油香精";
		
		
//		System.out.println("======"+fileName.substring(0, fileName.indexOf(" "))+"==="+fileName.substring(fileName.indexOf(" ")+1, fileName.length()));
//		File file1 = new File(ResourceUtils.getURL("classpath:52020.xls").getFile());
//		ExcelUtil.exportExcel(new FileInputStream(file1), "xls");
		
	}

}

