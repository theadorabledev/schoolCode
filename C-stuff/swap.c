#include <stdio.h>
#include <stdlib.h>
#include <time.h>
int main(){
	//Reverses an array using pointers.
	int arr[10];
	srand(time(NULL));
	for(int i = 0; i < 10; i++){
		arr[i] = rand();
		printf("%d ", arr[i]);
	}
	printf("\n");
	int * pointer = &arr[9];
	int newArr[10];
	int * otherPointer = &newArr[0];
	for(int i = 0; i < 10; i ++){
		*otherPointer = *pointer;
		pointer--;
		otherPointer++;
		printf("%d ", newArr[i]);
	}
	
}