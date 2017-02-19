package pl.edu.agh.mwo.java2;

import java.io.File;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class App {

	public static void main(String[] args) {
		try {
		    	Workbook wb = WorkbookFactory.create(new File("sudoku.xlsx"));
		    	SudokuBoardChecker board = new SudokuBoardChecker(wb);
		    	for(int arkusz = 0; arkusz < 7; arkusz++){
			    	if(board.verifyBoardsStructure(arkusz)){
			    		System.out.println("Plansza " + (arkusz+1) + " jest ok syntaktycznie");
			    		if(board.verifyBoardsNumbers(arkusz)){
				    		System.out.println("Plansza " + (arkusz+1) + " jest ok semantycznie");
			    		}
				    	else{
				    		System.out.println("Plansza " + (arkusz+1) + " nie jest ok semantycznie");
				    	}
			    	}
			    	else{
			    		System.out.println("Plansza " + (arkusz+1) + " nie jest ok syntaktycznie");
			    	}
		    	}
		    	
		    } 
		catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
		        e.printStackTrace();
		}

	}

}
