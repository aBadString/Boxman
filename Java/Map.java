import java.io.*;
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
    //控制
    JButton wBtn, aBtn, sBtn, dBtn, restart;
    Button jum;
    //关卡数
    JLabel passnum;
    JTextArea jumpass;
    //计时器
    Thread t;
    timeThread time;
    
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
        restart = new JButton("重来");
        jum = new Button("跳关");
        time = new timeThread();
        passnum = new JLabel();
        jumpass = new JTextArea();
        t = new Thread(time);
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
        //无法实现键盘监是因为没有获取屏幕焦点，将需要监听的控件获取屏幕焦点即可
        playGameFrame.setFocusable(true);
        wBtn.setBounds(370, 185, 45, 45);
        aBtn.setBounds(325, 230, 45, 45);
        sBtn.setBounds(370, 230, 45, 45);
        dBtn.setBounds(415, 230, 45, 45);
        restart.setBounds(340, 50, 80, 40);
        jum.setBounds(410, 120, 40, 20);
        wBtn.setFocusPainted(false);
        aBtn.setFocusPainted(false);
        sBtn.setFocusPainted(false);
        dBtn.setFocusPainted(false);
        time.setBounds(340, 20, 80, 20);
        passnum.setBounds(340, 0, 80, 20);
        passnum.setText("第" + String.valueOf(pass) + "关");
        jumpass.setBounds(340, 120, 60, 20);
        playGameFrame.add(wBtn);
        playGameFrame.add(aBtn);
        playGameFrame.add(sBtn);
        playGameFrame.add(dBtn);
        playGameFrame.add(restart);
        playGameFrame.add(jum);
        playGameFrame.add(time);
        playGameFrame.add(passnum);
        playGameFrame.add(jumpass);
        for(int i=0; i<M; i++)
        {
            for(int j=0; j<N; j++)
            {                
                platJLable[i][j].setBounds(32*j, 32*i, 32, 32);
                playGameFrame.add(platJLable[i][j]);             
            }
        }
        time.setFlag(true);
        time.setTime(300);
        t.start();  //启动计时线程
        playGameFrame.setVisible(true);
    }
    
    void AddListener()
    {
        playGameFrame.addKeyListener( 
        //匿名内部类 - 键盘适配器
        new KeyAdapter()
        {
            // 键入某个键时调用此方法
            public void keyPressed(KeyEvent e) 
            {
                int keyCode = e.getKeyCode();
                if(keyCode == KeyEvent.VK_UP)
                {
                    ChangeDataIcon('w');
                }
                else if(keyCode == KeyEvent.VK_DOWN)
                {
                    ChangeDataIcon('s');
                }
                else if(keyCode == KeyEvent.VK_LEFT)
                {
                    ChangeDataIcon('a');
                }
                else if(keyCode == KeyEvent.VK_RIGHT)
                {
                    ChangeDataIcon('d');
                }
                else
                {
                    ChangeDataIcon(e.getKeyChar());
                }
                end();
            }
        });
        
        jumpass.addKeyListener(
        //匿名内部类 - 键盘适配器
        new KeyAdapter()
        {
            // 键入某个键时调用此方法
            public void keyTyped(KeyEvent e) 
            {
                // 如果键入的字符是0~9，或者按键是Del键或Backspace键，则
                // 直接返回读入的键盘字符，否则，设置键入的字符为键位未知（0）
                if (((e.getKeyChar() <= 0x39) && (e.getKeyChar() >= 0x30))|| (e.getKeyChar() == 127) || (e.getKeyChar() == 8)) 
                {
                    e.setKeyChar(e.getKeyChar());
                } 
                else 
                {
                    e.setKeyChar((char) 0);
                }
            }
        });
        
        MapActionListener mal = new MapActionListener();
        wBtn.addActionListener(mal);
        aBtn.addActionListener(mal);
        sBtn.addActionListener(mal);
        dBtn.addActionListener(mal); 
        restart.addActionListener(mal);
        jum.addActionListener(mal);
        
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
            else if (ob == restart)
            {
                iniMap();
                DrawMap();
                time.setTime(300);
                time.setFlag(true);
            }
            else if (ob == jum)
            {
                int p = Integer.parseInt(jumpass.getText());
                if(p<=9 && p>=1)
                {
                    setPass(p);
                    ReadMap(); 
                    DrawMap();
                    passnum.setText("第" + String.valueOf(pass) + "关");
                    time.setTime(300);
                    time.setFlag(true);
                }
                jumpass.setText("");
            }
            playGameFrame.requestFocus();//重新获得焦点
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
            // 解析数据到各个变量中
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
                time.setFlag(false);
                JOptionPane.showMessageDialog(playGameFrame, "第"+pass+"关通过！");  
                setPass(getPass()+1);
                ReadMap(); 
                DrawMap();
                passnum.setText("第" + String.valueOf(pass) + "关");
                time.setTime(300);
                time.setFlag(true);
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
    
    
    //计时线程
    class timeThread extends JLabel implements Runnable
    {
        int t;
        boolean flag;
        void setTime(int i)
        {
            t = i;
        }
        void setFlag(boolean i)
        {
            flag = i;
        }
        public void run()
        {
            try
            {
                this.setText("时间：" + String.valueOf(t) + "s");
                for(; ; t--)
                {
                    if(flag)
                    {
                        this.setText("时间：" + String.valueOf(t) + "s");
                    }
                    if(t == 0)
                    {
                        flag = false;
                    }
                    Thread.sleep(1000);
                }
            }
            catch (Exception ee)
            {
                
            }
        }
    }
}

