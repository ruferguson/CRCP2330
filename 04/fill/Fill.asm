// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Fill.asm

// Runs an infinite loop that listens to the keyboard input.
// When a key is pressed (any key), the program blackens the screen,
// i.e. writes "black" in every pixel;
// the screen should remain fully black as long as the key is pressed. 
// When no key is pressed, the program clears the screen, i.e. writes
// "white" in every pixel;
// the screen should remain fully clear as long as no key is pressed.

(STARTOVER) // reset to start

@SCREEN 	// start screen in RAM0
D = A
@0
M = D



(KEYCHECK)

@KBD
D = M

@BLACK 		// go to BLACK if key pressed
D; JGT

@WHITE 		// go to WHITE if no key pressed
D;JEQ

@KEYCHECK	//(continuously loop)
0;JMP



(BLACK)
@R1
M = -1		// -1 = 11111111111111
@FILL
0;JMP

(WHITE)
@R1
M = 0
@FILL
0;JMP



(FILL)
@R1
D = M

@R0 		// get pixel address
A = M
// fill it
M = D 

@R0
D = M + 1
@KBD
D = A - D

@R0
M = M + 1
A = M

@FILL
D;JGT

@STARTOVER 	// reset to start
0;JMP
