#include <string.h>
#include <unistd.h>
#include <stdio.h>
// gcc -lm -m32 myprintf.c -0 myprintf32

void *ptr;
char tmpchar [256];
void print_d(int a)
{
	int tab[256];
	int i=0;
	while(a)
	{
		tab[i] = a % 10;
		a /= 10;
		i++;
	}
	int c=0;
	for(int j = i-1; j>=0 ; j--)
	{
		tmpchar[c] = tab[j] + '0';
		c++;
	}
	tmpchar[c] = '\0';
	write(1, tmpchar, strlen(tmpchar));
}
void displayStack()
{
	for(int i=0;i<100;i++)
	{
		printf("addr = '%p', value = '%d'\n", ptr , *(int *)ptr);
		ptr = ptr + sizeof(char);
	}
}
void print_s(char* a)
{
	write(1, a, strlen(a));
}

void print_x(int a)
{
	int x = a;
	int i=1;
	int temp;
	char c[256];
	while(x!=0)
	{
		temp = x % 16;
		
		if(temp < 10)
			temp = temp + 48;
		else
			temp = temp + 55;
		
		c[i++] = temp;
		x = x / 16;
	}
	int count = 0;
	for(int j=i-1;j>0;j--)
	{
		tmpchar[count] = c[j];
		count ++;
	}
	tmpchar[count] = '\0';
	write(1, tmpchar, strlen(tmpchar));
}

void print_b(int a)
{
	int x;
	int i=1;
	char c[256];
	while(a != 0)
	{
		x = a%2;
		x = x+48;
		a=a/2;
		c[i++] = x;
	}
	int count = 0;
	for(int j=i-1;j>0;j--)
	{
		tmpchar[count] = c[j];
		count ++;
	}
	tmpchar[count] = '\0';
	write(1, tmpchar, strlen(tmpchar));
}



int myprintf(char *str, ...) {
	
    void *p = &str +  sizeof(char);
	ptr = &str;
	//displayStack();

	int i;
    for (i = 0; i < strlen(str); i++) {
        if (str[i] == '%') {
            switch (str[i+1]) {
                case 's':
					print_s(*((char**)p));		
                    p += sizeof(char*);
                    break;
                case 'd':
                    print_d(*((int*)p)); 
                    p += sizeof(int);
                    break;
                case 'x':
                    print_x(*((int*)p)); 
                    p += sizeof(int);
                    break;
				case 'b':
                    print_b(*((int*)p)); 
                    p += sizeof(int);
                    break;
            }
            i++;
        } else {
			tmpchar[0] = str[i];
			tmpchar[1] = '\0';
			write(1, tmpchar, strlen(tmpchar));
        }
    }
}


int main()
{
	myprintf("T1 = '%s', T2 = '%s', T3 = '%s'\n", "tekst1", "tekst2", "tekst3");
	myprintf("n1 = '%d', n2 = '%d', n3 = '%d'\n",1 ,2 ,3);
	myprintf("hex[256] = '%x', hex[15] = '%x', hex[333] = '%x'\n", 256, 15, 333);
	myprintf("bin[256] = '%b', bin[15] = '%b', bin[333] = '%b'\n", 256, 15, 333);
	
	myprintf("T1 = '%s', n1 = '%d', hex[13] = '%x', bin[77] = '%b'\n" , "tekst1" , 1345 , 13, 77);
	myprintf("bin[77] = '%b', hex[13] = '%x', n1 = '%d', T1 = '%s'\n" , 77 , 13 , 1345, "tekst1");
	
	myprintf("%x %x %x %x %x %x %x \n" ,  10 , 16 , 32 , 33 , 100 , 9 , 12);
	
	return 0;
}