package base.util.fileOperator;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import atomic.exception.SystemException;


import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * 
 *
 * TODO
 * xls文件操作
 * 
 * @author wx
 * @version  1.0  2014-9-9
 * 
 * 版权所有(C)卫德住工科技
 * Copyright(C)EasyPc.All Rights Reserved.
 * 
 * history:
 *
 */
@SuppressWarnings({"rawtypes"})
public class FileExcelOperation {

	private WritableWorkbook fWorkBook = null;
	private WritableWorkbook newReadBook = null;
	private Workbook fReadBook = null;
	
	/**
	 * 创建读取EXCEL
	 * @param fileName
	 * @return
	 */
	public Workbook readExcel(String fileName)
	{
		File f = new File(fileName);
		if (!f.exists())
		{
			throw new SystemException("文件不存在");
		}
		try {
			fReadBook = Workbook.getWorkbook(f);
		} catch (Exception e) {
			throw new SystemException(e.getMessage());
		}
		return fReadBook;
	}
	/**
	 * 获取数据页通过索引
	 * TODO
	 * 
	 * @param index
	 * @return Sheet
	 * 
	 * history
	 *
	 */
	public Sheet readSheet(int index)
	{
		if(fReadBook == null)
		{
			throw new SystemException("读取EXCEL工作薄不能为空");
		}
		return fReadBook.getSheet(index);
	}
	/**
	 * 获取数据页通过页签
	 * TODO
	 * 
	 * @param name
	 * @return Sheet
	 * 
	 * history
	 *
	 */
	public Sheet readSheet(String name)
	{
		if(fReadBook == null)
		{
			throw new SystemException("读取EXCEL工作薄不能为空");
		}
		return fReadBook.getSheet(name);
	}
	public Cell getCell(Sheet activeSheet , int col , int row)
	{
		return activeSheet.getCell(col , row);
	}
	
	public String getCellValue(Sheet activeSheet , int col , int row)
	{
		return activeSheet.getCell(col , row).getContents().toString();
	}
	public int getRowCount(Sheet activeSheet)
	{
		return activeSheet.getRows();
	}
	public int getColumnCount(Sheet activeSheet)
	{
		return activeSheet.getColumns();
	}
	/**
	 * 创建写入EXCEL
	 * @param fileName
	 * @return
	 */
	public WritableWorkbook openExcel(String fileName)
	{
		if (fWorkBook == null)
		{
			String str = fileName.substring(0 , fileName.lastIndexOf(File.separator));
			 File dir = new File(str+File.separator);
			 if (!dir.exists())
			 {
				 dir.mkdirs();
			 }
		}
		File f = new File(fileName);
		try {
			fWorkBook = Workbook.createWorkbook(f);
		} catch (IOException e) {
			throw new SystemException(e.getMessage());
		}
		return fWorkBook;
	}
	
	public WritableWorkbook copeWritableWorkbook(String fileName){
		String str = fileName.substring(0 , fileName.lastIndexOf(File.separator));
		File dir = new File(str+File.separator);
		if (!dir.exists())
		{
			dir.mkdirs();
		}
		try {
			newReadBook = Workbook.createWorkbook(new File(fileName), fReadBook);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newReadBook;
	}
	
	public WritableSheet getNewSheet(int index)
	{
		return newReadBook.getSheet(index);
	}
	
	public WritableSheet getSheet(int index)
	{
		return fWorkBook.getSheet(index);
	}
	public WritableSheet getSheet(String name)
	{
		return fWorkBook.getSheet(name);
	}
	public WritableSheet createSheet(String name)
	{
		return fWorkBook.createSheet(name, 0);
	}
	public WritableSheet createSheet(String name , int index)
	{
		return fWorkBook.createSheet(name, index);
	}
	
	public Cell getCell(WritableSheet activeSheet , int col , int row)
	{
		return activeSheet.getCell(col , row);
	}
	
	public String getCellValue(WritableSheet activeSheet , int col , int row)
	{
		return activeSheet.getCell(col , row).getContents().toString();
	}
	public void setCellValue(WritableSheet activeSheet , int col , int row , String value)
	{
		try {
			activeSheet.addCell(new Label(col, row, value));
		} catch (Exception e) {
			throw new SystemException(e.getMessage());
		} 
	}
	
	public int getRowCount(WritableSheet activeSheet)
	{
		return activeSheet.getRows();
	}
	public int getColumnCount(WritableSheet activeSheet)
	{
		return activeSheet.getColumns();
	}
	/**
	 * 
	 * @param activeSheet 激活页
	 * @param l 数据LIST
	 * @param cap 头LIST
	 * @param sort 排序LIST
	 * @param startRow 起始行
	 * @param startCol 起始列
	 */
	
	@SuppressWarnings("unused")
	public void setExcelValue(WritableSheet activeSheet , List l , List cap , List sort , int startRow , int startCol)
	{
		if (l.size() <=0)
		{
			return;
		}
		int row = startRow ;
		if (cap != null && cap.size()>0)
		{
			for (int i = 0 ; i<cap.size() ; i++)
			{
				setCellValue(activeSheet , startCol +i , startRow , cap.get(i).toString());
			}
			
		} else {
			if (sort != null && sort.size()>0)
			{	
				for (int r = 0 ; r < sort.size() ;r++)
				{
					Map m = (Map)l.get(0);
					Iterator it = m.entrySet().iterator();
					int i =0;
					while(it.hasNext())
					{
						Map.Entry t = (Map.Entry) it.next();
						if (sort.get(r).toString().equals(t.getKey().toString()))
						{
							setCellValue(activeSheet , startCol +r , startRow , t.getKey().toString());	
							i++;
							break;
						}	
						i++;
						
					}
				}
			} else {
				Map m = (Map)l.get(0);
				Iterator it = m.entrySet().iterator();
				int i =0;
				while(it.hasNext())
				{
					Map.Entry t = (Map.Entry) it.next();
					setCellValue(activeSheet , startCol +i , startRow , t.getKey().toString());	
					i++;
				}
			}
		}
		row += 1;
		
		if (sort != null && sort.size()>0)
		{
			for (int i = 0 ; i<l.size() ; i++)
			{
				Map m = (Map)l.get(i);
				for (int c = 0 ; c<sort.size() ; c++)
				{
					if (m.containsKey(sort.get(c).toString()))
					{
						setCellValue(activeSheet , startCol +c , row +i  , m.get(sort.get(c).toString()).toString());
					}
				}
			}
		} else {
			for (int i = 0 ; i<l.size() ; i++)
			{
				Map m = (Map)l.get(i);
				Iterator it = m.entrySet().iterator();
				int r =0;
				while(it.hasNext())
				{
					Map.Entry t = (Map.Entry) it.next();
					setCellValue(activeSheet , startCol +r , row +i  , t.getValue().toString());	
					r ++;
				}
			}
		}
	}
	
	
	public WritableSheet setSheetExcelValue(int index, int startRow, int startCol, String value)
	{
		WritableSheet r = null;
		
		r = this.getNewSheet(index);
		setCellValue(r, startRow, startCol, value);
		
		return r;
	}
	
	
	public WritableSheet newSheetExcelValue(String sheetName , int index , List l , List cap , List sort , int startRow , int startCol)
	{
		WritableSheet r = null;
		
		r = this.createSheet(sheetName, index);
		setExcelValue(r , l , cap ,sort, startRow , startCol);
		
		return r;
	}
	
	
	public WritableSheet newSheetExcelValue(String sheetName , List l , List cap , List sort , int startRow , int startCol)
	{
		WritableSheet r = null;
		
		r = this.createSheet(sheetName, 0);
		setExcelValue(r , l , cap , sort ,startRow , startCol);
		
		return r;
	}
	
	
	public WritableSheet newSheetExcelValue(String sheetName , List l , List cap ,List sort )
	{
		WritableSheet r = null;
		r = this.createSheet(sheetName, 0);
		setExcelValue(r , l , cap ,sort, 0 , 0);
		
		return r;
	}
	
	public WritableSheet newSheetExcelValue( List l , List cap , List sort)
	{
		WritableSheet r = null;
		r = this.createSheet("新建sheet1", 0);
		setExcelValue(r , l , cap ,sort , 0 , 0);
		
		return r;
	}
	
	public WritableSheet newSheetExcelValue( List l , List sort)
	{
		WritableSheet r = null;
		r = this.createSheet("新建sheet1", 0);
		setExcelValue(r , l , null ,sort, 0 , 0);
		return r;
	}
	
	public void saveQuit()
	{
		try {
			if (fWorkBook != null)
			{
				fWorkBook.write();
				fWorkBook.close();
				fWorkBook = null;
			}
			if (newReadBook != null)
			{
				newReadBook.write();
				newReadBook.close();
				newReadBook = null;
			}
			if (fReadBook != null)
			{
				fReadBook.close();
				fReadBook = null;
			}
		} catch (Exception e) {
			throw new SystemException(e.getMessage());
		}
		
	}
	public void withoutSaveQuit()
	{
		try {
			if (fWorkBook != null)
			{
				fWorkBook.close();
				fWorkBook = null;
			}
			if (newReadBook != null)
			{
				newReadBook.close();
				newReadBook = null;
			}
			if (fReadBook != null)
			{
				fReadBook.close();
				fReadBook = null;
			}
		} catch (Exception e) {
			throw new SystemException(e.getMessage());
		}
	}
  
}
