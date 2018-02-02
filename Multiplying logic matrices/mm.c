// gcc -lpthread zd2.c -o zd2
#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <stdlib.h>


int a[128][128], b[128][128], c[128][128];
int currentRow = 0, rows = 0, columnsb = 0, columnsa = 0;
pthread_mutex_t lock;

void generateMatrix( int rows, int columns, int matrix[][columns])
{
	for(int i = 0 ; i<rows ; i++)
	{
		for(int j=0 ; j<columns ; j++)
		{
			matrix[i][j]= rand()%2;
		}
	}
}

void displayMatrix(int rows, int columns, int matrix[][columns])
{
	for(int i = 0 ; i<rows ; i++)
	{
		for(int j=0 ; j<columns ; j++)
		{
			printf("%d ",matrix[i][j]);
		}
		printf("\n");
	}
}

void *compute(void *v)
{
	int *t = (int *) v;
	int one = 0;
	int row=0;
	int exit=0;
	while(1)
	{		
		pthread_mutex_lock(&lock);
		
			if(currentRow < rows)
				row = currentRow++;
			else
			{
				exit = 1;
			}
			
		pthread_mutex_unlock(&lock);
		if(exit)
			pthread_exit(NULL);
			
		//printf("THREAD %d STARTS COMPUTING %d ROW\n",*t,row);
	
		for(int k=0;k<columnsb; k++)
		{
			for( int i = 0; i < columnsa ; i++)
			{
				//printf("%d THRED IS COMPUTING a[%d][%d] * b[%d][%d]\n",*t,row,i,i,k);
				if(a[row][i] == 1)
				{
					if(b[i][k] == 1)
					{
						one = 1;
						break;
					}
				}
			}
			c[row][k] = one;
			one = 0;
		}
	}
}

void fill(int rows1, int columns1, int rows2, int columns2, int matrixA[][columns1], int matrixB[][columns2])
{
	for(int i=0;i<rows1;i++)
	{
		for(int j=0;j<columns1;j++)
		{
			a[i][j] = matrixA[i][j];
		}
	}
	
	for(int i=0;i<rows2;i++)
	{
		for(int j=0;j<columns2;j++)
		{
			b[i][j] = matrixB[i][j];
		}
	}
}


int main()
{
	srand( time( NULL ) );
	int rows1;
	int rows2;
	int columns1;
	int columns2;
	int threads;
	pthread_t tids[threads];
	void *retval;
	
	printf("Enter number of rows and columns of first matrix\n");
	scanf("%d",&rows1);
	scanf("%d",&columns1);
	
	printf("Enter number of rows and columns of second matrix\n");
	scanf("%d",&rows2);
	scanf("%d",&columns2);
	
	if( columns1 != rows2 )
	{
		printf("Number of columns or rows is wrong!\n");
		return 0;
	}
	
	int matrixA[rows1][columns1];
	int matrixB[rows2][columns2];
	
	printf("Enter number of threads\n");
	scanf("%d",&threads);
	
	if (pthread_mutex_init(&lock, NULL) != 0)
    {
        printf("Mutex\n");
        return 1;
    }

	generateMatrix(rows1, columns1, matrixA);
	generateMatrix(rows2, columns2, matrixB);

	rows=rows1;
	columnsb=columns2;
	columnsa=columns1;

	fill(rows1, columns1, rows2, columns2, matrixA, matrixB);
		
	int vals[threads];

	for (int i=0;i<threads;i++)
	{
		vals[i] = i;
		pthread_create(tids+i, NULL, compute, vals+i);
	}

	for(int i=0;i<threads;i++)
	{
		pthread_join(tids[i], &retval);
	}
	printf("\n\n");
	displayMatrix(rows1, columns1, matrixA);
	printf("*\n");
	displayMatrix(rows2, columns2, matrixB);
	printf("=\n");
	
	for(int i=0;i< rows1 ; i++)
	{
		for(int j=0;j<columns2;j++)
		{
			printf("%d ",c[i][j]);
		}
		printf("\n");
	}
	
	pthread_mutex_destroy(&lock);
		
	return 0;
}