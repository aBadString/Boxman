/** 登录窗体 */
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Login
{
    //窗体
    JFrame loginFrame;
    //文本对象
    JLabel nameLabel, passwordLabel, imageLabel;
    //输入框
    JTextField nameInput, infoJTextField;
    JPasswordField password;
    //按钮 忘记密码 - 注册账号 - 登录
    myButton loginBtn2, loginBtn1, loginBtn;
    //复选框 自动登录 - 记住密码
    JCheckBox choose2, choose1;
    //哈希表
    LinkedHashMap<String,String> name_password;
    
    public Login()
    {
    /* （1）创建各个容器的实例对象 */
        loginFrame = new JFrame("用户登录界面");
        //创建“用户名”文本对象
        nameLabel = new JLabel("用户名：");
        //创建“用户名”输入框对象
        nameInput = new JTextField();
        //创建“注册账号”按钮
        loginBtn1 = new myButton("register");
        //创建“密码”文本对象
        passwordLabel = new JLabel("    密码：");
        //创建“密码”输入框对象
        password = new JPasswordField();
        //创建“忘记密码”按钮
        loginBtn2 = new myButton("forget");
        //创建“自动登录”“记住密码”复选框对象
        choose2 = new JCheckBox("自动登录");
        choose1 = new JCheckBox("记住密码");
        //创建“登录”按钮
        loginBtn = new myButton("login");
        //创建“图片”文本对象
        imageLabel = new JLabel(new ImageIcon("imgUI/login.jpeg"));
        //创建“用户名密码”哈希表
        name_password = new LinkedHashMap<String,String>();
        //创建“信息提示”文本框对象
        infoJTextField = new JTextField ();
        
        //从数据库中读取账号密码数据，存入哈希表中
        set_name_password();
        showUI();
        AddListener();
    }
    
    public void showUI()
    {
    /* （2）创建一个顶级容器组件，设置窗体的属性 */
        //设置窗口尺寸大小
        loginFrame.setSize(480,520);
        //设置窗口背景颜色
        // loginFrame.setBackground(new Color(0x000000));
        //JFrame当中会默认使用流式布局管理器(FlowLayout)将整个窗体进行覆盖操作，也就是说设置的颜色确实是存在的，只是被布局管理器给覆盖住了，所以无法看见。		
        //在窗体当中添加一个面板操作,并进行对象的上转型操作。使得窗体面板容器占满整个窗体容器，然后直接对窗体面板当中的背景颜色进行设置就行
        Container pane = loginFrame.getContentPane();
		pane.setBackground(new Color(0x03A7BF));
		//设置窗口居中
        loginFrame.setLocationRelativeTo(null);
        //设置关闭窗口时结束程序运行
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置不可调节窗口大小
        loginFrame.setResizable(false);
        //为窗口设置流局界面
        loginFrame.setLayout(new FlowLayout());
        
    /* （3）创建各个组件并将组件添加到顶级容器组件中 */
        //添加图片到窗口中
        loginFrame.add(imageLabel);
        //把“用户名”文本对象添加到窗口中       
        loginFrame.add(nameLabel);
        //为“用户名”输入框对象设置输入框尺寸，并添加到窗口中
        nameInput.setPreferredSize(new Dimension(280, 30));
        loginFrame.add(nameInput);
        //添加“注册账号”按钮
        loginBtn1.setPreferredSize(new Dimension(100,30));
        //去掉聚焦线 去掉边框
		loginBtn1.setFocusPainted(false);
		loginBtn1.setBorder(null);
        //设置图片
        loginBtn1.setIcon(new ImageIcon("imgUI/"+loginBtn1.getname()+".png"));
        loginFrame.add(loginBtn1);
        //把“密码”文本对象添加到窗口中
        loginFrame.add(passwordLabel);	
        //为“密码”输入框对象设置输入框尺寸，并添加到窗口中     
        password.setPreferredSize(new Dimension(280,30));
        loginFrame.add(password);
        //添加“忘记密码”按钮
        loginBtn2.setPreferredSize(new Dimension(100,30));
        //去掉聚焦线 去掉边框
		loginBtn2.setFocusPainted(false);
		loginBtn2.setBorder(null);
        //设置图片 
        loginBtn2.setIcon(new ImageIcon("imgUI/"+loginBtn2.getname()+".png"));
        loginFrame.add(loginBtn2);
        //把“自动登录”“记住密码”复选框对象添加到窗口中            
        choose1.setFocusPainted(false);
        choose1.setBackground(pane.getBackground());//背景色与pane相同
        choose2.setFocusPainted(false);
        choose2.setBackground(pane.getBackground());//背景色与pane相同
        loginFrame.add(choose1);
        loginFrame.add(choose2);
        //为“登陆”按钮对象设置尺寸，并添加到窗口中       
        loginBtn.setPreferredSize(new Dimension(300,30)); 
		//去掉聚焦线 去掉边框
		loginBtn.setFocusPainted(false);
		loginBtn.setBorder(null);
        //设置图片
        loginBtn.setIcon(new ImageIcon("imgUI/"+loginBtn.getname()+".png"));
        loginFrame.add(loginBtn);
        //把“信息提示”文本框对象设置尺寸，并添加到窗口中
        infoJTextField.setPreferredSize(new Dimension (400, 30));
        infoJTextField.setBorder(null);//去边框
        infoJTextField.setBackground(pane.getBackground());//背景色与pane相同
        infoJTextField.setEditable(false);//只读
        loginFrame.add(infoJTextField);
        
    /* （4）设置窗体可见 */
		loginFrame.setVisible(true);
    }
    
    public void AddListener()
    {
        /* 监听按钮 */
        //事件监听器
        LoginActionListener lal = new LoginActionListener();
        loginBtn.addActionListener(lal);       
        loginBtn1.addActionListener(lal);
        loginBtn2.addActionListener(lal);
        choose2.addActionListener(lal);
        //鼠标事件监听器
        LoginMouseListener lml = new LoginMouseListener();
        loginBtn.addMouseListener(lml);
        loginBtn1.addMouseListener(lml);
        loginBtn2.addMouseListener(lml);
        //焦点监听器
        LoginFocusListener lfl = new LoginFocusListener();
        nameInput.addFocusListener(lfl);
        password.addFocusListener(lfl);
        //键盘适配器
        LoginKeyAdapter lka = new LoginKeyAdapter();
        nameInput.addKeyListener(lka);
        password.addKeyListener(lka);
    }
    
    /* 内部类监听器 */
    //事件监听器
    class LoginActionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            Object ob = e.getSource();
            //获取输入框的内容
            String name0 = nameInput.getText();
            //String password0 = password.getText(); //使用或覆盖了已过时的 API。
            String password0 = String.valueOf(password.getPassword());
            if(ob == loginBtn) //登录
            {               
                //识别输入的账号密码
                //取出哈希表中的所有键引用
                Set keys = name_password.keySet();
                String namec, passwordc;
                //设置flag
                boolean flag = true;
                for(Object key : keys)
                {
                    namec = (String)key;
                    if(name0.equals(namec))
                    {
                        passwordc = (String)name_password.get(key);
                        if(password0.equals(passwordc))
                        {
                            flag = false;
                            break;
                        }
                    }
                }
                if(flag)
                {
                    JOptionPane.showMessageDialog(loginFrame, "用户名或密码不正确");    
                }
                else 
                {
                    if(choose1.isSelected()) //登陆成功并且勾选了“记住密码”
                    {
                        System.out.println("记住密码");
                        try
                        {
                            char[] pa = password0.toCharArray();
                            //以只写的方式打开文件
                            RandomAccessFile raf = new RandomAccessFile("iniTXT/remember.password", "rw");
                            //获得文件长度，字节数
                            long fileLength = raf.length();
                            //将写文件指针移到文件尾。
                            raf.seek(fileLength);
                            //写入文件
                            for(int i=0; i<pa.length; i++)
                            {
                                raf.writeChar(pa[i]+3); //凯撒密码加密
                            }
                            raf.writeChar('@');
                            raf.writeChars(name0);
                            raf.writeChar('\r');
                            raf.writeChar('\n');
                            raf.close();
                        }
                        catch(Exception ee)
                        {
                            System.out.println("文件不存在");
                        }
                    }
                    
                    JOptionPane.showMessageDialog(loginFrame, "登录成功");
                    new Map();
                    loginFrame.dispose(); //销毁窗口
                }
                //清空文本框
                nameInput.setText("");
                password.setText("");    
            }
            else if(ob == loginBtn1) //注册账号
            {
                int ln = name0.length();
                int lp = password0.length();
                boolean flag = false;
                //判断用户名密码长度
                if(ln<1 || ln>10 || lp<6 || lp>24)
                {
                    JOptionPane.showMessageDialog(loginFrame, "用户名或密码长度不符合要求！");
                }
                else
                {
                    boolean flag2 = true;
                    //取出哈希表中的所有键引用
                    Set keys = name_password.keySet();
                    String namec;
                    for(Object key : keys)
                    {
                        namec = (String)key;
                        if(name0.equals(namec)) //如果用户名重复
                        {
                            flag2 = false;
                            JOptionPane.showMessageDialog(loginFrame, "该用户已存在！");
                        }
                    }
                    if(flag2)
                    {
                        flag = true;
                    }                        
                }
                if(flag)
                {
                    //将账号与密码放入哈希表中
                    name_password.put(name0, password0);
                    JOptionPane.showMessageDialog(loginFrame, "注册成功");                   
                    try
                    {
                        //以只写的方式打开文件
                        RandomAccessFile raf = new RandomAccessFile("iniTXT/the.password", "rw");
                        //获得文件长度，字节数
                        long fileLength = raf.length();
                        //将写文件指针移到文件尾。
                        raf.seek(fileLength);
                        raf.writeChars(password0);
                        raf.writeChar('@');
                        raf.writeChars(name0);
                        raf.writeChar('\r');
                        raf.writeChar('\n'); 
                        raf.close();
                    }
                    catch(Exception ee)
                    {
                    }
                    new Register(name0); //调用个人信息调查窗口
                    //System.out.println(name0);
                    //System.out.println(password0);
                }
                //清空文本框
                nameInput.setText("");
                password.setText("");
            }
            else if(ob == loginBtn2)
            {
                System.out.println("忘记密码");
            }
            else if(ob == choose2)  //自动登录
            {
                if(choose2.isSelected())
                {
                    System.out.println("自动登录");
                    try
                    {
                        //以只读的方式打开文件
                        RandomAccessFile raf = new RandomAccessFile("iniTXT/remember.password", "rw");
                        StringBuffer spass = new StringBuffer();	// 定义一个字符缓冲器
                        StringBuffer sname = new StringBuffer();	// 定义一个字符缓冲器
                        char a;
                        raf.seek(0);
                        for(a=0; (a=raf.readChar()) != '@'; )
                        {
                            a = (char)((int)a - 3); //凯撒密码解密
                            spass.append(a);
                        }
                        //System.out.println(spass);
                        for(a=0; (a=raf.readChar()) != '\r'; )
                        {
                            sname.append(a);
                        }
                        raf.close();
                        
                        nameInput.setText(sname.toString());
                        password.setText(spass.toString());
                    }
                    catch(Exception ee)
                    {
                        System.out.println(ee);
                    }
                }
                else
                {
                    nameInput.setText("");
                    password.setText("");
                }
            }
        }
    } 
    //键盘适配器
    class LoginKeyAdapter extends KeyAdapter
    {
        // 键入某个键时调用此方法
        public void keyTyped(KeyEvent e) 
        {
            Object ob = e.getSource();
            if(ob == nameInput)
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
            else if(ob == password)
            {
                // 如果键入的字符是a~z,A~Z,0~9，或者按键是Del键或Backspace键，则
                // 直接返回读入的键盘字符，否则，设置键入的字符为键位未知（0）
                if(((e.getKeyChar() <= 0x7A) && (e.getKeyChar() >= 0x61)) || ((e.getKeyChar() <= 0x5A) && (e.getKeyChar() >= 0x41)) || ((e.getKeyChar() <= 0x39) && (e.getKeyChar() >= 0x30)) || (e.getKeyChar() == 127) || (e.getKeyChar() == 8))
                {
                    e.setKeyChar(e.getKeyChar());
                } 
                else 
                {
                    e.setKeyChar((char) 0);
                }
            }                
        }
    }
    //鼠标监听器
    class LoginMouseListener implements MouseListener
    {
        // 鼠标在按钮上按下
        public void mousePressed(MouseEvent e)
        {
            myButton je = (myButton)e.getSource();
            je.setIcon(new ImageIcon("imgUI/"+je.getname()+"_mousePressed.png"));
        }
        // 鼠标在按钮上释放
        public void mouseReleased(MouseEvent e)
        {
            myButton je = (myButton)e.getSource();
            je.setIcon(new ImageIcon("imgUI/"+je.getname()+"_mouseEntered.png"));
        }
        // 鼠标进入按钮上方
        public void mouseEntered(MouseEvent e)
        {
            myButton je = (myButton)e.getSource();
            je.setIcon(new ImageIcon("imgUI/"+je.getname()+"_mouseEntered.png"));
        }
        // 鼠标离开按钮上方
        public void mouseExited(MouseEvent e)
        {
            myButton je = (myButton)e.getSource();
            je.setIcon(new ImageIcon("imgUI/"+je.getname()+".png"));
        }
        // 单击鼠标（按下并释放）
        public void mouseClicked(MouseEvent e)
        {
            
        }
    }
    //焦点监听器
    class LoginFocusListener implements FocusListener
    {
        //获得焦点
        public void focusGained(FocusEvent e)
        {
            Object ob = e.getSource();
            
            infoJTextField.setForeground(Color.green);//文本颜色
            infoJTextField.setHorizontalAlignment(JTextField.CENTER);//文本居中
            
            if(ob == nameInput)
            {
                infoJTextField.setText("用户名只能由1~10位数字组成。");
            }
            else if(ob == password)
            {
                infoJTextField.setText("密码由6~24位数字或大小写字母组成。");
            }
        }
        //失去焦点
        public void focusLost(FocusEvent e)
        {
            
        }
    }
    //从数据库中读取账号密码数据，存入哈希表中
    void set_name_password()
    {
        try
        {
            //以只读的方式打开文件
            RandomAccessFile raf = new RandomAccessFile("iniTXT/the.password", "rw");
            StringBuffer spass = new StringBuffer();	// 定义一个字符缓冲器
            StringBuffer sname = new StringBuffer();	// 定义一个字符缓冲器
            char a = '0';
            raf.seek(0);
            //获得文件长度，字节数
            long fileLength = raf.length();
            if(fileLength == 0)
            {
                return;
            }
            for(;raf.getFilePointer() != fileLength;)
            {
                for(a=0; (a=raf.readChar()) != '@'; )
                {
                    spass.append(a);
                }
                for(a=0; (a=raf.readChar()) != '\r'; )
                {
                    sname.append(a);
                }
                name_password.put(sname.toString(), spass.toString());
            }
            raf.close();
        }
        catch(Exception ee)
        {
        }
    }    
}

//具有名字信息的按钮
class myButton extends JButton
{
	private String name;
    myButton(String s)
    {
        super(s);
        setname(s);
    }
    void setname(String s)
    {
        name = s;
    }
    String getname()
    {
        return name;
    }
}