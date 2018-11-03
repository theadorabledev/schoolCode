#include <stdio.h>
#include<stdbool.h>
#include <math.h>
#include <stdlib.h>
#include <time.h>
//unsigned int log(unsigned int n)
//{
//   return (n > 1)? 1 + log(n/2): 0;
//}

int pi(int n){
	return (int)((n * (log(n) + log(log(n)) -1 + ((log(log(n)) -2 )/log(n)) -( (pow(log(log(n)), 2) - (6 * log(log(n))) + 11 ) /(2* pow(log(n), 2)))  + (1/ pow(log(n), 2)))) * 1.05); 
	
}

int main(int argc,char *argv[]){	
	int n = atoi( argv[1]);
	int lenPrimes = pi(n);
	//define lenPrimes pi(n)
	_Bool primes[lenPrimes] ;
	time_t start, finish;
	start = time(0);
	for(int i =0; i < lenPrimes; i++){
		primes[i] = false;
	}
	//printf("%d\n", lenPrimes);
	for(int i = 2; i < (int) sqrt(lenPrimes) + 2; i++){
		if(!primes[i]){
			//printf("\nprime %d", i);
			//printf("%d %d\n", i, i*i);
			for(int z = (i * i); z < lenPrimes ; z += i){
				primes[z] = true;
				//if(primes[3203]){printf("??? %d %d\n", i, z);}

			}
			//printf("%d\n", i);
		}
	}
	int count = 0;
	for(int i = 2; i < lenPrimes + 2; i++){
		if(!primes[i]){
			count++;
			//printf("%d %d\n",count, i);
			if(count == n){
				printf("Prime number %d is %d" ,n, i);
				break;
			}
		}
	}

    finish = time(0);
    //printf("\nTime for sort %d: ", ((double)(finish - start))/CLOCKS_PER_SEC);
	printf("\nTime for sort %d: ", (double)(finish - start));
return 0;
}
