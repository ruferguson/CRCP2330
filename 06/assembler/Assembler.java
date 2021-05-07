import java.io.*;
import java.util.*;

/* 
/Users/ruferguson/Documents/nand2tetris/projects/06/add/add.asm
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
		/*Scanner filePath = new Scanner(System.in);
		System.out.println("Enter file path: ");
		String sourceName = filePath.nextLine(); 

		Scanner exportPath = new Scanner(System.in);
		System.out.println("Enter export path: ");
		String exportName = exportPath.nextLine(); 

		File source = new File(sourceName);
		File export = new File(exportName);
		*/

		File source = new File("/Users/ruferguson/Documents/nand2tetris/projects/06/add/add.asm");
		File export = new File("/Users/ruferguson/Documents/nand2tetris/projects/06/add.mine.hack");

		assembler(source, export);

		parse();
	}

	public static void assembler(File source, File export) throws IOException {
		assemblyCode = source;

		FileWriter writer = new FileWriter(export);
		machineCode = new BufferedWriter(writer);
		encoder = new Code();
	}

	public static void parse() throws IOException {
		Parser parser = new Parser(assemblyCode);

		while (parser.hasMoreCommands()) {
			parser.advance();

			String commandType = parser.commandType();
			String instruction = null;

			if (commandType.equals("A_COMMAND")) {
				// Format A-instruction
				String symbol = parser.symbol();
				String formattedNumber = encoder.formatToBinary(symbol);
				instruction = "0" + formattedNumber;
			} else if (commandType.equals("C_COMMAND")) {
				// Format C-instruction
				StringWriter newString = new StringWriter();
				newString.append("111");
				newString.append(encoder.comp(parser.comp()));
				newString.append(encoder.dest(parser.dest()));
				newString.append(encoder.jump(parser.jump()));
				instruction = newString.toString();
			}

			if (!commandType.equals("L_COMMAND")) {
				// write to file
				machineCode.write(instruction);
				machineCode.newLine();
			}
		}

		// release
		parser.close();
		machineCode.flush();
		machineCode.close();
	}
}