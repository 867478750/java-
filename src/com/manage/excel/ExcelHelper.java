package com.manage.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jodd.bean.BeanUtil;
import jodd.datetime.JDateTime;
import jodd.util.StringUtil;

import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


public class ExcelHelper {
	private static ExcelHelper helper = null;
	
	private ExcelHelper() {
		
	}
	
	public static synchronized ExcelHelper getInstanse() {
		if(helper == null) {
			helper = new ExcelHelper();
		}
		return helper;
	}
	
	/**
	 * ��Excel�ļ����뵽list����
	 * @param head	�ļ�ͷ��Ϣ
	 * @param file	���������Դ
	 * @param cls	���浱ǰ���ݵĶ���
	 * @return
	 * @auther <a href="mailto:hubo@feinno.com">hubo</a>
	 * @return List
	 * 2012-4-19 ����01:17:48
	 */
	public List importToObjectList(ExcelHead head, File file, Class cls) {
		List contents = null;
		FileInputStream fis;
		// ����excel����list���͵�����
		List<List> rows;
		try {
			fis = new FileInputStream(file);
			rows = excelFileConvertToList(fis);
			
			// ɾ��ͷ��Ϣ
			for (int i = 0; i < head.getRowCount(); i++) {
				rows.remove(0);
			}
			
			// ����ṹת����Map
			Map<Integer, String> excelHeadMap = convertExcelHeadToMap(head.getColumns());
			// ����Ϊ����
			contents = buildDataObject(excelHeadMap, head.getColumnsConvertMap(), rows, cls);
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return contents;
	}
	
	/**
	 * ����������Excel�ļ�
	 * @param excelColumns	����ͷ��Ϣ
	 * @param excelHeadConvertMap	��Ҫ�����ݽ�������ת������
	 * @param modelFile  ģ��Excel�ļ�
	 * @param outputFile �����ļ�
	 * @param dataList	����excel�����������Դ
	 * @auther <a href="mailto:hubo@feinno.com">hubo</a>
	 * @return void
	 * 2012-4-19 ����10:04:30
	 */
	public void exportExcelFile(ExcelHead head, File modelFile, File outputFile, List<?> dataList) {
		// ��ȡ����excelģ��
	    InputStream inp = null;
	    Workbook wb = null;
		try {
			inp = new FileInputStream(modelFile);
			wb = WorkbookFactory.create(inp);
			Sheet sheet = wb.getSheetAt(0);
			// ���ɵ�������
			buildExcelData(sheet, head, dataList);
			
			// �������ļ���
		    FileOutputStream fileOut = new FileOutputStream(outputFile);
		    wb.write(fileOut);
		    fileOut.close();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (InvalidFormatException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * ������ṹת����Map
	 * @param excelColumns
	 * @auther <a href="mailto:hubo@feinno.com">hubo</a>
	 * @return void
	 * 2012-4-18 ����01:31:12
	 */
	private Map<Integer, String> convertExcelHeadToMap(List<ExcelColumn> excelColumns) {
		Map<Integer, String> excelHeadMap = new HashMap<Integer, String>();
		for (ExcelColumn excelColumn : excelColumns) {
			if(StringUtil.isEmpty(excelColumn.getFieldName())) {
				continue;
			} else {
				excelHeadMap.put(excelColumn.getIndex(), excelColumn.getFieldName());
			}
		}
		return excelHeadMap;
	}
	
	/**
	 * ���ɵ�����Excel�ļ�������
	 * @param sheet	��������
	 * @param excelColumns	excel��ͷ
	 * @param excelHeadMap	excel��ͷ��Ӧʵ������
	 * @param excelHeadConvertMap	��Ҫ�����ݽ�������ת������
	 * @param dataList		����excel�����������Դ
	 * @auther <a href="mailto:hubo@feinno.com">hubo</a>
	 * @return void
	 * 2012-4-19 ����09:36:37
	 */
	private void buildExcelData(Sheet sheet, ExcelHead head, List<?> dataList) {
		List<ExcelColumn> excelColumns = head.getColumns(); 
		Map<String, Map> excelHeadConvertMap = head.getColumnsConvertMap();
		
		// ����ṹת����Map
		Map<Integer, String> excelHeadMap = convertExcelHeadToMap(excelColumns);
		
		// �ӵڼ��п�ʼ��������
	    int startRow = head.getRowCount();
	    int order = 1;
	    for (Object obj : dataList) {
	    	Row row = sheet.createRow(startRow++);
	    	for (int j = 0; j < excelColumns.size(); j++) {
	    		Cell cell = row.createCell(j);
	    		cell.setCellType(excelColumns.get(j).getType());
	    		String fieldName = excelHeadMap.get(j);
	    		if(fieldName != null) {
	    			Object valueObject = BeanUtil.getProperty(obj, fieldName);
	    			
	    			// ���������Ҫת�����ֶ���Ϣ�������ת��
	    			if(excelHeadConvertMap != null && excelHeadConvertMap.get(fieldName) != null) {
	    				valueObject = excelHeadConvertMap.get(fieldName).get(valueObject);
	    			}
	    			
	    			if(valueObject == null) {
	    				cell.setCellValue("");
	    			} else if (valueObject instanceof Integer) {
	    				cell.setCellValue((Integer)valueObject);
					} else if (valueObject instanceof String) {
						cell.setCellValue((String)valueObject);
					} else if (valueObject instanceof Date) {
						cell.setCellValue(new JDateTime((Date)valueObject).toString("YYYY-MM-DD"));
					} else {
						cell.setCellValue(valueObject.toString());
					}
	    		} else {
	    			cell.setCellValue(order++);
	    		}
			}
		}
	}
	
	/**
	 * ��Excel�ļ�����ת��ΪList����
	 * @param fis	excel�ļ�
	 * @return	List<List> list�����ʽ������
	 * @throws IOException
	 * @auther <a href="mailto:hubo@feinno.com">hubo</a>
	 * @return List<List>
	 * 2012-4-18 ����11:37:17
	 */
	@SuppressWarnings("rawtypes")
	public List<List> excelFileConvertToList(FileInputStream fis) throws Exception {
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sheet = wb.getSheetAt(0);
		List<List> rows = new ArrayList<List>();
		DecimalFormat df = new DecimalFormat("#");
		for (Row row : sheet) {
			List<Object> cells = new ArrayList<Object>();
	        for (Cell cell : row) {
	        	Object obj = null;
	            @SuppressWarnings("unused")
				CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());
	            
	            switch (cell.getCellType()) {
	                case Cell.CELL_TYPE_STRING:
	                    obj = cell.getRichStringCellValue().toString();
	                    break;
	                case Cell.CELL_TYPE_NUMERIC:
	                    if (DateUtil.isCellDateFormatted(cell)) {
	                        obj = new JDateTime(cell.getDateCellValue());
	                    } else {
	                        obj=df.format(cell.getNumericCellValue());
	                    }
	                    break;
	                case Cell.CELL_TYPE_BOOLEAN:
	                    obj = cell.getBooleanCellValue();
	                    break;
	                case Cell.CELL_TYPE_FORMULA:
	                	obj = df.format(cell.getNumericCellValue());
	                    break;
	                default:
	                	obj = null;
	            }
	            if(!obj.equals("")){
	            	 cells.add(obj);
	            }
		       
	        }
				if(cells.size()!=0){
					rows.add(cells);
				}      
	    }
		return rows;
	}
	
	/**
	 * ����Excel�������ݶ���
	 * @param excelHeadMap ��ͷ��Ϣ
	 * @param excelHeadConvertMap ��Ҫ����ת���ĵ�Ԫ
	 * @param rows
	 * @param cls 
	 * @auther <a href="mailto:hubo@feinno.com">hubo</a>
	 * @return void
	 * 2012-4-18 ����11:39:43
	 */
	private List buildDataObject(Map<Integer, String> excelHeadMap, Map<String, Map> excelHeadConvertMap, List<List> rows, Class cls) {
		List contents = new ArrayList();
		for (List list : rows) {
			// �����ǰ��һ����������,����Ե�ǰ�е�����
			if(list == null || list.get(0) == null) {
				break;
			}
			// ��ǰ�е����ݷ���map��,����<fieldName, value>����ʽ
			Map<String, Object> rowMap = rowListToMap(excelHeadMap, excelHeadConvertMap, list);
			
			// ����ǰ��ת���ɶ�Ӧ�Ķ���
			Object obj = null;
			try {
				obj = cls.newInstance();
			} catch (InstantiationException ex) {
				ex.printStackTrace();
			} catch (IllegalAccessException ex) {
				ex.printStackTrace();
			}
			BeanUtil.populateBean(obj, rowMap);
			
			contents.add(obj);
		}
		return contents;
	}
	
	/**
	 * ����ת�г�map,����<fieldName, value>����ʽ
	 * @param excelHeadMap ��ͷ��Ϣ
	 * @param excelHeadConvertMap excelHeadConvertMap
	 * @param list
	 * @return
	 * @auther <a href="mailto:hubo@feinno.com">hubo</a>
	 * @return Map<String,Object>
	 * 2012-4-18 ����01:48:41
	 */
	private Map<String, Object> rowListToMap(Map<Integer, String> excelHeadMap, Map<String, Map> excelHeadConvertMap, List list) {
		Map<String, Object> rowMap = new HashMap<String, Object>();
		for(int i = 0; i < list.size(); i++) {
			String fieldName =  excelHeadMap.get(i);
			// �������������
			if(fieldName != null) {
				Object value = list.get(i);
				if(excelHeadConvertMap != null && excelHeadConvertMap.get(fieldName) != null) {
					value = excelHeadConvertMap.get(fieldName).get(value);
				}
				rowMap.put(fieldName, value);
			}
		}
		return rowMap;
	}
}

