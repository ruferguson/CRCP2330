import java.io.*;
import java.util.*;

public class Assembler{
	private static File assemblyCode;
	private static BufferedWriter machineCode;
	private static Code encoder;
	private static SymbolTable symbolTable;

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
		parse();
	}

	public static void firstPass() throws IOException {
		Parser parser = new Parser(assemblyCode);
		while (parser.hasMoreCommands()) {
			parser.advance();
			String commandType = parser.commandType();

			if (commandType.equals("L_COMMAND")) {
				String symbol = parser.symbol();
				symbolTable.addEntry(symbol, symbolTable.getProgAdd());
			} else {
				symbolTable.incProgAdd();
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
						symbolTable.addEntry(symbol, symbolTable.getDataAdd());
						symbolTable.incDataAdd();
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