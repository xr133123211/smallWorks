// openMP.cpp : 定义控制台应用程序的入口点。
//

#include "stdafx.h"
#include "stdlib.h"
#include "omp.h"
#include "time.h"
#include "math.h"


int _tmain(int argc, _TCHAR* argv[])
{
	
	clock_t st, et;
	st = clock();
	int num = 0;
	int n = 500000;
	int size = 1;
	
	int l = 1;	
	

	
	
	omp_set_num_threads(size);

#pragma omp parallel
	{
		int g = size;
		l++;
		int i = l;	
		int r = n;
	   
		for (; i <= r; i=i+g){
			
			int result = 1;
			int q = sqrt(double(i));
			for (int j = 2; j <= q; j++){
				if (i%j == 0) { result = -1; break; }
			}
			if (result == 1) {
				num++; //printf("%d\n",i); 
			}
			
		}
		et = clock();
		printf("now time is :　%d\n", et - st);
	}
	printf("total time is :　%d\n",et-st);
	printf("total num is : %d\n",num);
	getchar();
	return 0;
}

