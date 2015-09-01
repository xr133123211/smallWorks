#include <iostream>

#include "list.h"
using namespace std;

extern void testList1() {
	SeqList list;
	list.data[1] = 100;
	list.data[2] = 101;
	list.data[0] = 103;
	list.data[3] = 104;
	list.n = 4;
	show(list);
	//reverse(list);
	deleteValue(list, 100);
	cout << endl;
	show(list);
	
}
//倒置顺序表
extern void reverse(SeqList &L) {
	int N = L.n;
	int l = N/2+N%2-1;

	int swap;
	for (int i = 0; i <= l; i++) {
		swap = L.data[i];
		L.data[i] = L.data[N-1-i];
		L.data[N-i-1] = swap;
	}
}

//打印顺序表
extern void show(SeqList L) {
	int N = L.n;
	for (int i = 0; i < L.n; i++)
		cout << L.data[i] << endl;
}


//删除顺序表中对应的所有值
extern void deleteValue(SeqList &L, int value) {
	int i = 0;
	int j = 0;
	for (int i = 0; i < L.n; i++) {
		if (L.data[i] != value) {
			L.data[j] = L.data[i];
			j++;
		}
		
	}
	L.n = j;

}
