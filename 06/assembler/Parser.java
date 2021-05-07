import java.io.*;
import java.io.Reader.*;
import java.util.*;

public class Parser{
	private BufferedReader reader;
	private String curLine;
	private String nextLine;

	public Parser(File filestream) throws IOException {
		//opens input file/stream and gets ready to parse it

		// file reader reads the file and buffered reader adds a buffer
		this.reader = new BufferedReader(new FileReader(filestream));
		this.curLine = null;
		this.nextLine = this.getNextLine();
	}

	private String getNextLine() throws IOException {
		String nextLine = this.reader.readLine();
		System.out.println(nextLine);

		// conditional if the next line is empty or contains a comment
		if (nextLine.trim().isEmpty() || nextLine.trim().startsWith("//")) {
		}

		return nextLine;
	}

	public boolean hasMoreCommands() {
		// are there more commands in the input
		return (this.nextLine != null);
	}

	public void advance() throws IOException {
		// reads the next command from 	the input and makes it the current command.
		// should be called only if hasMoreCommands() is true.
		// initially there is no current command.
		this.curLine = this.nextLine;
		this.nextLine = this.getNextLine();
	}

	public String commandType() {
		// Returns the type of the current command:
		// - A_COMMAND for @Xxx where Xxx is either a symbol or a decimal number
		// - C_COMMAND for dest=comp;jump
		// - L_COMMAND (actually psuedo-command) for (Xxx) where Xxx is a symbol.
		String indicator = this.curLine.trim();
		String a = "A_COMMAND";
		String c = "C_COMMAND";
		String l = "L_COMMAND";

		if (indicator.startsWith("@")) {
			return a;
		} else if (indicator.startsWith("(") && indicator.endsWith(")")) {
			return l;
		} else {
			return c;
		}
	}

	public String symbol() {
		// Returns the symbol or decimal Xxx of the current command @Xxx or (Xxx).
		// Should be called only when commandType() is A_COMMAND or L_COMMAND.
		String symbolX = this.curLine.trim();

		if (this.commandType().equals("A_COMMAND")) {
			return symbolX.substring(1);
		} else if (this.commandType().equals("L_COMMAND")) {
			return symbolX.substring(1, symbolX.length() - 1);
		} else {
			return null;
		}
	}

	// format of C_COMMAND:	dest=comp;jump

	public String dest() {
		// Returns the dest mnemonic of the current C-command (8 possibilities).
		// Should be called only when commandType() is C_COMMAND.
		String trimmed = this.curLine.trim();
		int destIndex = trimmed.indexOf("=");

		if (destIndex == -1) {
			return null;
		} else {
			return trimmed.substring(0, destIndex);
		}
	}

	public String comp() {
		// Returns the comp mnemonic of the current C-command (28 possibilities).
		// Should be called only when commandType() is C_COMMAND.
		String trimmed = this.curLine.trim();
		int destIndex = trimmed.indexOf("=");
		if (destIndex != -1) {
			trimmed = trimmed.substring(destIndex + 1);
		}
		int compIndex = trimmed.indexOf(";");
		if (compIndex == -1) {
			return trimmed;
		} else {
			return trimmed.substring(0, compIndex);
		}

	}

	public String jump() {
		// Returns the jump mnemonic of the current C-command (8 possibilities).
		// Should be called only when commandType() is C_COMMAND.
		String trimmed = this.curLine.trim();
		int compIndex = trimmed.indexOf(";");

		if (compIndex == -1) {
			return null;
		} else {
			return trimmed.substring(compIndex + 1);
		}
	}

	// Close and release
	public void close() {
		try {
			this.reader.close();
		} catch (IOException e) {
		}
	}
}