

@SCREEN
D = A
@addr
M = D

(KEYCHECK)

@KEYPRESSED
D = M

// go to BLACK if key pressed
@BLACK
D; JGT

// go to WHITE if no key pressed
@WHITE
D;JEQ

//(continuously loop)
@KEYCHECK
0;JMP