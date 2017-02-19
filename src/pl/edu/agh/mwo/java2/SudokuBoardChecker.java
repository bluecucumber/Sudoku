package pl.edu.agh.mwo.java2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class SudokuBoardChecker {
	
	private Workbook board;
	
	public SudokuBoardChecker(Workbook workbook){
		this.board = workbook;
	}
	
	public boolean verifyBoardsStructure(int sheetIndex) {
		
		int i, j;
		Sheet sheet = board.getSheetAt(sheetIndex);

		for(i=0; i<9; i++){
			for(j=0; j<9; j++)
			{
				Row row = sheet.getRow(i);
				if (row == null) {
				 // ca³y wiersz jest pusty (nie zosta³ stworzony)
				 // w takim przypadku nie mo¿na z niego pobraæ komórek
				}
				else{
					Cell cell = row.getCell(j);
					if (cell == null) {
						System.out.println("pusta");
					 // komórka nie zosta³a stworzona (ma pust¹ wartoœæ)
					 // w takim przypadku nie mo¿na z niej pobraæ wartoœci
					}
					else{
						@SuppressWarnings("deprecation")
						CellType cellType = cell.getCellTypeEnum();
						switch (cellType) {
						 case BLANK: // pusta wartoœæ
							 break;
						 case BOOLEAN: // wartoœæ logiczna
							 return false;
						 case STRING: // wartoœæ tekstowa
							 return false;
						 case NUMERIC: // wartoœæ numeryczna (zmienno przecinkowa)
							 double value = cell.getNumericCellValue();
							 if(value < 1 || value > 9){
								 return false;
							 }
							 break;
						 case FORMULA: // formu³a
							 return false;
						 case ERROR: // b³¹d
							 return false;
						 case _NONE:
							 break;
						 default:
							 break;
						}
					}
				}
	
			}
		}
		return true;
	}
	public boolean verifyBoardsNumbers(int sheetIndex) {
		Sheet sheet = board.getSheetAt(sheetIndex);
		if(verifyRows(sheet) && verifyColumns(sheet) && verifySquares(sheet)){
			return true;
		}
		else{ return false; }
	}

	private boolean verifySquares(Sheet sheet) {
		int i, j, k, l;
		double value;
		for(l=0; l<3; l++){
			for(k=0; k<3; k++){
				Set<Double> set = new HashSet<>(Arrays.asList(new Double[] { 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0 }));
				for(i=0; i<3; i++){
					for(j=0; j<3; j++)
					{
						Row row = sheet.getRow(i+l*3);
						Cell cell = row.getCell(j+k*3);	
						value = cell.getNumericCellValue();
						if(value != 0){
							if(set.remove(value)){	}
							else{ return false; }
						}				
					}
				}
			}
		}
		return true;
	}

	private boolean verifyColumns(Sheet sheet) {
		int i, j;
		double value;
		for(i=0; i<9; i++){
			Set<Double> set = new HashSet<>(Arrays.asList(new Double[] { 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0 }));
			for(j=0; j<9; j++)
			{
				Row row = sheet.getRow(j);
				Cell cell = row.getCell(i);	
				value = cell.getNumericCellValue();
				if(value != 0){
					if(set.remove(value)){	}
					else{ return false; }
				}				
			}
		}
		return true;
	}

	private boolean verifyRows(Sheet sheet) {
		int i, j;
		double value;
		for(i=0; i<9; i++){
			Set<Double> set = new HashSet<>(Arrays.asList(new Double[] { 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0 }));
			for(j=0; j<9; j++)
			{
				Row row = sheet.getRow(i);
				Cell cell = row.getCell(j);	
				value = cell.getNumericCellValue();
				if(value != 0){
					if(set.remove(value)){	}
					else{ return false; }
				}				
			}
		}
		return true;
	}
}
