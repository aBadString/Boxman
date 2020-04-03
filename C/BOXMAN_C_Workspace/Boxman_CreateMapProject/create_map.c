#include <stdio.h>


typedef struct Map
{
	int plat[10][10];	//地图
	int num;		//每一关的箱子数
	int pass;		//关卡数
}MAP;

MAP map[9] = {
//map[1]
{
{{0,0,0,1,1,1,0,0,0,0},
{0,0,0,1,2,1,0,0,0,0},
{1,1,1,1,0,1,0,0,0,0},
{1,2,3,0,3,1,1,1,0,0},
{1,1,1,4,3,0,2,1,0,0},
{0,0,1,3,1,1,1,1,0,0},
{0,0,1,2,1,0,0,0,0,0},
{0,0,1,1,1,0,0,0,0,0},
{0,0,0,0,0,0,0,0,0,0},
{0,0,0,0,0,0,0,0,0,0}}
, 4, 1},
//map[2]
{
{{1,1,1,1,1,0,0,0,0,0},
{1,4,0,0,1,0,0,0,0,0},
{1,0,3,3,1,0,1,1,1,0},
{1,0,3,0,1,0,1,2,1,0},
{1,1,1,0,1,1,1,2,1,0},
{0,1,1,0,0,0,0,2,1,0},
{0,1,0,0,0,1,0,0,1,0},
{0,1,0,0,0,1,1,1,1,0},
{0,1,1,1,1,1,0,0,0,0},
{0,0,0,0,0,0,0,0,0,0}}
, 3, 2},
//map[3]
{
{{0,1,1,1,1,1,1,1,0,0},
{0,1,0,0,0,0,0,1,1,1},
{1,1,3,1,1,1,0,0,0,1},
{1,0,4,0,3,0,0,3,0,1},
{1,0,2,2,1,0,3,0,1,1},
{1,1,2,2,1,0,0,0,1,0},
{0,1,1,1,1,1,1,1,1,0},
{0,0,0,0,0,0,0,0,0,0},
{0,0,0,0,0,0,0,0,0,0},
{0,0,0,0,0,0,0,0,0,0}}
, 4, 3},
//map[4]
{
{{0,1,1,1,1,0,0,0,0,0},
{1,1,0,0,1,0,0,0,0,0},
{1,4,3,0,1,0,0,0,0,0},
{1,1,3,0,1,1,0,0,0,0},
{1,1,0,3,0,1,0,0,0,0},
{1,2,3,0,0,1,0,0,0,0},
{1,2,2,5,2,1,0,0,0,0},
{1,1,1,1,1,1,0,0,0,0},
{0,0,0,0,0,0,0,0,0,0},
{0,0,0,0,0,0,0,0,0,0}}
, 5, 4},
//map[5]
{
{{0,1,1,1,1,1,0,0,0,0},
{0,1,4,0,1,1,1,0,0,0},
{0,1,0,3,0,0,1,0,0,0},
{1,1,1,0,1,0,1,1,0,0},
{1,2,1,0,1,0,0,1,0,0},
{1,2,3,0,0,1,0,1,0,0},
{1,2,0,0,0,3,0,1,0,0},
{1,1,1,1,1,1,1,1,0,0},
{0,0,0,0,0,0,0,0,0,0},
{0,0,0,0,0,0,0,0,0,0}}
, 3, 5},
//map[6]
{
{{0,0,0,1,1,1,1,1,1,1},
{0,0,1,1,0,0,1,0,4,1},
{0,0,1,0,0,0,1,0,0,1},
{0,0,1,3,0,3,0,3,0,1},
{0,0,1,0,3,1,1,0,0,1},
{1,1,1,0,3,0,1,0,1,1},
{1,2,2,2,2,2,0,0,1,0},
{1,1,1,1,1,1,1,1,1,0},
{0,0,0,0,0,0,0,0,0,0},
{0,0,0,0,0,0,0,0,0,0}}
, 5, 6},
//map[7]
{
{{0,0,0,1,1,1,1,1,1,0},
{0,1,1,1,0,0,0,0,1,0},
{1,1,2,0,3,1,1,0,1,1},
{1,2,2,3,0,3,0,0,4,1},
{1,2,2,0,3,0,3,0,1,1},
{1,1,1,1,1,1,0,0,1,0},
{0,0,0,0,0,1,1,1,1,0},
{0,0,0,0,0,0,0,0,0,0},
{0,0,0,0,0,0,0,0,0,0},
{0,0,0,0,0,0,0,0,0,0}}
, 5, 7},
//map[8]
{
{{0,0,1,1,1,1,0,0,0,0},
{0,0,1,2,2,1,0,0,0,0},
{0,1,1,0,2,1,1,0,0,0},
{0,1,0,0,3,2,1,0,0,0},
{1,1,0,3,0,0,1,1,0,0},
{1,0,0,1,3,3,0,1,0,0},
{1,0,0,4,0,0,0,1,0,0},
{1,1,1,1,1,1,1,1,0,0},
{0,0,0,0,0,0,0,0,0,0},
{0,0,0,0,0,0,0,0,0,0}}
, 4, 8},
//map[9]
{
{{0,0,1,1,1,1,0,0,0,0},
{1,1,1,0,0,1,1,0,0,0},
{1,0,2,2,0,0,1,1,1,0},
{1,0,5,2,5,0,0,0,1,0},
{1,4,3,3,2,3,3,0,1,0},
{1,0,0,1,1,0,0,0,1,0},
{1,1,1,1,1,1,1,1,1,0},
{0,0,0,0,0,0,0,0,0,0},
{0,0,0,0,0,0,0,0,0,0},
{0,0,0,0,0,0,0,0,0,0}}
, 6, 9}
};


int main(void)
{
	int i;
	FILE *fp;

	fp = fopen("map.txt", "w");
	if(fp == NULL)
	{
		printf("文件创建失败!\n");
		getchar();
		return -1;
	}
	else
	{
		for(i = 0; i < 9; i++)
		{
			fwrite(&map[i], sizeof(MAP), 1, fp);
		}
		printf("map.txt文件已被创建.\n");
		fclose(fp);
		getchar();
		return 0;
	}
}