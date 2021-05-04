import java.io.*;
import java.io.Reader*;
import java.util.*;

public class Parser{
	private BufferedReader reader;

	public Parser(File filestream) {
		//opens input file/stream and gets ready to parse it

		// file reader reads the file and buffered reader adds a buffer
		this.reader = new BufferedReader(new FileReader(filestream));
	}

	public boolean hasMoreCommands() {
		// are there more commands in the input
		
		return;
	}

	public void advance() {
		// reads the next command from 	the input and makes it the current command.
		// should be called only if hasMoreCommands() is true.
		// initially there is no current command.

	}

	public CommandType commandType() {
		// Returns the type of the current command:
		// - A_COMMAND for @Xxx where Xxx is either a symbol or a decimal number
		// - C_COMMAND for dest=comp;jump
		// - L_COMMAND (actually psuedo-command) for (Xxx) where Xxx is a symbol.
	}

	public String symbol() {
		// Returns the symbol or decimal Xxx of the current command @Xxx or (Xxx).
		// Should be called only when commandType() is A_COMMAND or L_COMMAND.
	}

	public String dest() {
		// Returns the dest mnemonic of the current C-command (8 possibilities).
		// Should be called only when commandType() is C_COMMAND.
	}

	public String comp() {
		// Returns the comp mnemonic of the current C-command (28 possibilities).
		// Should be called only when commandType() is C_COMMAND.
	}

	public String jump() {
		// Returns the jump mnemonic of the current C-command (8 possibilities).
		// Should be called only when commandType() is C_COMMAND.
	}
}