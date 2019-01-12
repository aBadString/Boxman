//package NotePadSong;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class NotePadSong
{
    Frame frame;
    int WIDTH, HEIGTH;	// 窗体尺寸
    threeImageButton new_button, top_button, setting_button, close_button;

    public static void main(String[] args)
	{   
        NotePadSong note_pad_song = new NotePadSong();
		note_pad_song.initUI();
	}
    
    NotePadSong()
    {
        WIDTH=400; HEIGTH=400;
		frame = new Frame("NotePadSong");
                
        new_button = new threeImageButton(
				".\\imgUI\\new_normal.png",
				".\\imgUI\\new_mouseEntered.png",
				".\\imgUI\\new_mousePressed.png"
				);
		top_button = new threeImageButton(
				".\\imgUI\\top_normal.png",
				".\\imgUI\\top_mouseEntered.png",
				".\\imgUI\\top_mousePressed.png"
				);
		setting_button = new threeImageButton(
				".\\imgUI\\setting_normal.png",
				".\\imgUI\\setting_mouseEntered.png",
				".\\imgUI\\setting_mousePressed.png"
				);
		close_button = new threeImageButton(
				".\\imgUI\\close_normal.png",
				".\\imgUI\\close_mouseEntered.png",
				".\\imgUI\\close_mousePressed.png"
				);
    }
    
    void initUI()
	{
        frame.setSize(WIDTH, HEIGTH);
        frame.setBackground(new Color(0xE7CFFF));
        frame.setLayout(null);  // 取消默认布局
        
        // 为按钮设置大小和位置
        new_button.setBounds(0*50, 30, 50, 50);
        top_button.setBounds(1*50, 30, 50, 50);
        setting_button.setBounds(2*50, 30, 50, 50);
        close_button.setBounds(WIDTH-50, 30, 50, 50);
        // 为按钮设置图片
        new_button.changeImage(1);
        top_button.changeImage(1);
        setting_button.changeImage(1);
        close_button.changeImage(1);
        // 为按钮注册监听器
        new_button.addMouseListener(new ButtonHandler());
        top_button.addMouseListener(new ButtonHandler());
        setting_button.addMouseListener(new ButtonHandler());
        close_button.addMouseListener(new ButtonHandler());
        // 把按钮加入窗体中
        frame.add(new_button);
        frame.add(top_button);
        frame.add(setting_button);
        frame.add(close_button);
        
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
    
/* ButtonHandler
 * 按钮事件侦听器
 */
class ButtonHandler implements MouseListener
{      
    // 鼠标在按钮上按下
	public void mousePressed(MouseEvent e)
	{
		((threeImageButton)e.getSource()).changeImage(3);
	}
	// 鼠标在按钮上释放
	public void mouseReleased(MouseEvent e)
	{
		((threeImageButton)e.getSource()).changeImage(2);
	}
	// 单击鼠标（按下并释放）
	public void mouseClicked(MouseEvent e)
	{
        
	}
	// 鼠标进入按钮上方
	public void mouseEntered(MouseEvent e)
	{
		((threeImageButton)e.getSource()).changeImage(2);
	}
	// 鼠标离开按钮上方
	public void mouseExited(MouseEvent e)
	{
		((threeImageButton)e.getSource()).changeImage(1);
	}
}

/* imageButton类
 * 自定义图片按钮
 */
class imageButton extends JButton
{
	private ImageIcon image;
    
    public void loadImage(String s)
    {
        image = new ImageIcon(s);
    }
	// 显示按钮的图片
	public void setImage(String s)
	{
		// 去掉背景点击效果
		this.setContentAreaFilled(false);  
		// 去掉聚焦线
		this.setFocusPainted(false);
		// 去掉边框
		this.setBorder(null); 
		// 设置显示的图片
        this.loadImage(s);
		this.setIcon(image);
	}
}

/* threeImageButton类
 * 三级触发的图片按钮
 */
class threeImageButton extends imageButton
{
    String icon1, icon2, icon3;
    
    threeImageButton(String s1, String s2, String s3)
    {
        icon1=s1; icon2=s2; icon3=s3;
    }
    // 改变按钮的图片
	public void changeImage(int a)
	{
		// 设置背景图片
		if(a == 2)
		{
			setImage(icon2);
		}
		else if(a == 3)
		{
			setImage(icon3);
		}
		else
		{
			setImage(icon1);
		}
	}
}