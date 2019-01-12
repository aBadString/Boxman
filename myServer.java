import java.io.*;
import java.net.*;

public class myServer 
{
    public static void main(String[] args) 
    {
		myServer ms = new myServer();
		ms.setServer(1762);
	}
    
	/*
	 * 创建服务器，等待客户机的连接并进行通信
	 * 参数：port 端口号
	 */
	public void setServer(int port) 
    {
		try 
        {
			ServerSocket ss = new ServerSocket(port);   // 创建一个服务器对象
			System.out.println("服务器创建成功，端口号：" + ss.getLocalPort() + "等待连接中……");

            // 等待客户机连接
			Socket client = ss.accept();    // 侦听并接受到此套接字的连接。此方法在连接传入之前一直阻塞。
			System.out.println("连接成功，连接的客户机是：" + client.getPort());

            // 获得Socket对象的输入输出流
			DataOutputStream out = new DataOutputStream(client.getOutputStream());
			DataInputStream ins = new DataInputStream(client.getInputStream());

            User user = new User("");
            
            user.id = ins.readUTF(); 
            Thread.sleep(100);
            user.name = ins.readUTF();
            Thread.sleep(100);
            user.sex = ins.readUTF();
            Thread.sleep(100);
            user.age = ins.readUTF();
            Thread.sleep(100);
            user.place = ins.readUTF();
            Thread.sleep(100);
            user.like[0] = ins.readUTF();
            Thread.sleep(100);
            user.like[1] = ins.readUTF();
            Thread.sleep(100);
            user.like[2] = ins.readUTF();
            Thread.sleep(100);
            user.lange = ins.readUTF();
            Thread.sleep(100);
            
            
            System.out.println("ID：" + user.id);
            System.out.println("姓名：" + user.name);
            System.out.println("性别：" + user.sex);
            System.out.println("年龄：" + user.age);
            System.out.println("籍贯：" + user.place);
            System.out.println("爱好：" + user.like[0] + user.like[1] + user.like[2]);
            System.out.println("喜欢的编程语言：" + user.lange);
            
			// 关闭与客户机的连接
			client.close();
		} 
        catch (Exception e) 
        {
		}
	}
    
    /*
	 * 读取一个以'\r'结尾字符串输入
	 */
	private String readString(InputStream ins) throws IOException
    {
		StringBuffer msg = new StringBuffer();// 创建一个字符串缓冲区
        int a = 0;
		char c = 0;
        for(;(a = ins.read()) != '\r';)// 读取客户机的一个字节，以'\r'结束
        {
            c = (char)a;// 将输入流转换为字符显示
            msg.append(c);// 将读到的字符加到字符缓冲区中
        }
		String s = msg.toString().trim();// 将读到的BufferString转换为字符串，并调用trim()去掉尾部的空格
		return s;// 返回得到的字符串
	}
}