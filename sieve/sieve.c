#include <stdio.h>
#include<stdbool.h>
#include <math.h>
#include <stdlib.h>
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
	bool primes[lenPrimes];
	for(int i = 2; i < (int) sqrt(lenPrimes) + 2; i++){
		if(!primes[i]){
			//printf("\nprime %d", i);
			for(int z = (i * i); z < lenPrimes - i; z += i){
				//printf("%d %d\n", i, z);
				primes[z] = true;
			}
			//printf("%d\n", i);
		}else{
			//printf("\ncomposite %d", i);
		}
	}
	int count = 5;
	int y = primes.Count(x => x);
	for(int i = 2; i < lenPrimes + 2; i++){
		if(!primes[i]){
			for(int z = 2; z < sqrt(i); z++){
				if(i % z == 0){
					printf("\ncomposite %d", i);
				}
			}
			count++;
			printf("%d\n", i);
			if(count == n ){
				printf("\n\n%d", i);
				
				break;
			}
		}else{
			//for(int z = 2; z < sqrt(i); z++){
				//if(i % z == 0){
					//printf("\ncomposite %d", i);
				//}
			//}
		}
	}
	printf("\n\n%d\n\n", lenPrimes);
	//printf("%d", lenPrimes);
    //while(argc--)
    //{
    //    printf("%s\n",argv[argc]);
    //}

return 0;
}
