package TestFramework;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//200. Strategy to Access Excel Data
//201. Getting rows and its cells from Sheet
//202. Retrieving Data from Excel based on condition

public class dataDriven {
		
	//Once column is identified, then scan entire testcases column to identify purchase row
	//after you grab purchase testcase row, pull all that data of that row and feed it into test

	public ArrayList<String> getData(String testcaseName,String sheetName) throws IOException
	{
		//Accepts a fileInputStream argument, so basically you have to wrap your file as an input stream object and then pass it inside this
		//so FileInputStream is a class in Java where it will create an object which have the power to read any file
				
				ArrayList<String> a = new ArrayList<String>();
				
				FileInputStream fis = new FileInputStream("C:\\Users\\Fran\\Desktop\\QA(Theory)\\6. Udemi\\Selenium\\demodata.xlsx");
				XSSFWorkbook workbook = new XSSFWorkbook(fis);
				
				int sheets = workbook.getNumberOfSheets();
				for(int i=0;i<sheets;i++)
				{
					if(workbook.getSheetName(i).equalsIgnoreCase(sheetName))
					{
					XSSFSheet sheet = workbook.getSheetAt(i);
					//Identify (in our Excel called: demodata) Testcases column by scanning the entire 1st row
					
					Iterator<Row> rows = sheet.iterator();// sheet is a collection of rows, and to traverse each and every row we are using iterator concept
					Row firstrow =  rows.next();
					Iterator<Cell> ce =  firstrow.cellIterator();// Once you get the desired row, row is a collection of cells, and to traverse each and every cell we are using iterator concept again
					int k=0;
					int column=0;
				while(ce.hasNext()) //these while method checks if next cell is present, yes, it is present,so when condition is true,"I will go inside the method, and I will actually move now   
				{
					Cell value = ce.next();
					if(value.getStringCellValue().equalsIgnoreCase("TestCases"))
					{
						//desired column
						column=k;
					}
					
					k++;
				}
				System.out.println(column);
				
				//once column is identified, then scan entire test case column to identify purchase row
				while(rows.hasNext())		//if next row is present, then go inside, okay?	
				{
					Row r = rows.next();	//now we are in the 1st row
					if(r.getCell(column).getStringCellValue().equalsIgnoreCase(testcaseName))//So here, you are saying,for every row, get me the cell value only at the zero index, okay?
					{
						//after you grab purchase test case row = pull all the data of that row and feed into test
						
						Iterator<Cell> cv = r.cellIterator();
						while(cv.hasNext())
						{
							Cell c =  cv.next();
							if(c.getCellType()==CellType.STRING)//It cannot find method c.getCellTypeEnum*****************minuto 3, leccion 205.
							{
								
							a.add(c.getStringCellValue()); //Now all the data of the test case column is properly stored in this ArrayList
							}
							else
							{
							a.add(NumberToTextConverter.toText(c.getNumericCellValue()));
							}
						}
					}
						
				}
									
					}
				}
				return a;
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		
	}

}
