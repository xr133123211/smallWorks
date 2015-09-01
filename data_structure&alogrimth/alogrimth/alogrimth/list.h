#pragma once

#define maxsize 100
typedef struct {
	int data[maxsize];	
	int n;
}SeqList;

void reverse(SeqList &L);

void show(SeqList L);

void deleteValue(SeqList &L, int value);

void testList1();