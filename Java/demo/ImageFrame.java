import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class ImageFrame extends JFrame {
  class ImagePanel extends JPanel {
    Dimension d;
    Image image;
    public ImagePanel(Dimension d, Image image) {
      super();
      this.d = d;
      this.image = image;
    }
    @Override
    public void paintComponent(Graphics g) {
      super.paintComponent(g);
      g.drawImage(image, 0, 0, d.width, d.height, this);
      MainFrame.instance().repaint();
    }
  }
  Dimension frameSize = new Dimension(500, 300);
  ImageIcon imageIcon = new ImageIcon(this.getClass().getResource(
      "/images/bg.jpg"));
  public ImageFrame() {
    // 设置窗体属性
    setSize(frameSize);
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setIconImage(imageIcon.getImage());
    setUndecorated(true);
  }
  public void addImageByJLable() {
    setLayout(null);
    // 设置背景
    JLabel lbBg = new JLabel(imageIcon);
    lbBg.setBounds(0, 0, frameSize.width, frameSize.height);
    this.getContentPane().add(lbBg);
    addComponents();
    setVisible(true);
  }
  public void addImageByRepaint() {
    ImagePanel imagePanel = new ImagePanel(frameSize, imageIcon.getImage());
    setContentPane(imagePanel);
    addComponents();
    setVisible(true);
  }
  private void addComponents() {
    JButton btn1 = new JButton("haha");
    btn1.setBounds(10, 20, 60, 30);
    this.getContentPane().add(btn1);
    JTextField jtf = new JTextField("22222222222");
    jtf.setBounds(200, 100, 80, 30);
    this.getContentPane().add(jtf);
  }
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    ImageFrame imageFrame = new ImageFrame();
    // imageFrame.addImageByJLable();
    imageFrame.addImageByRepaint();
  }
}