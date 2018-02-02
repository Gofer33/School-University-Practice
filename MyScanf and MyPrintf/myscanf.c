#include <string.h>
#include <unistd.h>
#include <stdio.h>
#include <math.h>
#include <stdlib.h>

//gcc -lm -m32 myscanf.c -o myscanf32
void *ptr;

void scan_d(int *w)
{
	char number[256];
	char buffer = 'a';

	int i=0;
	while(buffer != '\n')
	{	
		read(0, &buffer, 1);
		number[i] = buffer;
		i++;
	}
	number[i-1] = '\0';
	int j=0;
	int power = 0;
	for(int i = strlen(number) - 1 ; i>=0 ; i--)
	{
		j=j+(number[i] - '0') * pow(10,power);
		power ++ ;
	}
	*w = j;
}

void scan_s(char **a)
{
	*a = malloc(5*sizeof(char));
	char word[256] = "";
	char buffer = 'a';
	
	int i=0;
	while(buffer != '\n')
	{	
		read(0, &buffer, 1);
		word[i] = buffer;
		i++;
	}
	word[i-1] = '\0';
	strcpy(*a,word);
}

void scan_b(char **a)
{
	*a = malloc(5*sizeof(char));
	char number[256];
	char buffer = 'a';
	char tmpchar [256] = "";
	
	int i=0;
	while(buffer != '\n')
	{	
		read(0, &buffer, 1);
		number[i] = buffer;
		i++;
	}
	number[i-1] = '\0';
	
	int j=0;
	int power = 0;
	for(int i = strlen(number) - 1 ; i>=0 ; i--)
	{
		j=j+(number[i] - '0') * pow(10,power);
		power ++ ;
	}
	
	int x;
	int z=1;
	char c[256];
	while(j != 0)
	{
		x = j%2;
		x = x+48;
		j=j/2;
		c[z++] = x;
	}
	int count = 0;
	for(int j=z-1;j>0;j--)
	{
		tmpchar[count] = c[j];
		count ++;
	}
	tmpchar[count] = '\0';
	
	strcpy(*a,tmpchar);
}
void scan_x(char **a)
{
	*a = malloc(5*sizeof(char));
	char number[256];
	char buffer = 'a';
	char tmpchar [256] = "";
	
	int i=0;
	while(buffer != '\n')
	{	
		read(0, &buffer, 1);
		number[i] = buffer;
		i++;
	}
	number[i-1] = '\0';
	
	int j=0;
	int power = 0;
	for(int i = strlen(number) - 1 ; i>=0 ; i--)
	{
		j=j+(number[i] - '0') * pow(10,power);
		power ++ ;
	}
	
	int x;
	int z=1;
	char c[256];
	while(j != 0)
	{
		x = j%16;
		if(x < 10)
			x = x + 48;
		else
			x = x + 55;
		j=j/16;
		c[z++] = x;
	}
	int count = 0;
	for(int j=z-1;j>0;j--)
	{
		tmpchar[count] = c[j];
		count ++;
	}
	tmpchar[count] = '\0';

	strcpy(*a,tmpchar);
}

void displayStack()
{
	for(int i=0;i<100;i++)
	{
		printf("ADDR = '%p' , VALUE = '%p'\n",ptr, *(int *)ptr);
		ptr = ptr + sizeof(char);
	}
}

int myscanf(char *str, ...) {
    void *p = &str + sizeof(char);
	ptr = &str;
	//displayStack();
	
	int i;
    for (i = 0; i < strlen(str); i++) {
        if (str[i] == '%') {
            switch (str[i+1]) {
                case 's':
					scan_s((char **)(*((char**)p)));		
                    p += sizeof(char**);
                    break;
                case 'd':
                    scan_d((int *)(*((int*)p))); 
                    p += sizeof(int*);
                    break;
                case 'x':
					scan_x((char **)(*((char**)p)));		
                    p += sizeof(char**);
                    break;
				case 'b':
					scan_b((char **)(*((char**)p)));		
                    p += sizeof(char**);
                    break;
            }
            i++;
        } else {			
        }
    }
}

int main()
{
	int a=1;
	int b=2;
	int c=3;
	
	char * d = "T1";
	char * e = "T2";
	char * f = "T3";
	
	char * bin1 = "";
	char * bin2 = "";
	char * bin3 = "";
	
	char * hex1 = "";
	char * hex2 = "";
	char * hex3 = "";
	
	
	myscanf("%x%d%b%s", &hex1,&a,&bin1,&d);
	
	printf("NEW HEX1 VALUE = '%s'\n", hex1);
	printf("NEW A VALUE = '%d'\n", a);
	printf("NEW BIN1 VALUE = '%s'\n", bin1);
	printf("NEW D VALUE = '%s'\n", d);

	return 0;
}