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

// set sum to 0
@sum
M = 0

(WHILE)

// if (a < 0) goto ENDWHILE1
@sum
D = M
@ENDWHILE
D;JLE

// R2 = R2 + b
@b
D = M
@sum
M = M + D

// a --
@a
M = M - 1

// jump to WHILE
@a
D = M
@WHILE
D;JGT

(ENDWHILE)




