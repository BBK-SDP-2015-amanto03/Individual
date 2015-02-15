package sml;

//////////////////////////////////////
//Class: Translator
//Coursework1
//Author: Ahsan Manto
/////////////////////////////////////

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
//import java.utilzanner;
import java.util.Scanner;//-------------------------------->>>>>> Corrected


//Added the following
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.lang.reflect.Constructor;
//Added the following
import java.lang.reflect.*;






/*
 * The translator of a <b>S</b><b>M</b>al<b>L</b> program.
 */
public class Translator {

	// word + line is the part of the current line that's not yet processed
	// word has no whitespace
	// If word and line are not empty, line begins with whitespace
	private String line = "";
	private Labels labels; // The labels of the program being translated
	private ArrayList<Instruction> program; // The program to be created
	private String fileName; // source file of SML code
	//private String "sml.Instruction.LinInstruction";

	private static final String SRC = "src";


	
	//public Translator(String fileName) {
		
		public Translator(String fileName) {	
		
		this.fileName = SRC + "/" + fileName;


	}

	// translate the small program in the file into lab (the labels) and
	// prog (the program)
	// return "no errors were detected"
	public boolean readAndTranslate(Labels lab, ArrayList<Instruction> prog) {

		try (Scanner sc = new Scanner(new File(fileName))) {
			// Scanner attached to the file chosen by the user
			labels = lab;
			labels.reset();
			program = prog;
			program.clear();

			try {
				line = sc.nextLine();
			} catch (NoSuchElementException ioE) {
				return false;
			}

			// Each iteration processes line and reads the next line into line
			while (line != null) {
				// Store the label in label
				String label = scan();

				if (label.length() > 0) {
					Instruction ins = getInstruction(label);
					if (ins != null) {
						labels.addLabel(label);
						program.add(ins);
					}
				}

				try {
					line = sc.nextLine();
				} catch (NoSuchElementException ioE) {
					return false;
				}
			}
		} catch (IOException ioE) {
			System.out.println("File: IO error " + ioE.getMessage());
			return false;
		}
		return true;
	}

	// line should consist of an MML instruction, with its label already
	// removed. Translate line into an instruction with label label
	// and return the instruction
	public Instruction getInstruction(String label) {
		int s1; // Possible operands of the instruction
		int s2;
		int r;
		int x;

		if (line.equals(""))
			return null;

		
		
	
		String ins = scan();

		
		
/////////////// ********* COMMENT/UNCOMMENT TO ACTIVATE/DEACTIVATE SWITCH CASES/REFLECTION CODE///////


		
///////////////////////////////////////////////////////////////////////////////////////////////		
///////////////////////////////////////////////////////////////////////////////////////////////
//////////////////// SWITCH CASES BEGIN HERE  **************** ONL BNZ IS NOT FUNCTIONAL //////		
///////////////////////////////////////////////////////////////////////////////////////////////		
///////////////////////////////////////////////////////////////////////////////////////////////		

		
		switch (ins) 
		{
		////--------------------------------------IF ins is 'add' then do the following case
		case "add":
			r = scanInt();
			s1 = scanInt();
			s2 = scanInt();
			return new AddInstruction(label, r, s1, s2);
		
		case "lin":
			r = scanInt();
			s1 = scanInt();
			return new LinInstruction(label, r, s1);
			
		case "sub":
			r = scanInt();
			s1 = scanInt();
			s2 = scanInt();
			return new SubInstruction(label, r, s1, s2);
			
		case "mul":
			r = scanInt();
			s1 = scanInt();
			s2 = scanInt();
			return new MulInstruction(label, r, s1, s2);	
			
		case "div":
			r = scanInt();
			s1 = scanInt();
			s2 = scanInt();
			return new DivInstruction(label, r, s1, s2);	
////
////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////**************** BNZ Sub Class is not functional *********************//////////////// 
////////////////////////////////////////////////////////////////////////////////////////////////////			
////////	case "bnz":
////////			r = scanInt();
////////			s1 = scanInt();
////////			s1 = scanIn(); 	
////////			return new BnzInstruction(label, r, s1);
////////////////////////////////////////////////////////////////////////////////////////////////////		
////			
			
		case "out":
			r = scanInt();
			return new OutInstruction(label, r);		
			
		}



		

		
		
		
		
/////////////////////////////////////////////////////////////////////////////////////////////		
/////////////////////////////////////////////////////////////////////////////////////////////
//////////////////  REFLECTION BEGINS HERE - NOT FUNCTIONAL /////////////////////////////////////////////////	
/////////////////////////////////////////////////////////////////////////////////////////////		
/////////////////////////////////////////////////////////////////////////////////////////////			

/*	
		
		switch (ins)
					{

		
		
		
			case "lin":
			
			r  = scanInt();
			s1 = scanInt();
//			
/////////////COMMENT OUT SUB CLASS CALL; CREATE REFLECTION CODE TO REPLACE			
////			return new LinInstruction(label, r, s1);
//
//						
////			Class c = "foo".getClass();
//			
////			Class<?>[] d = Character.class.getClasses();
//			
//
//			


			//String1 parameter
			Class[] paramString1 = new Class[1];	
			paramString1 [0] = String.class;
		 
			//int1 parameter
			Class[] paramInt1 = new Class[1];	
			paramInt1 [0] = Integer.TYPE;		
			
			//int2 parameter
			Class[] paramInt2 = new Class[1];	
			paramInt2 [0] = Integer.TYPE;	


	
			
			try{

		Class cls = Class.forName("sml.LinInstruction");
		Object obj = cls.newInstance();
		
		method = cls.getDeclaredMethod(paramString1, paramInt1, paramInt2);
		method.invoke(obj, 123);
		
		
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
				
//				return new LinInstruction(label, r, s1);
				
				
				
//			
//			
		}	
		
		
*/	
		
		
		

		return null;
	}

	/*
	 * Return the first word of line and remove it from line. If there is no
	 * word, return ""
	 */
	private String scan() {
		line = line.trim();
		if (line.length() == 0)
			return "";

		int i = 0;
		while (i < line.length() && line.charAt(i) != ' ' && line.charAt(i) != '\t') {
			i = i + 1;
		}
		String word = line.substring(0, i);
		line = line.substring(i);
		return word;
	}

	// Return the first word of line as an integer. If there is
	// any error, return the maximum int
	private int scanInt() {
		String word = scan();
		if (word.length() == 0) {
			return Integer.MAX_VALUE;
		}

		try {
			return Integer.parseInt(word);
		} catch (NumberFormatException e) {
			return Integer.MAX_VALUE;
		}
	}
}