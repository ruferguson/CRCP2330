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
// a = R0
// b = R1
// while(a > 0){
//   R2 = R2 + b
//   a --
// }

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