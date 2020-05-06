import java.util.Scanner;
import java.io.*;
import java.util.*;

class PassOne {
	public static void main(String args[])throws Exception {
		// Taking input filename of assembly file and printing it
		System.out.println("Welcome to my PassOne Assembler!\n");
		//System.out.print("Enter the input file name :");
    	//Scanner  input = new Scanner(System.in);
		//String filename = input.next();
		String filename = "input.asm";
		System.out.println("The file name you have entered is:" + filename + "\n");
		File file = new File(filename);
		BufferedReader br = new BufferedReader(new FileReader(file));
		String st;
		br = new BufferedReader(new FileReader(file));
		System.out.println("INPUT ASSEMBLY FILE:\n");	
		while((st = br.readLine()) != null){
			System.out.println(st);
		}
		System.out.println("\n");	
		
		//actual conversion will start here
		/*
 			Data structure required are Symbol table, LitTab,PoolTab and OpTab.
			Except OpTab others would be empty at start.
			OpTab would enclose different opcode and their short forms
			e.g., STOP => IS,00
				  ADD => IS,01
			A dictionary could be used
			The OpTab would be static and would never change	
			Other table are data structures themselves and so we could make a class for each
			with functions
		*/
		System.out.println("OUTPUT INTERMEDIATE CODE:\n");
		SymTab sym = new SymTab();
		LookUpTable lt = new LookUpTable();	
		
		file = new File(filename);	
		// Start reading the file and substite for the IC codes first
		br = new BufferedReader(new FileReader(file));
		while((st = br.readLine()) != null){
			String[] words = st.split(" ",2);
			words[0] = lt.getCode(words[0]);
			for(String a : words)
				System.out.print(a + " ");
			System.out.print("\n");
		}	
	}
}

class LookUpTable {
	Hashtable<String,String> lt;
	public LookUpTable() throws Exception {
		lt = new Hashtable<String,String>();
		//System.out.print("Enter the name of Instruction codes file:");		
		String filename = "OpTab.txt";
		File file = new File(filename);
		BufferedReader br = new BufferedReader(new FileReader(file));
		String st;
		while((st = br.readLine()) != null){
			String[] arrOfStr = st.split(" ");
			lt.put(arrOfStr[0],arrOfStr[1]);
		}
	}
	
	public String getCode(String instruction){
		String code;
		if(lt.containsKey(instruction))
			code = lt.get(instruction);
		else 
			code = instruction;
		return code;
	}
}

	
class SymTab {
	Hashtable<String,Integer> symtab;
	//constructor	
	public SymTab(){
		//create a dict datastructure to store the data
		symtab = new Hashtable<String,Integer>();	
	}	
	
	//create functions to insert
	public void setAddress(String symbol,int address){
		System.out.println("Hello from setAddress!");
		symtab.put(symbol,address);
		System.out.println("symtab : " + symtab);
	}

	public void getAddress(String symbol){
		int value = symtab.get(symbol);
		System.out.println("Hello from getAddress! Value: " + value);	
	}		
}


class LitTab {

}

class PoolTab {

}
