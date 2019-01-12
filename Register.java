import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;

public class Register
{
    User user;
    //窗体
    JFrame registerFrame;
    //输入框
    TextField name, age;
    //复选框
    Checkbox man, woman;
    JCheckBox like3, like2, like1;
    //单选框组
    CheckboxGroup sex;
    //选项框
    Choice nativeplace;
    //列表框
    List website;
    //按钮
    Button btn1, btn2, btn3;
    //标签
    JLabel l, l1, l2, l3, l4, l5, l6;
    //背景图片面板
    JPanel p;
    
    public Register(String id)
    {
        user = new User(id);
        registerFrame = new JFrame("用户注册界面");
        p = new JPanel() 
        {
            public void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                ImageIcon ii = new ImageIcon("imgUI/bg.jpg");
                g.drawImage(ii.getImage(), 0, 0, getWidth(), getHeight(), ii.getImageObserver());
            }
        };
        name = new TextField(10); // 姓名，宽度为10
        man = new Checkbox("男");
        woman = new Checkbox("女");
        sex = new CheckboxGroup(); // 性别单选钮组
        age = new TextField("20", 4); // 年龄，初始值为20，文本域宽度为4
        nativeplace = new Choice(); // 籍贯
        like1 = new JCheckBox("读书"); // 爱好
        like2 = new JCheckBox("上网");
        like3 = new JCheckBox("体育活动");
        website = new List(4); // 喜欢的编程语言。显示4行
        btn1 = new Button("确认"); // 确认、取消和退出按钮
        btn2 = new Button("取消");
        btn3 = new Button("退出");
        l = new JLabel("个人信息调查表");
        l1 = new JLabel("姓名");
        l2 = new JLabel("性别");
        l3 = new JLabel("年龄");
        l4 = new JLabel("籍贯");
        l5 = new JLabel("爱好");
        l6 = new JLabel("喜欢的编程语言");
        
        showUI();
        AddListener();
    }
    
    public void showUI()
    {
        registerFrame.setSize(400, 400); // 设置窗体的尺寸
		p.setLayout(null); // 取消面板的布局管理器 
		//设置窗口居中
        registerFrame.setLocationRelativeTo(null);
        //设置不可调节窗口大小
        registerFrame.setResizable(false);
		l.setBounds(150, 50, 100, 20);// 个人信息调查表
		l1.setBounds(50, 100, 40, 20);// 姓名标签
		name.setBounds(90, 100, 100, 20);// 姓名文本域
		l2.setBounds(230, 100, 40, 20);// 性别标签
		man.setCheckboxGroup(sex);// 制作单选按钮组
		woman.setCheckboxGroup(sex);
		sex.setSelectedCheckbox(man);
		man.setBounds(270, 100, 60, 20);// 男单选钮
		woman.setBounds(330, 100, 60, 20);// 女单选钮
		l3.setBounds(50, 150, 40, 20);// 年龄标签
		age.setBounds(90, 150, 50, 20);// 年龄文本域
		l4.setBounds(230, 150, 40, 20);// 籍贯标签
		nativeplace.add("北京");// 设置选项框内容
		nativeplace.add("上海");
		nativeplace.add("天津");
		nativeplace.add("重庆");
		nativeplace.add("广东");
		nativeplace.add("河南");
		nativeplace.setBounds(270, 150, 60, 20);// 籍贯选项框
		l5.setBounds(50, 200, 40, 20);// 爱好标签
		like1.setBounds(90, 200, 60, 20);// 读书
		like2.setBounds(150, 200, 60, 20);// 上网
		like3.setBounds(210, 200, 100, 20);// 体育活动
        like1.setOpaque(false);
        like2.setOpaque(false);
        like3.setOpaque(false);
		website.add("C"); // 喜欢的编程语言
		website.add("C++");
		website.add("Java");
		website.add("Python");
		website.add("Matlab");
		website.add("Assembly");
		website.add("PHP");
		l6.setBounds(50, 250, 100, 20);// 喜欢的编程语言标签
		website.setBounds(150, 250, 100, 60);// 喜欢的编程语言
		btn1.setBounds(110, 330, 50, 20);// 确认
       // btn1.setContentAreaFilled(false);  
		btn2.setBounds(180, 330, 50, 20);// 取消
       // btn2.setContentAreaFilled(false);  
		btn3.setBounds(250, 330, 50, 20);// 退出
       // btn3.setContentAreaFilled(false);  
		// 将各组件添加到窗体中
		p.add(l);
		p.add(l1);
		p.add(name);
		p.add(l2);
		p.add(man);
		p.add(woman);
		p.add(l3);
		p.add(age);
		p.add(l4);
		p.add(nativeplace);
		p.add(l5);
		p.add(like1);
		p.add(like2);
		p.add(like3);
		p.add(l6);
		p.add(website);
		p.add(btn1);
		p.add(btn2);
		p.add(btn3);
        registerFrame.add(p);
        
        registerFrame.setVisible(true);
    }
    
    public void AddListener()
    {
        /* 监听按钮 */
        //键盘适配器
        RegisterKeyAdapter rka = new RegisterKeyAdapter();
        age.addKeyListener(rka); // 为年龄文本域注册键盘事件侦听器
        //焦点监听器
        RegisterFocusAdapter rfa = new RegisterFocusAdapter();
        age.addFocusListener(rfa); // 为年龄文本域注册键盘事件侦听器
        //事件监听器
        RegisterActionListener ral = new RegisterActionListener();
        btn1.addActionListener(ral); // 为三个按钮注册事件侦听器
		btn2.addActionListener(ral);
		btn3.addActionListener(ral);
        //窗口适配器
        RegisterWindowAdapter rwa = new RegisterWindowAdapter();
        registerFrame.addWindowListener(rwa);
    }
    
    /* 内部类监听器 */
    //窗口适配器
    class RegisterWindowAdapter extends WindowAdapter
    {
        /* 处理关闭窗口事件的方法
        * windowClosing(e)
        */
        public void windowClosing(WindowEvent e)
        {
            registerFrame.dispose(); //销毁窗口
        }
    }
    //事件监听器
    class RegisterActionListener implements ActionListener
    {
        // 窗体的ActionEvent事件处理方法
        public void actionPerformed(ActionEvent e) 
        {
            Object ob = e.getSource(); // 获取事件对象
            if (ob == btn3)// 单击退出按钮 
            { 
                new Client();
                registerFrame.dispose(); //销毁窗口
                //System.exit(0); // 退出系统
            }
            else if (ob == btn1)// 单击确认按钮 
            {
                System.out.println("ID：" + user.id);
                user.name = name.getText();
                System.out.println("姓名：" + user.name);
                user.sex = sex.getSelectedCheckbox().getLabel();
                System.out.println("性别：" + user.sex);
                user.age = age.getText();
                System.out.println("年龄：" + user.age);
                user.place = nativeplace.getSelectedItem();
                System.out.println("籍贯：" + user.place);
                // 如果复选框被选中，则返回其标签，否则将字符串设置为空
                user.like[0] = like1.isSelected() ? like1.getText() + "  " : "";
                user.like[1] = like2.isSelected() ? like2.getText() + "  " : "";
                user.like[2] = like3.isSelected() ? like3.getText() + "  " : "";
                System.out.println("爱好：" + user.like[0] + user.like[1] + user.like[2]);
                user.lange = website.getSelectedItem();
                System.out.println("喜欢的编程语言：" + user.lange);
                new Client();
                registerFrame.dispose(); //销毁窗口
            } 
            else if (ob == btn2) // 单击取消按钮
            {
                name.setText(""); // 清空姓名文本域
                sex.setSelectedCheckbox(man); // 选中"男人"单选按钮
                age.setText("20"); // 设置年龄文本域为20
                like1.setSelected(false); // 取消爱好各复选框
                like2.setSelected(false);
                like3.setSelected(false);
                website.deselect(website.getSelectedIndex());// 取消所选喜欢的编程语言
            }
        }
    }
    
    /* 内部类事件适配器 */
    //键盘适配器
    class RegisterKeyAdapter extends KeyAdapter
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
    }
    //焦点适配器
    class RegisterFocusAdapter extends FocusAdapter
    {
        // 年龄文本域失去键盘焦点时调用此方法
        public void focusLost(FocusEvent e) 
        {
            // 将年龄字符串转换为整数
            int i = Integer.parseInt(age.getText());
            // 年龄无效，年龄文本域恢复默认值并重获键盘焦点
            if ((i == 0) || (i >= 200)) 
            {
                // 弹出一个错误提示对话框
                JOptionPane.showMessageDialog(registerFrame, "年龄有误，其值应该为1-199！", "错误提示",JOptionPane.ERROR_MESSAGE);
                age.setText("20"); // 恢复年龄默认值
                age.requestFocusInWindow(); // 年龄文本域重获焦点
            }
        }
    }    
    
    class Client
    {
        Client()
        {
            Socket mySocket;
            DataInputStream in = null;
            DataOutputStream out = null;
            try
            {
                mySocket = new Socket("localhost", 1762);
                in = new DataInputStream(mySocket.getInputStream());
                out = new DataOutputStream(mySocket.getOutputStream());
                out.writeUTF(user.id);
                Thread.sleep(100);
                out.writeUTF(user.name);
                Thread.sleep(100);
                out.writeUTF(user.age);
                Thread.sleep(100);
                out.writeUTF(user.sex);
                Thread.sleep(100);
                out.writeUTF(user.place);
                Thread.sleep(100);
                out.writeUTF(user.like[0]);
                Thread.sleep(100);
                out.writeUTF(user.like[1]);
                Thread.sleep(100);
                out.writeUTF(user.like[2]);
                Thread.sleep(100);
                out.writeUTF(user.lange);
                Thread.sleep(100);
                out.close();
                in.close();
                mySocket.close();
                System.out.println("...");
            }
            catch(Exception ee)
            {
                
            }
        }
    }
}

/* 封装用户属性 */
class User 
{
    String id;// 账号
    String name;// 姓名
    String sex;// 性别
    String age;// 年龄
    String place;// 籍贯
    String[] like;// 爱好
    String lange;// 喜欢的编程语言
    
    public String getId() 
    {
        return id;
    }
    User(String id)
    {
        this.id = id;
        name = new String("");
        sex = new String("男");
        age = "20";
        place = new String("北京");
        like = new String[3];
        like[0] = new String("");
        like[1] = new String("");
        like[2] = new String("");
        lange = new String("");
    }
}
