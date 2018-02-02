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

#define MAX 100

_Bool startsWith(const char *pre, const char *str)
{
    size_t lenpre = strlen(pre),
           lenstr = strlen(str);
    return lenstr < lenpre ? 0 : strncmp(pre, str, lenpre) == 0;
}

char* func (char* buffer)
{
	char *tmp;
	tmp = strtok(buffer," ");
	tmp = strtok(NULL,"&");
	return tmp;
}

int main(int argc, char **argv)
{
    int new_fd , client_socket[MAX], activity, i , valread , sd, max_sd, port, sock, fd;
	char client_nick[MAX][MAX];
	int client_logged[MAX];
	char *hn, *token, *msg;
	fd_set fds;
	char buffer[1025];
	
	for (i = 0; i < MAX; i++) 
    {
        client_socket[i] = 0;
		client_logged[i] = 0;
		strcpy(client_nick[i], "");
    }
	
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

	sock = server_tcp_socket(hn, port);
	while(1)
	{
        FD_ZERO(&fds);
  
        FD_SET(sock, &fds);
        max_sd = sock;
         
        for ( i = 0 ; i < MAX ; i++) 
        {
            sd = client_socket[i];
             
            if(sd > 0)
                FD_SET( sd , &fds);
             
            if(sd > max_sd)
                max_sd = sd;
        }
		
        activity = select( max_sd + 1 , &fds , NULL , NULL , NULL);
          
        if (FD_ISSET(sock, &fds)) 
        {
            if ((new_fd = accept_tcp_connection(sock))<0)
            {
                perror("accept");
                exit(EXIT_FAILURE);
            }
				
			send(new_fd, "Welcome in MiniTalk!\nCurrently online:\n", strlen("Welcome in MiniTalk!\nCurrently online:\n"), 0 );
			for(int i =0; i < MAX ; i++)
			{
				if(client_socket[i] !=0 && client_logged[i] == 1)
				{
					send(new_fd, client_nick[i], strlen(client_nick[i]), 0 );
					send(new_fd, "\n", strlen("\n"), 0 );
				}
			}
			
			send(new_fd, "Enter nick\n", strlen("Enter nick\n"), 0 );
			
            for (i = 0; i < MAX; i++) 
            {
                if( client_socket[i] == 0 )
                {
                    client_socket[i] = new_fd;
					client_logged[i] = 0;
                    break;
                }
            }
        }
          
        for (i = 0; i < MAX; i++) 
        {
            sd = client_socket[i];
            if (FD_ISSET( sd , &fds)) 
            {
                if ((valread = read( sd , buffer, 1024)) == 0)
                {
                    close( sd );
                    client_socket[i] = 0;
					client_logged[i] = 0;
					strcpy(client_nick[i], "");
                }
                else
                {	
					valread--;
                    buffer[valread] = '\0';
										
					if(client_logged[i] == 1)
					{
						int sent = 0;
						for(int j=0;j<MAX;j++)
						{
								if (startsWith(client_nick[j],buffer) && client_socket[j] != 0 && client_logged[j] == 1)
								{
									token = func(buffer);
									char mes[256] ;
									strcpy(mes,client_nick[i]);
									strcat(mes,": ");
									strcat(mes, token);
									strcat(mes, "\n");
									send(client_socket[j] , mes, strlen(mes), 0 );
									sent = 1;
								}
						}
						if (sent == 0)
						{
							send(client_socket[i] , "MESSAGE NOT SENT\n", strlen("MESSAGE NOT SENT\n"), 0 );
						}
					}
					else
					{
						strcpy(client_nick[i], buffer);
						client_logged[i] = 1;
					}
                }
            }
        }
	}
	close(sock);
	return 0;
}

