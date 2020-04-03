using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace BoxMan
{
    class Map
    {
        public const int M = 10;
        public const int N = 10;
        int num { get; set; }           //每一关的箱子数
        public int pass { get; set; }   //关卡数
        public int[,] plat;             //地图
        int[,] plat_ini;                //初始地图

        public Map()
        {
            pass = 1;
            num = 0;
            plat = new int[M,N];
            plat_ini = new int[M,N];

            //ReadMap();
            //PrintMap();
        }

        //读取并解析地图
        public void ReadMap()
        {
            try
            {
                //把文件读入sb中
                StreamReader sr = new StreamReader("../../maps/" + pass + ".map");
                StringBuilder sb = new StringBuilder();   //定义一个字符缓冲器
                int a;
                for (a = 0; (a = sr.Read()) != -1;)
                {
                    sb.Append((char)a);
                }
                sr.Close();
                //解析数据到各个变量中
                String ss = sb.ToString();
                char[] mapdata = ss.ToCharArray();
                int k = 0;
                num = 0;
                for (int i = 0; i < M; i++)
                {
                    for (int j = 0; j < N; k++)
                    {
                        if ('0' <= mapdata[k] && mapdata[k] <= '9')
                        {
                            plat_ini[i,j] = plat[i,j] = (int)mapdata[k] - 48;
                            if (plat[i,j] == 3 || plat[i,j] == 5)
                            {
                                num++;
                            }
                            j++;
                        }
                    }
                }
            }
            catch (Exception e)
            {
                Console.WriteLine(e.Message);
            }
        }
        //重新覆盖地图
        public void IniMap()
        {
            for (int i = 0; i < M; i++)
            {
                for (int j = 0; j < N; j++)
                {
                    plat[i,j] = plat_ini[i,j];
                }
            }
        }
        //获取输入，改变地图
        public void ChangeDataIcon(char ch)
        {
            int i, j, x, y, a, b;
            i = j = x = y = a = b = 0;

            for (i = 0; i < M; i++)
            {
                for (j = 0; j < N; j++)
                {
                    if (plat[i,j] == 4 || plat[i,j] == 6) //寻找玩家的位置坐标
                    {
                        x = i; y = j;
                    }
                }
            }

            switch (ch)
            {
                case 'w'://上
                case 'W'://上
                    a = -1;
                    b = 0;
                    break;
                case 's'://下
                case 'S'://下
                    a = 1;
                    b = 0;
                    break;
                case 'a'://左
                case 'A'://左
                    a = 0;
                    b = -1;
                    break;
                case 'd'://右
                case 'D'://右
                    a = 0;
                    b = 1;
                    break;
                default:
                    a = 0;
                    b = 0;
                    break;
            }

            if ((a != 0) || (b != 0))
            {
                //小人行走
                if (plat[x + a,y + b] == 0 || plat[x + a,y + b] == 2)
                {
                    plat[x + a,y + b] += 4;
                    plat[x,y] -= 4;
                }
                //小人推箱子
                if (plat[x + a,y + b] == 3 || plat[x + a,y + b] == 5)
                {
                    if (plat[x + 2 * a,y + 2 * b] == 0 || plat[x + 2 * a,y + 2 * b] == 2)
                    {
                        plat[x + 2 * a,y + 2 * b] += 3;
                        plat[x + a,y + b] += 1;
                        plat[x,y] -= 4;
                    }
                }
            }
        }
        //判断游戏结束
        public bool IsPast()
        {
            int i, j, n;

            n = 0;
            for (i = 0; i < M; i++)
            {
                for (j = 0; j < N; j++)
                {
                    if (plat[i,j] == 5)    //找到了一个在目标位置的箱子
                    {
                        n++;
                    }
                }
            }
            if (n == num)       //判断所有目标位置的在箱子数量是否等于通关条件
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        //打印地图
        void PrintMap()
        {
            for (int i = 0; i < M; i++)
            {
                for (int j = 0; j < N; j++)
                {
                    Console.Write((char)(plat[i,j] + 48));
                }
                Console.WriteLine();
            }
        }

    }
}
