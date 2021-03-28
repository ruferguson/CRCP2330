// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Mult.asm

// Multiplies R0 and R1 and stores the result in R2.
// (R0, R1, R2 refer to RAM[0], RAM[1], and RAM[2], respectively.)
//
// This program only needs to handle arguments that satisfy
// R0 >= 0, R1 >= 0, and R0*R1 < 32768.

// pseudo code
//   a = R0
//   b = R1
//   (WHILE1)
//    if (a > 0) goto ENDWHILE1
//       R2 = R2 + b
//       a --
//       jump to WHILE1
//   (ENDWHILE1)

// set a = R0
@R0
D = A
@a
M = D 

// set b = R1
@R1
D = A
@b
M = D

@sum
M = 0

(WHILE)
@sum
D = M
@ENDWHILE
D;JGT

@b
D = M
@sum
M = M + D

@sum
M=M+1

@WHILE
D;JLE

(ENDWHILE)




