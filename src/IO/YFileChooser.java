package IO;

/**
 * @program: WorkSpace
 * @description:
 * @author: Mr.Th
 * @create: 2019-03-06 22:12
 **/
import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class YFileChooser implements ActionListener{
    JFrame frame=new JFrame("文件选择器实例");
    JTabbedPane tabPane=new JTabbedPane();//选项卡布局
    Container con=new Container();//布局1
    Container con1=new Container();//布局2
    JLabel label1=new JLabel("选择目录");
    JLabel label2=new JLabel("选择文件");
    JTextField text1=new JTextField();
    JTextField text2=new JTextField();
    JTextField text3=new JTextField();
    JButton button1=new JButton("...");
    JButton button2=new JButton("...");
    JFileChooser jfc=new JFileChooser();//文件选择器
    public YFileChooser(){
        jfc.setCurrentDirectory(new File("d:\\"));//文件选择器的初始目录定为d盘
        //下面两行是取得屏幕的高度和宽度
        double lx=Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double ly=Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        frame.setLocation(new Point((int)(lx/2)-150,(int)(ly/2)-150));//设定窗口出现位置
        frame.setSize(300,150);//设定窗口大小
        frame.setContentPane(tabPane);//设置布局
        //下面设定标签等的出现位置和高宽
        label1.setBounds(10,10,70,20);
        label2.setBounds(10,30,100,20);
        text1.setBounds(80,10,120,20);
        text2.setBounds(80,30,120,20);
        text3.setBounds(90,50,150,120);
        button1.setBounds(210,10,50,20);
        button2.setBounds(210,30,50,20);
        button1.addActionListener(this);//添加事件处理
        button2.addActionListener(this);//添加事件处理
        con.add(label1);
        con.add(label2);
        con.add(text1);
        con.add(text2);
        con.add(text3);
        con.add(button1);
        con.add(button2);
        con.add(jfc);
        tabPane.add("目录/文件选择",con);//添加布局1
        tabPane.add("暂无内容",con1);//添加布局2
        frame.setVisible(true);//窗口可见
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//使能关闭窗口，结束程序
    }
    public void actionPerformed(ActionEvent e){//事件处理
        File f = null;
        if(e.getSource().equals(button1)){//判断触发方法的按钮是哪个
            jfc.setFileSelectionMode(1);//设定只能选择到文件夹
            int state=jfc.showOpenDialog(null);//此句是打开文件选择器界面的触发语句
            if(state==1){
                return;//撤销则返回
            }
            else{
                f=jfc.getSelectedFile();//f为选择到的目录
                text1.setText(f.getAbsolutePath());
            }
        }
        if(e.getSource().equals(button2)){
            jfc.setFileSelectionMode(0);//设定只能选择到文件
            int state=jfc.showOpenDialog(null);//此句是打开文件选择器界面的触发语句
            if(state==1){
                return;//撤销则返回
            }
            else{
                f=jfc.getSelectedFile();//f为选择到的文件
                text2.setText(f.getAbsolutePath());
                try {
                    //读取文件(缓存字节流)
                    BufferedInputStream in = new BufferedInputStream(new FileInputStream(f.getAbsolutePath()));
                    //读取数据
                    //一次性取多少字节
                    byte[] bytes = new byte[2048];
                    //接受读取的内容(n就代表的相关数据，只不过是数字的形式)
                    int n = -1;
                    //循环取出数据
                    while ((n = in.read(bytes,0,bytes.length)) != -1) {
                        //转换成字符串
                        String str = new String(bytes,0,n,"GBK");
                        System.out.println(str);
                        //写入相关文件
                        text3.setText(str);
                    }
                    //清楚缓存
                    //关闭流
                    in.close();
                }catch (Exception es){
                    es.printStackTrace();
                }
            }
        }
    }
}
