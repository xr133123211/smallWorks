#pragma once
#include <iostream>

typedef struct node{
	int data;
	node *link;
}LinkNode,*LinkList;


using namespace std;
void testLinkList() {
	LinkNode *L = new LinkNode;
	L->data = 1;
	cout << L->data << endl;
}