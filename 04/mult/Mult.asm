// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Mult.asm

// Multiplies R0 and R1 and stores the result in R2.
// (R0, R1, R2 refer to RAM[0], RAM[1], and RAM[2], respectively.)
//
// This program only needs to handle arguments that satisfy
// R0 >= 0, R1 >= 0, and R0*R1 < 32768.

// set a = R0
@R0
D = M
@a
M = D 

// set b = R1
@R1
D = M 
@b
M = D

// set R2 to 0
@R2
M = 0

(WHILE)

// if (a < 0) goto ENDWHILE1
@a
D = M
@ENDWHILE
D;JLE

// R2 = R2 + b
@b
D = M
@R2
M = M + D

// a --
@1
D = A
@a
M = M - D

// jump to WHILE
@WHILE
0;JMP

(ENDWHILE)




