#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/wait.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>


int argsCount=0;
int commandCount=0;
int commandLength[256];
char *errorToFile;
char *fromFile;
char *toFile;
_Bool waitforchild = 1;

void sig_handler(int signo)
{
	if (signo == SIGINT);
} 

void cd(char ** args)
{
	if(args[1] == NULL)
	{
		printf("cd, no arguments\n");
	}
	else
	{
		if(chdir(args[1]) != 0)
		{
			printf("No such directory\n");
		}
	}
}
void exec (char** args)
{
	int temp = 0;
	if(args[0] == NULL)
	{
		printf("No arguments!\n");
	}
	else if (!strcmp(args[0],"cd"))
	{
		cd(args);
	}
	else if (!strcmp(args[0],"exit"))
	{
		exit(0);
	}
	else
	{
	int numPipes = commandCount-1;
    int status;
    int i = 0;
    pid_t pid, wpid;



    int pipefds[2*numPipes];
	int testpipe[2];

    for(i = 0; i < (numPipes); i++){
        if(pipe(pipefds + i*2) < 0) {
            perror("couldn't pipe");
            exit(EXIT_FAILURE);
        }
		if(pipe(testpipe) < 0) {
            perror("couldn't pipe");
            exit(EXIT_FAILURE);
        }
    }
    int j = 0;
	int k1=0;
	int k2=1;
	int temp = 0 ;
    while(k1<commandCount)
	{
		
		char **command1= malloc(128 * sizeof(char*));
		int help=0;
		for(int h=0;h<commandLength[k1];h++)
		{
				command1[h]=args[temp];
				help++;
				temp++;
		}
		command1[help] = NULL;
        pid = fork();
        if(pid == 0) {

           
            if(k1<commandCount-1){
                if(dup2(pipefds[j + 1], 1) < 0){
                    perror("dup2");
                    exit(EXIT_FAILURE);
                }
				
            }

           
            if(k1 != 0 ){
                if(dup2(pipefds[j-2], 0) < 0){
                    perror(" dup2");
                    exit(EXIT_FAILURE);
                }
            }	
			if(toFile && k1 == commandCount-1)
			{
				int fd  = open(toFile, O_WRONLY | O_CREAT, 0777);
				if(dup2(fd, 1) < 0){
                    perror(" dup2");
                    exit(EXIT_FAILURE);
                }
				close (fd);
			}
			if(errorToFile && k1 == commandCount-1)
			{
				int fd  = open(errorToFile, O_WRONLY | O_CREAT, 0777);
				if(dup2(fd, 2) < 0){
                    perror(" dup2");
                    exit(EXIT_FAILURE);
                }
				close (fd);
			}
			if(fromFile)
			{
				int fd  = open(fromFile, O_RDONLY, 0777);
				if(dup2(fd, 0) < 0){
                    perror(" dup2");
                    exit(EXIT_FAILURE);
                }
				close (fd);
			}
			 for(i = 0; i < 2*numPipes; i++){
                    close(pipefds[i]);
            }
		    
            if( execvp(command1[0], command1) < 0 ){
				
                    perror("fail");
                    exit(EXIT_FAILURE);
            }
			exit(0);
        } else if(pid < 0){
            perror("error");
            exit(EXIT_FAILURE);
        }

        k1++;
        j+=2;
    }
	

	
    for(i = 0; i < 2 * numPipes; i++){
        close(pipefds[i]);
    }
	
	if(waitforchild)
	for(i = 0; i < numPipes + 1; i++)
		wait(&status);

}
		
	
	
}

char** getArgs()
{
	char **args = malloc(128 * sizeof(char*));
	char *line = malloc(64 * sizeof(char*));
	errorToFile = malloc(64 * sizeof(char*));
	fromFile = malloc(64 * sizeof(char*));
	toFile = malloc(64 * sizeof(char*));
	
	
	char **commands = malloc(128 * sizeof(char*));
	char *token;
	int position = 0;
	int c;
	argsCount = 0;
	commandCount = 0;
	int finish=0;
	int argsCountTemp=0;
	errorToFile = NULL ;
	fromFile = NULL ;
	toFile = NULL ;
	
	if(!args)
		fprintf(stderr, "[lsh]# Allocation error\n");
	
	while (!finish)  {
		c=getchar();
		if (c == EOF)
		{
			exit(0);
		}
      if (c == '\n') {
      line[position] = '\0';
	  finish=1;
    } else {
      line[position] = c;
    }
    position++;
	
	}
	if(line[strlen(line)-1] == '&')
	{
		token = strtok(line, "&");
		waitforchild = 0;
	}
	
	token = strtok(line, ">");
	if (token[strlen(token)-1]=='2')
	{
		token = strtok(NULL, " \n");
		errorToFile = token;
	}
	else
	{
		token = strtok(NULL, " \n");
		toFile = token;
	}
	
	token = strtok(line, "<");
	token = strtok(NULL, " \n");
	fromFile = token ;
	
	 
	token = strtok(line, "|");
	while( token != NULL ) {
      commands[commandCount] = token;
	  commandCount++;
	  token = strtok(NULL, "|");
   }
  
   
	
	for(int i=0;i<commandCount;i++)
	{
		argsCountTemp=0;
		token = strtok(commands[i], " \t\r\n\a");
		while( token != NULL ) 
		{
			args[argsCount] = token;
			argsCountTemp++;
			argsCount++;
			token = strtok(NULL, " \t\r\n\a");
		}
		commandLength[i]=argsCountTemp;
		args[argsCount] = NULL;
	}
	if(!strcmp(args[argsCount-1],"2"))
	{
		argsCount--;
		commandCount--;
	}
	 int temp=0;
	
   return args;
	
	

}

void lsh()
{	
char buff[FILENAME_MAX];

	char** args;
	getcwd( buff, FILENAME_MAX );
	printf("%s>",buff);
	while(1)
	{
		args = getArgs();
		exec(args);
		getcwd( buff, FILENAME_MAX );
		printf("%s>",buff);
	}
}

int main(int argc, char *argv[])
{
	if (signal(SIGINT, sig_handler) == SIG_ERR)
		printf("can't catch SIGINT\n");
    lsh();
	return 0;
}