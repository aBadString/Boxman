/**************************************
		      ������
		<������ : �侢��>
**************************************/

//ͷ�ļ�
#include <stdio.h>
#include <windows.h>
#include <conio.h>

//�궨��
#define M 10
#define N 10

//�ṹ�嶨��
typedef struct Map
{
	int plat[M][N];	//��ͼ
	int num;		//ÿһ�ص�������
	int pass;		//�ؿ���
}MAP;

//����ԭ������
void HideCursor(void);	//���ع��
void pos(int x, int y);	//��λ���
int  Pass(int pass);	//�ؿ�����
void DrawMap(void);		//������ͼ
void ChangeMap(int x, int y);	//�ı��ͼ
void PlayGame(void);	//������Ϸ
int  End(void);			//�ж���Ϸ����

//ȫ�ֱ���--��ͼ
static MAP map  = {{0}, 0, 0};
static MAP mapc = {{0}, 0, 0};	//��ͼ�������������¿�ʼ��Ϸ

//��������
int main(void)
{	
	system("mode con cols=48 lines=19");	//�����������ڴ�С
	HideCursor();
	map.pass = 1;
	
	while(1)
	{
		if(Pass(map.pass))		//���ݲ������ùؿ���Ϣ
		{
			break;				//���ļ�ʧ��	
		}
		DrawMap();				//������ͼ

		while(1)
		{
			PlayGame();			//������Ϸ
			if(End())			//�ж��Ƿ�ͨ��
			{
				map.pass++;		//���ؿ�������һ��
				pos(0, 14);	printf("���أ����س����Լ���\n");
				getchar();
				break;
			}
		}

		if(map.pass == 10)		//�ж��Ƿ�ͨ��ȫ���ؿ�
		{
			system("cls");
			pos(0,15);	printf("��ϲ��ͨ�������йؿ�!\n���س������˳�\n");
			getchar();
			break;
		}
	}
	
	return 0;
}


//���ع��
void HideCursor(void)
{
	CONSOLE_CURSOR_INFO cursor_info = {1, 0}; 
	SetConsoleCursorInfo(GetStdHandle(STD_OUTPUT_HANDLE), &cursor_info);
}

//��λ���
//��  �ܣ�����궨�����������
//��  ����������������ʾһ������
void pos(int x, int y)
{	
	COORD coord = {x, y};
	SetConsoleCursorPosition(GetStdHandle(STD_OUTPUT_HANDLE), coord);
}

//�ؿ�����
//��    �� �����ݲ��� pass ����Ӧ�Ĵ��ݸ�map
int Pass(int pass)
{
	int i, j;
	FILE *fp;
	
	fp = fopen("map.txt", "r");	//�ؿ���Ϣ�ļ�map.txt
	if(fp == NULL)	//
	{
		system("cls");
		printf("map.txt ��ʧ��!\n��������˳�");
		getch();
		return 1;
	}
	else
	{//-----else
		for(i = 0; i < pass; i++)//ͨ��ѭ����������pass�����ݿ�
		{
			fread(&map, sizeof(map), 1, fp);
		}
		fclose(fp);	//�ر�map.txt�ļ�
		
		//����һ�������Ա����¿�ʼ
		for(i = 0; i < M; i++)
		{
			for(j = 0; j < N; j++)
			{
				mapc.plat[i][j] = map.plat[i][j];	
			}
		}
		return 0;
	}//-----else
}

//������ͼ
void DrawMap(void)
{
	int i, j;
	
	system("cls");
	printf("��%d��\n", map.pass);
	//��������map.plat[i][j]����ӡ��ͼ
	for(i = 0; i < M; i++)
	{
		for(j = 0; j < N; j++)
		{
			switch(map.plat[i][j])
			{
			case 0:				//�յ�
				printf("  ");
				break;
			case 1:				//ǽ��
				printf("��");			//ALT+41462
				break;
			case 2:				//Ŀ��
				printf("��");			//ALT+41454
				break;
			case 3:				//����
				printf("��");			//ALT+41461
				break;
			case 4:				//���
				printf("��");			//ALT+0165
				break;
			case 5:				//����+Ŀ��
				printf("��");			//ALT+41455
				break;
			case 6:				//���+Ŀ��
				printf("��");
				break;		
			}
		}
		printf("\n");			//��ӡ��һ�У����д�ӡ��һ��
	}
	printf("w a s d ������Ϸ\n");
	printf("��1�����¿�ʼ\n");
}

//�ı��ͼ
//������map.plat[x][y]�����±�
void ChangeMap(int x, int y)
{
	pos(y*2, x+1);	//map.plat[x][y]�����±����Ӧͼ������λ������Ĺ�ϵ

	switch(map.plat[x][y])
	{
	case 0:				//�յ�
		printf("  ");
		break;
	case 1:				//ǽ��
		printf("��");			//ALT+41462
		break;
	case 2:				//Ŀ��
		printf("��");			//ALT+41454
		break;
	case 3:				//����
		printf("��");			//ALT+41461
		break;
	case 4:				//���
		printf("��");			//ALT+0165
		break;
	case 5:				//����+Ŀ��
		printf("��");			//ALT+41455
		break;
	case 6:				//���+Ŀ��
		printf("��");
		break;		
	}
}


//������Ϸ
//ͨ�� w a s d ��������ƶ�
//��1 �����¿�ʼ
void PlayGame(void)
{
	char ch;
	int i, j, x, y, a, b;
	
	for(i = 0; i < M; i++)
	{
		for(j = 0; j < N; j++)
		{
			if(map.plat[i][j] == 4 || map.plat[i][j] == 6)	//Ѱ����ҵ�λ������
			{
				x = i;	y = j;
			}
		}
	}
	
	ch = getch();

	switch(ch)		//�㷨����������������������ĵ�.rtf��
	{
	case 'w'://��
		a = -1;
		b = 0;
		break;		
	case 's'://��
		a = 1;
		b = 0;
		break;		
	case 'a'://��
		a = 0;
		b = -1;
		break;		
	case 'd'://��
		a = 0;
		b = 1;
		break;
	default:
		a = 0;
		b = 0;
		break;
	}

	if((a != 0) || (b != 0))
	{
		//С������
		if(map.plat[x+a][y+b] == 0 || map.plat[x+a][y+b] == 2)
		{
			map.plat[x+a][y+b] += 4;		ChangeMap(x+a, y+b);
			map.plat[x][y] -= 4;			ChangeMap(x, y);
		}
		//С��������
		if(map.plat[x+a][y+b] == 3 || map.plat[x+a][y+b] == 5)
		if(map.plat[x+2*a][y+2*b] == 0 || map.plat[x+2*a][y+2*b] == 2)
		{
			map.plat[x+2*a][y+2*b] += 3;	ChangeMap(x+2*a, y+2*b);
			map.plat[x+a][y+b] += 1;		ChangeMap(x+a, y+b);
			map.plat[x][y] -= 4;			ChangeMap(x, y);
		}

	}
	if(ch == '1')		//���¿�ʼ���ؿ�
	{
		for(i = 0; i < M; i++)
		{
			for(j = 0; j < N; j++)
			{
				map.plat[i][j] = mapc.plat[i][j];
			}
		}
		DrawMap();
	}
}

//�ж���Ϸ����
//ͨ�ط���1�����򷵻�0
int End(void)
{
	int i, j, n;
	
	n = 0;
	for(i = 0; i < M; i++)
	{
		for(j = 0; j < N; j++)
		{
			if(map.plat[i][j] == 5)	//�ҵ���һ����Ŀ��λ�õ�����
			{
				n++;
			}
		}
	}
	if(n == map.num)		//�ж�����Ŀ��λ�õ������������Ƿ����ͨ������
		return 1;			//ͨ���򷵻�1
	else
		return 0;
}


