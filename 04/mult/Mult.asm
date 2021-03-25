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

/* pseudo code for while
   sum = 0;
   i = 1;
   (WHILE1)
      if (i - 10 >= 0) goto ENDWHILE1
      sum = sum + i;
      i = i + 1;
      jump to WHILE1
   (ENDWHILE1)
*/

@sum
M = 0
@i
M = 1

