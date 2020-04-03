#include <windows.h>
#include <stdio.h>

int main(void)
{
	system("del boxman.exe");
	system("del create_map.exe");
	system("del map.txt");
	system("del boxman_icon.ico");
	system("del boxman_logo.bmp");
	system("del update.txt");
	system("del C:\\Users\\song\\Desktop\\BoxMan.lnk");
	//system("del D:\\BoxMan");

	printf("推箱子已卸载。感谢您的使用!\n还有一些文件请手动删除D盘根目录下的BoxMan文件夹。\n按下回车键完成卸载。");
	getchar();

	return 0;
}