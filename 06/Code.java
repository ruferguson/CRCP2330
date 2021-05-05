import java.io.*;
import java.util.*;

// translates Hack assembly language mnemonics into binary codes
public class Code {
	private HashMap<String, String> compMnem;
	private HashMap<String, String> destMnem;
	private HashMap<String, String> jumpMnem;

	public Code() {
		this.compMnem = new HashMap<String, String>();
		this.destMnem = new HashMap<String, String>();
		this.jumpMnem = new HashMap<String, String>();

		// dest instructions (p.110)
        destMnem.put("NULL", "000");
		destMnem.put("M", "001");
		destMnem.put("D", "010");
		destMnem.put("MD", "011");
		destMnem.put("A", "100");
		destMnem.put("AM", "101");
		destMnem.put("AD", "110");
		destMnem.put("AMD", "111");

		// comp instructions (p.109)
        compMnem.put("0", "0101010");
		compMnem.put("1", "0111111");
		compMnem.put("-1", "0111010");
		compMnem.put("D", "0001100");
		compMnem.put("A", "0110000");
		compMnem.put("M", "1110000");
		compMnem.put("!D", "0001101");
		compMnem.put("!A", "0110001");
		compMnem.put("!M", "1110001");
		compMnem.put("-D", "0001111");
		compMnem.put("-A", "0110011");
		compMnem.put("-M", "1110011");
		compMnem.put("D+1", "0011111");
		compMnem.put("A+1", "0110111");
		compMnem.put("M+1", "1110111");
		compMnem.put("D-1", "0001110");
		compMnem.put("A-1", "0110010");
		compMnem.put("M-1", "1110010");
		compMnem.put("D+A", "0000010");
		compMnem.put("D+M", "1000010");
		compMnem.put("D-A", "0010011");
		compMnem.put("D-M", "1010011");
		compMnem.put("A-D", "0000111");
		compMnem.put("M-D", "1000111");
		compMnem.put("D&A", "0000000");
		compMnem.put("D&M", "1000000");
		compMnem.put("D|A", "0010101");
		compMnem.put("D|M", "1010101");

		// jump instructions (p.110)
        jumpMnem.put("NULL", "000");
		jumpMnem.put("JGT", "001");
		jumpMnem.put("JEQ", "010");
		jumpMnem.put("JGE", "011");
		jumpMnem.put("JLT", "100");
		jumpMnem.put("JNE", "101");
		jumpMnem.put("JLE", "110");
		jumpMnem.put("JMP", "111");
	}

	//  Returns the binary code of the destMnem mnemonic.
	public String dest(String m) {
		return this.destMnem.get(m);
	}

	//  Returns the binary code of the compMnem mnemonic.
	public String comp(String m) {
		return this.compMnem.get(m);
	}

	//  Returns the binary code of the jumpMnem mnemonic.
	public String jump(String m) {
		return this.jumpMnem.get(m);
	}
}
