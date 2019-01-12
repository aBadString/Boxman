/**
  * 内部类监听器
  */


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ButtonTest
{
   public static void main(String[] args)
   {         
        JFrame frame = new ButtonFrame();
        frame.setTitle("ButtonTest");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
   }       
}
class ButtonFrame extends JFrame
{
   private JPanel buttonPanel;
   private static final int DEFAULT_WIDTH = 400;
   private static final int DEFAULT_HEIGHT = 300;
   public ButtonFrame()
   {      
      setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
      // 创建按钮对象
      JButton yellowButton = new JButton("Yellow");
      JButton blueButton   = new JButton("Blue");
      JButton redButton    = new JButton("Red");
      buttonPanel = new JPanel();
      // 添加按钮到画板
      buttonPanel.add(yellowButton); 
      buttonPanel.add(blueButton);
      buttonPanel.add(redButton);    
      add(buttonPanel);
      // 创建按钮监听器
      ColorListener yellowListener = new ColorListener(Color.YELLOW);
      ColorListener blueListener   = new ColorListener(Color.BLUE);
      ColorListener redListener    = new ColorListener(Color.RED);
      // 为每个按钮设置监听器
      yellowButton.addActionListener(yellowListener);
      blueButton.addActionListener(blueListener);
      redButton.addActionListener(redListener);
   }
   //内部类监听器
   private class ColorListener implements ActionListener
   {
      private Color backgroundColor;
      public ColorListener(Color c) {
         backgroundColor = c;
      }
      public void actionPerformed(ActionEvent event) {
         buttonPanel.setBackground(backgroundColor);//内部类可以访问外部类的变量
      }
   }
}