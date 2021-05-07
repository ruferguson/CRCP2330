import java.io.*;
import java.util.*;

public class Assembler{
	private static File assemblyCode;
	private static BufferedWriter machineCode;
	private static Code encoder;
	private static SymbolTable symbolTable;

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

		File source = new File("/Users/ruferguson/Documents/nand2tetris/projects/06/rect/Rect.asm");
		File export = new File("/Users/ruferguson/Documents/nand2tetris/projects/06/Rect.mine.hack");

		assembler(source, export);

		twoPasses();
	}

	public static void assembler(File source, File export) throws IOException {
		assemblyCode = source;

		FileWriter writer = new FileWriter(export);
		machineCode = new BufferedWriter(writer);
		encoder = new Code();
		symbolTable = new SymbolTable();
	}

	public static void twoPasses() throws IOException {
		firstPass();
		System.out.println();
		symbolTable.print();
		System.out.println();
		parse();
	}

	public static void firstPass() throws IOException {
		Parser parser = new Parser(assemblyCode);
		while (parser.hasMoreCommands()) {
			parser.advance();
			String commandType = parser.commandType();

			if (commandType.equals("L_COMMAND")) {
				String symbol = parser.symbol();
				if (symbolTable.getAddress(symbol) == null) {
					symbolTable.addEntry(symbol, symbolTable.getSize());
				} else {
					int address = symbolTable.getAddress(symbol);
					symbolTable.addEntry(symbol, address);
				}
			} 
		}
		parser.close();
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
				Character firstChar = symbol.charAt(0);
				String address = null;

				// if is a symbol
				if (!Character.isDigit(firstChar)) {

					// if symbol exists in symbol table
					if (!symbolTable.containsKey(symbol)) {
						symbolTable.addEntry(symbol, symbolTable.getSize());
					}
					address = Integer.toString(symbolTable.getAddress(symbol));
				} else {
					address = symbol;
				}
				String formattedNumber = encoder.formatToBinary(address);
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