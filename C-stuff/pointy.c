#include <stdlib.h>
#include <stdio.h>

int main(){
	unsigned int t = 2200000000;
	//2
	char *tp = &t;
	//pointer = & myInt;
	printf("Dec: %u\nHex: %x\n", t, t);
	printf("%x\n", tp);
	for(int d = 0; d < 4; d++){
		printf("Pointer %u   %hhx\n", d, tp);
		tp++;
	}
	printf("Dec: %u\nHex: %x\n", t, t);
	tp = &t;
	for(int d = 1; d < 5; d++){
		*tp += 1;
		printf("+%u - Dec: %u\nHex: %x\n", d, t, t);
		tp++;
	}
	tp = &t;
	for(int d = 1; d < 5; d++){
		*tp += 16;
		printf("+%u - Dec: %u\nHex: %x\n", (d * 16), t, t);
		tp++;
	}
}