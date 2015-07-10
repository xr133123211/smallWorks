#include <pthread.h>
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <math.h>
#include <time.h>


int gap;
int n;
clock_t st, etime;

void* thread_function(int args){

	int i = args;
	int result = 0;
	int g = gap;
	int j;
	int end = n;
	for (; i <= end; i = i + g){
		int q = sqrt((double)i);
		result = 0;
		for (j = q; j>1; j--) {
			if (i%j == 0) {
				result = -1;
				break;
			}
		}
		if (result == 0);
		//    printf("%d\n",i);
	}

	//time(&etime);
	etime = clock();
	printf("%u\n",etime-st);
	return NULL;

}

void lnear(){
	gap = 1;
	thread_function(1);
}

int main(){

	n = 1000000;




	st = clock();

	//lnear();

	pthread_t mythread[3];
	gap = 3;
	int i = 0;
	for (i = 0; i<gap; i++){
		if (pthread_create(&(mythread[i]), NULL, thread_function, i + 2))
		{
			printf("error creating thread.");
			abort();
		}

	}
	for (i = 0; i<gap; i++){
		if (pthread_join(mythread[i], NULL)) {
			printf("error joining thread.");
			abort();
		}
	}

	//printf("%u\n", etime - st);
	return 0;

}