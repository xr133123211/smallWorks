// Parallel.cpp : 定义控制台应用程序的入口点。
//

#include "stdafx.h"

#include <stdio.h>  
#include <math.h>  
#include <windows.h>
#include <process.h>

#include <sys/timeb.h>
#include <time.h>
#include <string>


void linear(int n);
void winParallel(int n);


int gap;
const int size = 10;
HANDLE threads[size];
_timeb ts1, ts2;



void showTime(){
	_ftime(&ts2);
	long long t1 = (long long)ts1.time * 1000 + ts1.millitm;
	long long t2 = (long long)ts2.time * 1000 + ts2.millitm;
	int ti = t2 - t1;//获取时间间隔，ms为单位的
	printf("Time_______%d\n", ti);
}

DWORD WINAPI Fun1Proc(LPVOID m)
{
	//线程处理代码

	int l = (int)m;
	int r = l+gap;
	//printf("%d\n",l);
	for (int i = l+1; i <= r; i++){
		int k = sqrt((double)i);
		int result = 1;
		for (int j = 2; j <= k; j++)
		{
			if (i%j == 0) { result = 0; break; }
		}
		//if (result == 1) printf("%d\n", i);
	}
	
	return 0;
}

int _tmain(int argc, _TCHAR* argv[])
{

	_ftime(&ts1);
	int n = 100000;
	
	//linear(n);
	winParallel(n);
			

	getchar();

	return 0;
}

void winParallel(int n){
	int a[11];
	gap = n / size;
	int endHandle = 0;
	for (int i = 0; i < size; i++) {
		threads[i]=CreateThread(NULL, 0, Fun1Proc, (LPVOID)(i*gap), 0, NULL);
	}	
	WaitForMultipleObjects(size, threads, TRUE, INFINITE);
	showTime();
	

	
}


void linear(int n){
	
	for (int i = 1; i <= n; i++){
		int k = sqrt((double)i);
		int result = 1;
		for (int j = 2; j <= k; j++)
		{
			if (i%j == 0) { result = 0; break; }
		}
		//if (result == 1) printf("%d\n", i);
	}
	showTime();
	
}
