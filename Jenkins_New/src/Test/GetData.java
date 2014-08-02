package Test;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class GetData {
	/*public static List<String> getLocators(String locatorName){
		String locator="";
		String locatorType="";
		List<String> locate=new ArrayList<String>();
		try {
FileInputStream fi=new FileInputStream("C:\\Users\\Sabyasachi.Rout\\Desktop\\jenkins\\testPoi.xlsx");
			
			XSSFWorkbook w=new XSSFWorkbook(fi);
			XSSFSheet s=w.getSheetAt(0);
			Iterator<Row> rowIterator=s.iterator();
			while (rowIterator.hasNext()) {
				Row row=rowIterator.next();
				Iterator<Cell> cellIterator=row.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell=cellIterator.next();
					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_STRING:
						if (cell.getStringCellValue().equalsIgnoreCase(locatorName)) {
							cell=cellIterator.next();
							locator=cell.getStringCellValue();
							//System.out.println(cell.getColumnIndex());
							XSSFRow rowHead=s.getRow(0);
							XSSFCell cellHaed=rowHead.getCell(cell.getColumnIndex());
							locatorType=cellHaed.getStringCellValue();
							//System.out.println(locatorType);
							locate.add(0, locatorType);
							locate.add(1, locator);
						}
						//System.out.println(cell.getStringCellValue());
						
						break;

					case Cell.CELL_TYPE_NUMERIC:
						//System.out.println(cell.getNumericCellValue());
						break;
					}
					
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return locate;
		
	}*/
	
	
	public static String getTestData(String data){
		String testData="";
		
		try {
			FileInputStream fi=new FileInputStream("C:\\Users\\Sabyasachi.Rout\\Desktop\\jenkins\\testPoi.xlsx");
						
						XSSFWorkbook w=new XSSFWorkbook(fi);
						XSSFSheet s=w.getSheetAt(1);
						Iterator<Row> rowIterator=s.iterator();
						while (rowIterator.hasNext()) {
							Row row=rowIterator.next();
							Iterator<Cell> cellIterator=row.cellIterator();
							while (cellIterator.hasNext()) {
								Cell cell=cellIterator.next();
								switch (cell.getCellType()) {
								case Cell.CELL_TYPE_STRING:
									if (cell.getStringCellValue().equalsIgnoreCase(data)) {
										cell=cellIterator.next();
										testData=cell.getStringCellValue();
										//System.out.println(cell.getColumnIndex());
										
									}
									//System.out.println(cell.getStringCellValue());
									
									break;

								case Cell.CELL_TYPE_NUMERIC:
									//System.out.println(cell.getNumericCellValue());
									break;
								}
								
							}
							
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
		return testData;
	}
	
	
	public static String getLocator(String locator){
		String testData="";
		
		try {
			FileInputStream fi=new FileInputStream("C:\\Users\\Sabyasachi.Rout\\Desktop\\jenkins\\testPoi.xlsx");
						
						XSSFWorkbook w=new XSSFWorkbook(fi);
						XSSFSheet s=w.getSheetAt(0);
						Iterator<Row> rowIterator=s.iterator();
						while (rowIterator.hasNext()) {
							Row row=rowIterator.next();
							Iterator<Cell> cellIterator=row.cellIterator();
							while (cellIterator.hasNext()) {
								Cell cell=cellIterator.next();
								switch (cell.getCellType()) {
								case Cell.CELL_TYPE_STRING:
									if (cell.getStringCellValue().equalsIgnoreCase(locator)) {
										cell=cellIterator.next();
										testData=cell.getStringCellValue();
										//System.out.println(cell.getColumnIndex());
										
									}
									//System.out.println(cell.getStringCellValue());
									
									break;

								case Cell.CELL_TYPE_NUMERIC:
									//System.out.println(cell.getNumericCellValue());
									break;
								}
								
							}
							
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
		return testData;
	}

}
