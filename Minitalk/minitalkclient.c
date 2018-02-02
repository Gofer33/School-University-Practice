#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <netinet/in.h>
#include <netdb.h>
#include "socketlib.h"

/*
gcc minitalk.c -o minitalk
./minitalk localhost 8090
*/

int main(int argc, char **argv)
{
	char *hn;
	int port, fd, activity, valread;
	char buffer[1025];
	
	if (argc != 3)
	{
		fprintf(stderr, "usage: minitalk hostname port\n");
		exit(1);
	}
	hn = argv[1];
	port = atoi(argv[2]);
	if (port < 5000)
	{
		fprintf(stderr, "usage: minitalk hostname port (port > 5000) \n");
		exit(1);
	}

	fd = request_tcp_connection(hn,port);
	
	fd_set fds;

	
	
	while(1)
	{
        FD_ZERO(&fds);
  
        FD_SET(fd, &fds);
		FD_SET(0, &fds);
		    
       
		activity = select( fd + 1 , &fds , NULL , NULL , NULL);
		if (FD_ISSET(fd, &fds)) 
		{
			if ((valread = read( fd , buffer, 1024)) != 0)
            {
				buffer[valread] = '\0';
				printf("%s", buffer);
			}
		}
			
		if (FD_ISSET( 0, &fds)) 
        {
			if ((valread = read( 0 , buffer, 1024)) != 0)
			{
				buffer[valread] = '\0';
				send(fd, buffer, strlen(buffer), 0 );
			}
		}
	}
	
	return 0;
}