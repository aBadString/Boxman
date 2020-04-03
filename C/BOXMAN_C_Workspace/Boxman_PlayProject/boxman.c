/**************************************
		      推箱子
		<开发者 : 冷劲松>
**************************************/

//头文件
#include <stdio.h>
#include <windows.h>
#include <conio.h>

//宏定义
#define M 10
#define N 10

//结构体定义
typedef struct Map
{
	int plat[M][N];	//地图
	int num;		//每一关的箱子数
	int pass;		//关卡数
}MAP;

//函数原型声明
void HideCursor(void);	//隐藏光标
void pos(int x, int y);	//定位光标
int  Pass(int pass);	//关卡设置
void DrawMap(void);		//创建地图
void ChangeMap(int x, int y);	//改变地图
void PlayGame(void);	//控制游戏
int  End(void);			//判断游戏结束

//全局变量--地图
static MAP map  = {{0}, 0, 0};
static MAP mapc = {{0}, 0, 0};	//地图副本，便于重新开始游戏

//主调函数
int main(void)
{	
	system("mode con cols=48 lines=19");	//设置批处理窗口大小
	HideCursor();
	map.pass = 1;
	
	while(1)
	{
		if(Pass(map.pass))		//根据参数设置关卡信息
		{
			break;				//打开文件失败	
		}
		DrawMap();				//创建地图

		while(1)
		{
			PlayGame();			//控制游戏
			if(End())			//判断是否通关
			{
				map.pass++;		//将关卡调至下一关
				pos(0, 14);	printf("过关！按回车键以继续\n");
				getchar();
				break;
			}
		}

		if(map.pass == 10)		//判断是否通过全部关卡
		{
			system("cls");
			pos(0,15);	printf("恭喜你通过了所有关卡!\n按回车键以退出\n");
			getchar();
			break;
		}
	}
	
	return 0;
}


//隐藏光标
void HideCursor(void)
{
	CONSOLE_CURSOR_INFO cursor_info = {1, 0}; 
	SetConsoleCursorInfo(GetStdHandle(STD_OUTPUT_HANDLE), &cursor_info);
}

//定位光标
//功  能：将光标定于输入的坐标
//参  数：两个整数，表示一个坐标
void pos(int x, int y)
{	
	COORD coord = {x, y};
	SetConsoleCursorPosition(GetStdHandle(STD_OUTPUT_HANDLE), coord);
}

//关卡设置
//功    能 ：根据参数 pass 将对应的传递给map
int Pass(int pass)
{
	int i, j;
	FILE *fp;
	
	fp = fopen("map.txt", "r");	//关卡信息文件map.txt
	if(fp == NULL)	//
	{
		system("cls");
		printf("map.txt 打开失败!\n按任意键退出");
		getch();
		return 1;
	}
	else
	{//-----else
		for(i = 0; i < pass; i++)//通过循环最后读到第pass个数据块
		{
			fread(&map, sizeof(map), 1, fp);
		}
		fclose(fp);	//关闭map.txt文件
		
		//留下一个副本以便重新开始
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

//创建地图
void DrawMap(void)
{
	int i, j;
	
	system("cls");
	printf("第%d关\n", map.pass);
	//遍历数组map.plat[i][j]，打印地图
	for(i = 0; i < M; i++)
	{
		for(j = 0; j < N; j++)
		{
			switch(map.plat[i][j])
			{
			case 0:				//空地
				printf("  ");
				break;
			case 1:				//墙壁
				printf("■");			//ALT+41462
				break;
			case 2:				//目标
				printf("☆");			//ALT+41454
				break;
			case 3:				//箱子
				printf("□");			//ALT+41461
				break;
			case 4:				//玩家
				printf("￥");			//ALT+0165
				break;
			case 5:				//箱子+目标
				printf("★");			//ALT+41455
				break;
			case 6:				//玩家+目标
				printf("￥");
				break;		
			}
		}
		printf("\n");			//打印完一行，换行打印下一行
	}
	printf("w a s d 控制游戏\n");
	printf("按1以重新开始\n");
}

//改变地图
//参数：map.plat[x][y]数组下标
void ChangeMap(int x, int y)
{
	pos(y*2, x+1);	//map.plat[x][y]数组下标与对应图形所在位置坐标的关系

	switch(map.plat[x][y])
	{
	case 0:				//空地
		printf("  ");
		break;
	case 1:				//墙壁
		printf("■");			//ALT+41462
		break;
	case 2:				//目标
		printf("☆");			//ALT+41454
		break;
	case 3:				//箱子
		printf("□");			//ALT+41461
		break;
	case 4:				//玩家
		printf("￥");			//ALT+0165
		break;
	case 5:				//箱子+目标
		printf("★");			//ALT+41455
		break;
	case 6:				//玩家+目标
		printf("￥");
		break;		
	}
}


//控制游戏
//通过 w a s d 控制玩家移动
//按1 以重新开始
void PlayGame(void)
{
	char ch;
	int i, j, x, y, a, b;
	
	for(i = 0; i < M; i++)
	{
		for(j = 0; j < N; j++)
		{
			if(map.plat[i][j] == 4 || map.plat[i][j] == 6)	//寻找玩家的位置坐标
			{
				x = i;	y = j;
			}
		}
	}
	
	ch = getch();

	switch(ch)		//算法解释详见“需求分析及设计文档.rtf”
	{
	case 'w'://上
		a = -1;
		b = 0;
		break;		
	case 's'://下
		a = 1;
		b = 0;
		break;		
	case 'a'://左
		a = 0;
		b = -1;
		break;		
	case 'd'://右
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
		//小人行走
		if(map.plat[x+a][y+b] == 0 || map.plat[x+a][y+b] == 2)
		{
			map.plat[x+a][y+b] += 4;		ChangeMap(x+a, y+b);
			map.plat[x][y] -= 4;			ChangeMap(x, y);
		}
		//小人推箱子
		if(map.plat[x+a][y+b] == 3 || map.plat[x+a][y+b] == 5)
		if(map.plat[x+2*a][y+2*b] == 0 || map.plat[x+2*a][y+2*b] == 2)
		{
			map.plat[x+2*a][y+2*b] += 3;	ChangeMap(x+2*a, y+2*b);
			map.plat[x+a][y+b] += 1;		ChangeMap(x+a, y+b);
			map.plat[x][y] -= 4;			ChangeMap(x, y);
		}

	}
	if(ch == '1')		//重新开始本关卡
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

//判断游戏结束
//通关返回1，否则返回0
int End(void)
{
	int i, j, n;
	
	n = 0;
	for(i = 0; i < M; i++)
	{
		for(j = 0; j < N; j++)
		{
			if(map.plat[i][j] == 5)	//找到了一个在目标位置的箱子
			{
				n++;
			}
		}
	}
	if(n == map.num)		//判断所有目标位置的在箱子数量是否等于通关条件
		return 1;			//通关则返回1
	else
		return 0;
}


