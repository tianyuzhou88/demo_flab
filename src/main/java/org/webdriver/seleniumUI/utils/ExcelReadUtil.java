package org.webdriver.seleniumUI.utils;

import jxl.*;

import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * excel
 * @author Administrator
 *
 */
public class ExcelReadUtil {
	/**
	 *
	 *
	 */
	public static  Object[][] case_data_excel( int sheet_id,
											   int start_row,
											   int end_row,
											   int start_col,
											   int end_col,
											   String sourcefile

	)
	{
		String cell_value=null;
		Cell cell=null;
		ArrayList<Object> testcase_data_list=new ArrayList<Object>();
		String[][] testcase_data_array=new String[end_row-start_row+1][end_col-start_col+1];
		Workbook testcase_data_workbook = null;
		try
		{

			testcase_data_workbook=Workbook.getWorkbook(new File(sourcefile));
			Sheet testcase_data_sheet=testcase_data_workbook.getSheet(sheet_id);
			int rows=testcase_data_sheet.getRows();
			int cols=testcase_data_sheet.getColumns();
			if (end_row-start_row+1>rows) {
				System.out.println("selected rows exceed the actual rows ");
			}
			if (end_col-start_col+1>cols) {
				System.out.println("selected column exceed the actual rows ");
			}
			if (end_row>rows-1) {
				System.out.println("row index exceeds actual row value");
			}
			if (end_col>cols-1) {
				System.out.println("column index exceeds actual column value");
			}

			for (int row = start_row,i=0; row <=end_row||i<testcase_data_array.length; row++,i++)
			{

				String[] row_array=new String[end_col-start_col+1];
				for(int col=start_col,j=0;col<=end_col||j<row_array.length;col++,j++)
				{
					cell=testcase_data_sheet.getCell(col, row);
					if(cell.getType() == CellType.DATE){
						DateCell dc = (DateCell)cell;
						Date date = dc.getDate();
						cell_value=formatDate(date,"yyyy-MM-dd");
					}
					else {
						cell_value=testcase_data_sheet.getCell(col, row).getContents();
					}


					row_array[j]=cell_value;
				}
				testcase_data_list.add(row_array);

			}

			String[][] testcase_data_array_try=new String[testcase_data_list.size()][end_col-start_col+1];
			testcase_data_array_try=testcase_data_list.toArray(testcase_data_array_try);
			testcase_data_array=testcase_data_array_try;

		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Object[][] testcase_data_object=(Object[][])testcase_data_array;
		return testcase_data_object;

	}

	/**
	 *

	 */
	public static  Object[][] case_data_excel( int sheet_id,
											   int start_row,
											   int end_row,
											   int start_col,
											   int end_col,
											   InputStream SourceInputStream

	)
	{
		String cell_value=null;
		Cell cell=null;
		ArrayList<Object> testcase_data_list=new ArrayList<Object>();
		String[][] testcase_data_array=new String[end_row-start_row+1][end_col-start_col+1];
		//System.out.println(SourceInputStream);
		try
		{
			Workbook testcase_data_workbook=Workbook.getWorkbook(SourceInputStream);
			Sheet testcase_data_sheet=testcase_data_workbook.getSheet(sheet_id);
			int rows=testcase_data_sheet.getRows();
			int cols=testcase_data_sheet.getColumns();
			if (end_row-start_row+1>rows) {
				System.out.println("selected rows exceed the actual rows ");
			}
			if (end_col-start_col+1>cols) {
				System.out.println("selected column exceed the actual rows ");
			}
			if (end_row>rows-1) {
				System.out.println("row index exceeds actual row value");
			}
			if (end_col>cols-1) {
				System.out.println("column index exceeds actual column value");
			}

			for (int row = start_row,i=0; row <=end_row||i<testcase_data_array.length; row++,i++)
			{

				String[] row_array=new String[end_col-start_col+1];
				for(int col=start_col,j=0;col<=end_col||j<row_array.length;col++,j++)
				{
					cell=testcase_data_sheet.getCell(col, row);
					if(cell.getType() == CellType.DATE){
						DateCell dc = (DateCell)cell;
						Date date = dc.getDate();
						cell_value=formatDate(date,"yyyy-MM-dd");
					}
					else {
						cell_value=testcase_data_sheet.getCell(col, row).getContents();
					}


					row_array[j]=cell_value;
				}

				testcase_data_list.add(row_array);

			}

			String[][] testcase_data_array_try=new String[testcase_data_list.size()][end_col-start_col+1];
			testcase_data_array_try=testcase_data_list.toArray(testcase_data_array_try);
			testcase_data_array=testcase_data_array_try;

		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Object[][] testcase_data_object=(Object[][])testcase_data_array;
		return testcase_data_object;

	}
	/**

	 */
	public static  Object[][] case_data_excel( int sheet_id,
											   InputStream SourceInputStream

	)
	{
		String cell_value=null;
		Cell cell=null;
		ArrayList<Object> testcase_data_list=new ArrayList<Object>();
		String[][] testcase_data_array=null;
		Workbook testcase_data_workbook = null;
		try
		{
			testcase_data_workbook=Workbook.getWorkbook(SourceInputStream);
			Sheet testcase_data_sheet=testcase_data_workbook.getSheet(sheet_id);
			int rows=testcase_data_sheet.getRows();
			int cols=testcase_data_sheet.getColumns();
			testcase_data_array=new String[rows][cols];

			for (int row = 0,i=0; row <=rows-1||i<testcase_data_array.length; row++,i++)
			{

				String[] row_array=new String[cols];
				for(int col=0,j=0;col<=cols-1||j<row_array.length;col++,j++)
				{
					cell=testcase_data_sheet.getCell(col, row);
					if(cell.getType() == CellType.DATE){
						DateCell dc = (DateCell)cell;
						Date date = dc.getDate();
						cell_value=formatDate(date,"yyyy-MM-dd");
					}
					else {
						cell_value=testcase_data_sheet.getCell(col, row).getContents();
					}

					row_array[j]=cell_value;
				}

				testcase_data_list.add(row_array);

			}

			String[][] testcase_data_array_try=new String[testcase_data_list.size()][cols];
			testcase_data_array_try=testcase_data_list.toArray(testcase_data_array_try);
			testcase_data_array=testcase_data_array_try;

		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Object[][] testcase_data_object=(Object[][])testcase_data_array;
		return testcase_data_object;

	}
	/**

	 */
	public static  Object[][] case_data_excel( int sheet_id,
											   String sourcefile

	)
	{
		String cell_value=null;
		Cell cell=null;
		ArrayList<Object> testcase_data_list=new ArrayList<Object>();
		String[][] testcase_data_array=null;
		Workbook testcase_data_workbook = null;
		try
		{
			testcase_data_workbook=Workbook.getWorkbook(new File(sourcefile));
			Sheet testcase_data_sheet=testcase_data_workbook.getSheet(sheet_id);
			int rows=testcase_data_sheet.getRows();
			int cols=testcase_data_sheet.getColumns();
			testcase_data_array=new String[rows][cols];

			for (int row = 0,i=0; row <=rows-1||i<testcase_data_array.length; row++,i++)
			{
				String[] row_array=new String[cols];
				for(int col=0,j=0;col<=cols-1||j<row_array.length;col++,j++)
				{
					cell=testcase_data_sheet.getCell(col, row);
					if(cell.getType() == CellType.DATE){
						DateCell dc = (DateCell)cell;
						Date date = dc.getDate();
						cell_value=formatDate(date,"yyyy-MM-dd");
					}
					else {
						cell_value=testcase_data_sheet.getCell(col, row).getContents();
					}


					row_array[j]=cell_value;
				}

				testcase_data_list.add(row_array);

			}

			String[][] testcase_data_array_try=new String[testcase_data_list.size()][cols];
			testcase_data_array_try=testcase_data_list.toArray(testcase_data_array_try);
			testcase_data_array=testcase_data_array_try;

		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Object[][] testcase_data_object=(Object[][])testcase_data_array;
		return testcase_data_object;

	}
	private  static String formatDate(Date date,String format)
	{
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		System.out.println(formatter.format(date).toString());
		return formatter.format(date).toString();

	}
	public  static  void  main(String[] args)
	{
		String filePath="src/main/resources/data/new_account.xls";
		Object[][] excel=ExcelReadUtil.case_data_excel(0, 1, 2, 0,0,filePath);
		for (int i=0;i<excel.length;i++)
		{
			Object[] excel2=excel[i];
			for (int j=0;j<excel2.length;j++)
			{
				System.out.println(excel2[j]);
			}
		}
	}
}
