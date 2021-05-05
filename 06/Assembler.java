import java.io.*;
import java.util.*;

/* /Users/ruferguson/Documents/nand2tetris/projects/06/add.asm
   /Users/ruferguson/Documents/nand2tetris/projects/06/add.mine.hack

   cd ../tools
   sh Assembler.sh

   cd ../projects
*/

public class Assembler{
	private static File assemblyCode;
	private static BufferedWriter machineCode;
	private static Code encoder;

	public static void main(String[] args) throws IOException {
		Scanner filePath = new Scanner(System.in);
		System.out.println("Enter file path: ");
		String sourceName = filePath.nextLine(); 

		Scanner exportPath = new Scanner(System.in);
		System.out.println("Enter export path: ");
		String exportName = exportPath.nextLine(); 

		File source = new File(sourceName);
		File export = new File(exportName);

		assembler(source, export);
	}

	public static void assembler(File source, File export) throws IOException {
		this.assemblyCode = source;

		FileWriter writer = new FileWriter(export);
		this.machineCode = new BufferedWriter(writer);
		this.encoder = new Code();
	}

	private void parse() throws IOException {
		Parser parser = new Parser(this.assemblyCode);
	}

	/*
		HashMap<String, Integer> symbols = new HashMap<String, Integer>();

		// predefined symbols (p. 110)
		symbols.put("SP", 0);
		symbols.put("LCL", 1);
		symbols.put("ARG",2);
		symbols.put("THIS",3);
        symbols.put("THAT",4);
        symbols.put("R0",0);
        symbols.put("R1",1);
        symbols.put("R2",2);
        symbols.put("R3",3);
        symbols.put("R4",4);
        symbols.put("R5",5);
        symbols.put("R6",6);
        symbols.put("R7",7);
        symbols.put("R8",8);
        symbols.put("R9",9);
        symbols.put("R10",10);
        symbols.put("R11",11);
        symbols.put("R12",12);
        symbols.put("R13",13);
        symbols.put("R14",14);
        symbols.put("R15",15);
        symbols.put("SCREEN",16384);
        symbols.put("KBD",24576);
	*/

}