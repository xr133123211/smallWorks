/*
Practices in CSAPP version II 
code by grayZANE  
github:https://github.com/xr133123211/codeForCSAPP
mail:zouyuan.iconsultnju@gmail.com
*/
#include<stdio.h>

//practice 2.55 compile and run showBytes on different machines 
//practice 2.56 different sample values
//practice 2.57 showInt,show Double,show longint 
typedef unsigned char * byte_pointer;

void showBytes(byte_pointer start,  int len){
	int i;
	for (i=0;i<len;i++){
		printf("%.2x",start[i]);
	}
	printf("\n");
	
}

void showInt(int x){
	showBytes((byte_pointer) &x,sizeof(int) );	
}

/*test2.55

int  main(){
	showInt(255);
	return 0;
}

*/


//practice2.58 return 1 if machine is big_endian else return 0
int is_big_endian(){
	int x = 0xf0;
	byte_pointer test = (byte_pointer) &x;
	return (0==*test);
}

/*test2.58
int main(){
	int result = is_big_endian();
	printf("%d",result);
	return 0;
}
*/

//practice2.59 
//write c expressions including the least important byte of y and remaining of x

int practice2_59(int x,int y){
	int base = ~0xff;	
	x = x&base;
	y = y&0xff;
	return x|y; 	
}


/*test2.59
int main(){
	int x = 0x89ABCDEF;
	int y = 0x76543210;
	printf("0x%x should equals 0x89ABCD10",practice2_59(x,y));
}
*/

//practice2.60 replace byte whose id is i with byte b
unsigned put_byte(unsigned x,unsigned char b,int i){
	unsigned base = 0xff;
	unsigned putB = (unsigned)b;
	base<<(8*i);
	putB<<(8*i);
	base = ~base;
	x = x&base;
	x = x|putB;
	return x;
}

/*test2.60
int main(){
	
	printf("replace_byte(0x12345678,0xAB,2)-->0x12AB5678 to be %x\n",put_byte(0x12345678,0xAB,2));
	printf("replace_byte(0x12345678,0xAB,0)-->0x123456AB to be %x\n",put_byte(0x12345678,0xAB,0));
	
	return 0;
}
*/

//practice2.61 just for int type's length equals 4byte
int judgeExpression(int x,char expr){
	
	printf("test %d %c = ",x,expr);
	int result;
	
	int y = ~x;	
	int x1 = x&0xff000000;
	int y1 = (x&0x000000ff)^0xff;	
	switch(expr){
	case('A'):	
	result=x&&1;
	break;		
	case('B'):
	result=y&&1;
	break;		
	case('C'):	
	result=x1&&1;
	break;		
	case('D'):
	result=y1&&1;
	break;		
	default:
	printf("error\n");
	result = -1;
	break;	
	printf("%d\n",result);
	}
	
	
	
}


//test 2.61
/*

*/
int main(){
	judgeExpression(3,'A');
	judgeExpression(3,'B');
	judgeExpression(3,'C');
	judgeExpression(3,'D');
	judgeExpression(0xffffffff,'A');
	judgeExpression(0xffffffff,'B');
	judgeExpression(0xffffffff,'C');
	judgeExpression(0xffffffff,'D');
	
	return 0;
}


//practice 2.62

int int_shift_arelogical(){
	int size = sizeof(int)<<3;
	int x = 0xff<<(size-8);
	x>>(size-1);
	return (x+1)>>1; 
	
}
//test2.62
/*
int main(){
	printf("%d\n",int_shift_arelogical());
	
}
*/


//practice2.63
/*
unsigned sra(int x,int k){
	int xsrl =  (unsigned)srl(x,k);
}

unsigned srl(int x,int k){
	int xsra =  (unsigned)x>>k;
}
*/
/*
int main(){
	
} 
*/
