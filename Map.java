import java.io.*;
import java.util.Scanner;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Map
{
    final int M;
    final int N;
    int num;		//每一关的箱子数
    int[][] plat;	//地图
    int[][] plat_ini;	//初始地图
	int pass;		//关卡数
    //窗体
    JFrame playGameFrame;
    //JLabel对象
    JLabel[][] platJLable;
    //跳关
    JButton wBtn, aBtn, sBtn, dBtn;
    
    
    // 构造方法
    public Map()
    {
        M = N = 10;
        plat = new int[M][N];
        plat_ini = new int[M][N];
        playGameFrame = new JFrame("推箱子");
        wBtn = new JButton("W");
        aBtn = new JButton("A");
        sBtn = new JButton("S");
        dBtn = new JButton("D");
        platJLable = new JLabel[M][N];  
        for(int i=0; i<M; i++)
        {
            for(int j=0; j<N; j++)
            {                
                platJLable[i][j] = new JLabel();         
            }
        }
    
        setPass(1);
        ReadMap();
        DrawMap();
        showUI();
        
        AddListener();
    }
    
     public void showUI()
     {
        playGameFrame.setSize(480, 320);
        playGameFrame.setBackground(new Color(0x38a7b2));
        //为窗口设置流局界面
        playGameFrame.setLayout(null);
		//设置窗口居中
        playGameFrame.setLocationRelativeTo(null);
        //设置关闭窗口时结束程序运行
        playGameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置不可调节窗口大小
        playGameFrame.setResizable(false);
        
        wBtn.setBounds(370, 185, 45, 45);
        aBtn.setBounds(325, 230, 45, 45);
        sBtn.setBounds(370, 230, 45, 45);
        dBtn.setBounds(415, 230, 45, 45);
        wBtn.setFocusPainted(false);
        aBtn.setFocusPainted(false);
        sBtn.setFocusPainted(false);
        dBtn.setFocusPainted(false);
        playGameFrame.add(wBtn);
        playGameFrame.add(aBtn);
        playGameFrame.add(sBtn);
        playGameFrame.add(dBtn);
        for(int i=0; i<M; i++)
        {
            for(int j=0; j<N; j++)
            {                
                platJLable[i][j].setBounds(32*j, 32*i, 32, 32);
                playGameFrame.add(platJLable[i][j]);             
            }
        }
        
        playGameFrame.setVisible(true);
    }
    
    void AddListener()
    {
        playGameFrame.addKeyListener( 
        //匿名内部类 - 键盘适配器
        new KeyAdapter()
        {
            // 键入某个键时调用此方法
            public void keyTyped(KeyEvent e) 
            {
                System.out.println("ee");
                ChangeDataIcon(e.getKeyChar());
                end();
            }
        });
        
        MapActionListener mal = new MapActionListener();
        wBtn.addActionListener(mal);
        aBtn.addActionListener(mal);
        sBtn.addActionListener(mal);
        dBtn.addActionListener(mal);
        
    }
    //事件监听器
    class MapActionListener implements ActionListener
    {
        // 窗体的ActionEvent事件处理方法
        public void actionPerformed(ActionEvent e) 
        {
            Object ob = e.getSource(); // 获取事件对象
            if (ob == playGameFrame)
            { 
                System.out.println("playGameFrame");
            }
            else if (ob == wBtn)
            {
                ChangeDataIcon('w');
            }
            else if (ob == aBtn)
            {
                ChangeDataIcon('a');
            } 
            else if (ob == sBtn)
            {
                ChangeDataIcon('s');
            } 
            else if (ob == dBtn)
            {
                ChangeDataIcon('d');
            } 
            end();
        }
    }
        
    public void setPass(int p)
    {
        pass = p;
    }
    public int getPass()
    {
        return pass;
    }
    // 读取并解析地图
    public void ReadMap()
    {
        try
        {
            // 把文件读入sb中
            FileInputStream fis = new FileInputStream("./maps/"+pass+".map");
            StringBuffer sb = new StringBuffer();	// 定义一个字符缓冲器
            int a;
            for(a=0; (a=fis.read()) != -1; )
            {
                sb.append((char)a);
            }
            fis.close();
            
            String ss = sb.toString();
            char[] mapdata = ss.toCharArray();
            int k=0;
            num = 0;            
            for(int i=0; i<M; i++)
            {
                for(int j=0; j<N; k++)
                {
                    if('0' <= mapdata[k] && mapdata[k] <= '9')
                    {
                        plat_ini[i][j] = plat[i][j] = (int)mapdata[k] - 48;
                        if(plat[i][j] == 3 || plat[i][j] == 5)
                        {
                            num++;
                        }
                        j++;
                    }
                }                
            }            
        }
        catch(Exception e)
        {
        }           
    }
    // 重新覆盖地图
    void iniMap()
    {
        for(int i=0; i<M; i++)
        {
            for(int j=0; j<N; j++)
            {
                plat[i][j] = plat_ini[i][j];
            }
        }
    }
    // 获取输入，改变地图
    void ChangeDataIcon(char ch)
    {
        int i, j, x, y, a, b;
        i = j = x = y = a = b = 0;
        
        for (i = 0; i < M; i++)
        {
            for (j = 0; j < N; j++)
            {
                if (plat[i][j] == 4 || plat[i][j] == 6)	//寻找玩家的位置坐标
                {
                    x = i;	y = j;
                }
            }
        }
        
        switch (ch)		//算法解释详见“需求分析及设计文档.rtf”
        {
        case 'w'://上
        case 'W'://上
        case 38://上
            a = -1;
            b = 0;
            break;
        case 's'://下
        case 'S'://下
        case 40://下
            a = 1;
            b = 0;
            break;
        case 'a'://左
        case 'A'://左
        case 37://左
            a = 0;
            b = -1;
            break;
        case 'd'://右
        case 'D'://右
        case 39://右
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
            if (plat[x + a][y + b] == 0 || plat[x + a][y + b] == 2)
            {
                plat[x + a][y + b] += 4;
                platJLable[x + a][y + b].setIcon(new ImageIcon("imgUI/"+plat[x + a][y + b]+".gif"));              
                plat[x][y] -= 4; 
                platJLable[x][y].setIcon(new ImageIcon("imgUI/"+plat[x][y]+".gif"));    
            }
            //小人推箱子
            if (plat[x + a][y + b] == 3 || plat[x + a][y + b] == 5)
            {
                if (plat[x + 2 * a][y + 2 * b] == 0 || plat[x + 2 * a][y + 2 * b] == 2)
                {
                    plat[x + 2 * a][y + 2 * b] += 3;
                    platJLable[x + 2 * a][y + 2 * b].setIcon(new ImageIcon("imgUI/"+plat[x + 2 * a][y + 2 * b]+".gif"));
                    plat[x + a][y + b] += 1;
                    platJLable[x + a][y + b].setIcon(new ImageIcon("imgUI/"+plat[x + a][y + b]+".gif"));
                    plat[x][y] -= 4;
                    platJLable[x][y].setIcon(new ImageIcon("imgUI/"+plat[x][y]+".gif"));
                }
            }
        }	
    }
    //判断游戏结束
    boolean IsPast()
    {
        int i, j, n;

        n = 0;
        for (i = 0; i < M; i++)
        {
            for (j = 0; j < N; j++)
            {
                if (plat[i][j] == 5)	//找到了一个在目标位置的箱子
                {
                    n++;
                }
            }
        }
        if (n == num)		//判断所有目标位置的在箱子数量是否等于通关条件
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    void end()
    {
        if(IsPast())
        {
            if(getPass() == 9)
            {
                JOptionPane.showMessageDialog(playGameFrame, "恭喜你通过所有关卡！");
                playGameFrame.dispose(); //销毁窗口
                System.exit(0); // 退出系统
            }
            else
            {
                JOptionPane.showMessageDialog(playGameFrame, "第"+pass+"关通过！"); 
                setPass(getPass()+1);
                ReadMap(); 
                DrawMap();
            }
        }
    }
    
    // 绘制地图
    public void DrawMap()
    {
        for(int i=0; i<M; i++)
        {
            for(int j=0; j<N; j++)
            {
                platJLable[i][j].setIcon(new ImageIcon("imgUI/"+plat[i][j]+".gif"));
                System.out.print((char)(plat[i][j]+48));
            }
            System.out.println();
        }
    }
}
