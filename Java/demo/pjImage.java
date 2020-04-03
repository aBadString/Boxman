import java.awt.*;
import javax.swing.*;

public class pjImage
{
    JFrame jf; 
    JPanel p;
    public pjImage() 
    {
        jf = new JFrame();
        p = new JPanel() 
        {
            public void paintComponent(Graphics g) 
            {
                super.paintComponent(g);
                ImageIcon ii = new ImageIcon("../imgUI/bg.jpeg");
                g.drawImage(ii.getImage(), 0, 0, getWidth(), getHeight(), ii.getImageObserver());
            }
        };
        
        
        p.add(new Button("按钮"));//如果覆盖的是pain()方法，按钮会被挡住
        jf.add(p);
        jf.setSize(300, 300);
        jf.setVisible(true);
    }

    public static void main(String[] args) 
    {
        new pjImage();
    }
}