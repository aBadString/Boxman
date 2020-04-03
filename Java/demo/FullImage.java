import java.awt.*;
import javax.swing.*;

public class FullImage extends JFrame 
{
    public FullImage() 
    {
        JPanel p = new JPanel() 
        {
            public void paintComponent(Graphics g) 
            {
                super.paintComponent(g);
                ImageIcon ii = new ImageIcon("imgUI/bg.jpeg");
                g.drawImage(ii.getImage(), 0, 0, getWidth(), getHeight(), ii.getImageObserver());
            }
        };
        p.add(new JButton("按钮"));//如果覆盖的是pain()方法，按钮会被挡住
        add(p);
        setSize(300, 300);
        setVisible(true);
    }

    public static void main(String[] args) 
    {
        new FullImage();
    }
}