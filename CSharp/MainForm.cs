using System.Windows.Forms;
using System.Drawing;
using System;

namespace BoxMan
{
    class MainForm : Form
    {
        //游戏地图对象
        Map map;
        //PictureBox对象
        PictureBox[,] pictureBox;
        //控制按钮
        Button wBtn, aBtn, sBtn, dBtn, restart;
        Button jum;
        //关卡数
        Label passnum;
        TextBox jumpass;

        public MainForm()
        {
            map = new Map();
            wBtn = new Button();
            aBtn = new Button();
            sBtn = new Button();
            dBtn = new Button();
            restart = new Button();
            jum = new Button();
            passnum = new Label();
            jumpass = new TextBox();
            pictureBox = new PictureBox[Map.M,Map.N];
            
            for (int i = 0; i < Map.M; i++)
            {
                for (int j = 0; j < Map.N; j++)
                {
                    pictureBox[i,j] = new PictureBox();
                }
            }

            map.pass = 1;
            map.ReadMap();
            DrawMap();
            showUI();
            EventListener();
        }

        //禁用键盘上下左右默认响应事件：切换控件焦点
        protected override bool ProcessDialogKey(Keys keyData)
        {
            if (keyData == Keys.Up || keyData == Keys.Down ||
            keyData == Keys.Left || keyData == Keys.Right)
                return false;
            else
                return base.ProcessDialogKey(keyData);
        }

        void showUI()
        {
            this.Size = new Size(480, 320);
            this.Text = "推箱子";
            //不可调整窗口大小 禁用最大化按钮
            this.FormBorderStyle = FormBorderStyle.FixedDialog;
            this.MaximizeBox = false;
            //设置可读取键盘按下
            this.KeyPreview = true;
            //无法实现键盘监是因为没有获取屏幕焦点，将需要监听的控件获取屏幕焦点即可
            this.Focus();
            this.SuspendLayout();

            //设置控件位置
            wBtn.Location = new Point(370, 185);
            wBtn.Size = new Size(45, 45);
            aBtn.Location = new Point(325, 230);
            aBtn.Size = new Size(45, 45);
            sBtn.Location = new Point(370, 230);
            sBtn.Size = new Size(45, 45);
            dBtn.Location = new Point(415, 230);
            dBtn.Size = new Size(45, 45);
            restart.Location = new Point(340, 50);
            restart.Size = new Size(80, 40);
            jum.Location = new Point(410, 120);
            jum.Size = new Size(40, 20);
            passnum.Location = new Point(360, 20);
            passnum.Size = new Size(80, 20);
            jumpass.Location = new Point(340, 120);
            jumpass.Size = new Size(60, 20);
            //设置控件文本
            wBtn.Text = "W";
            aBtn.Text = "A";
            sBtn.Text = "S";
            dBtn.Text = "D";
            restart.Text = "重来";
            jum.Text = "跳关";
            passnum.Text = "第" + Convert.ToString(map.pass) + "关";
            //控件加入窗体
            this.Controls.Add(wBtn);
            this.Controls.Add(aBtn);
            this.Controls.Add(sBtn);
            this.Controls.Add(dBtn);
            this.Controls.Add(restart);
            this.Controls.Add(jum);
            this.Controls.Add(passnum);
            this.Controls.Add(jumpass);

            for (int i = 0; i < Map.M; i++)
            {
                for (int j = 0; j < Map.N; j++)
                {
                    pictureBox[i, j].Location = new Point(32 * j, 32 * i);
                    pictureBox[i, j].Size = new Size(32, 32);
                    this.Controls.Add(pictureBox[i,j]);
                }
            }
        }

        //事件监听器
        void EventListener()
        {
            //使用拉姆达表达式与按钮事件绑定
            wBtn.Click += (obj, even) =>
            {
                map.ChangeDataIcon('w');
                DrawMap();
                end();          //游戏是否结束
                this.Focus();   //把焦点还给MainForm
            };
            aBtn.Click += (obj, even) =>
            {
                map.ChangeDataIcon('a');
                DrawMap();
                end();
                this.Focus();
            };
            sBtn.Click += (obj, even) =>
            {
                map.ChangeDataIcon('s');
                DrawMap();
                end();
                this.Focus();
            };
            dBtn.Click += (obj, even) =>
            {
                map.ChangeDataIcon('d');
                DrawMap();
                end();
                this.Focus();
            };
            restart.Click += (obj, even) =>
            {
                map.IniMap();
                DrawMap();
                end();
                this.Focus();
            };
            jum.Click += (obj, even) =>
            {
                try
                {
                    int p = Convert.ToInt32(jumpass.Text);
                    if (p <= 9 && p >= 1)
                    {
                        map.pass = p;
                        map.ReadMap();
                        DrawMap();
                        passnum.Text = "第" + Convert.ToString(map.pass) + "关";
                    }
                }
                catch(Exception e)
                {
                    Console.WriteLine(e.Message);
                }
                finally
                {
                    jumpass.Text = "";
                    this.Focus();
                }
            };

            //窗体键盘事件监听
            this.KeyDown += MainForm_KeyDown;
            //jumpass.KeyDown += Jumpass_KeyDown;
        }

        private void MainForm_KeyDown(object sender, KeyEventArgs e)
        {
            int key = e.KeyValue;
            Console.WriteLine(key);
            switch(key)
            {
                case 'w':
                case 'W':
                case (char)Keys.Up:
                    map.ChangeDataIcon('w');
                    DrawMap();
                    end();
                    break;
                case 'a':
                case 'A':
                case (char)Keys.Left:
                    map.ChangeDataIcon('a');
                    DrawMap();
                    end();
                    break;
                case 's':
                case 'S':
                case (char)Keys.Down:
                    map.ChangeDataIcon('s');
                    DrawMap();
                    end();
                    break;
                case 'd':
                case 'D':
                case (char)Keys.Right:
                    map.ChangeDataIcon('d');
                    DrawMap();
                    end();
                    break;
            }
        }

        // TODO: 控制文本框jumpass的输入
        private void Jumpass_KeyDown(object sender, KeyEventArgs e)
        {
            // 如果键入的字符是0~9，或者按键是Del键或Backspace键，则
            // 直接返回读入的键盘字符，否则，设置键入的字符为键位未知（0）
            if (((0x30 <= e.KeyValue) && (e.KeyValue <= 0x39)) || (e.KeyValue == 127) || (e.KeyValue == 8))
            {
                //e.Handled = true;
                //jumpass.Text += (char)e.KeyValue;
            }
            else
            {
                /*
                string str = jumpass.Text;
                if(str.Length <= 1)
                {
                    jumpass.Text = "";
                }
                else
                {
                    jumpass.Text = str.Substring(0, str.Length - 1);
                }
                
                Console.WriteLine(str);
                jumpass.Focus();
                */
            }
        }

        //绘制地图
        void DrawMap()
        {
            for (int i = 0; i < Map.M; i++)
            {
                for (int j = 0; j < Map.N; j++)
                {
                    pictureBox[i,j].Image = Image.FromFile("../../imgUI/" + map.plat[i,j] + ".gif");
                }
            }
        }

        //判断游戏是否结束
        void end()
        {
            if (map.IsPast())
            {
                if (map.pass == 9)
                {
                    MessageBox.Show("恭喜你通过所有关卡！");
                }
                else
                {
                    MessageBox.Show("第" + map.pass + "关通过！");
                    map.pass = map.pass + 1;
                    map.ReadMap();
                    DrawMap();
                    passnum.Text = "第" + Convert.ToString(map.pass) + "关";
                }
            }
        }
    }
}
