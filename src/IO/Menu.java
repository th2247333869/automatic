package IO;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.*;
import java.util.*;
import javax.swing.JFileChooser;
import javax.swing.*;

/**
 * @program: WorkSpace
 * @description:
 * @author: Mr.Th
 * @create: 2019-03-06 22:00
 **/
public class Menu extends JFrame
{
    public Menu()
    {
        super("MenuDemo");
        // 菜单栏
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("文件(F)");
        // 设置助记符为F，按下ALT + F 可以触发该菜单
        file.setMnemonic('F');

        JMenuItem open = new JMenuItem("打开");
        JMenuItem quit = new JMenuItem("退出");

        file.add(open);
        // 设置菜单分隔符
        file.addSeparator();
        file.add(quit);

        menuBar.add(file);

        // 设置菜单栏，使用这种方式设置菜单栏可以不占用布局空间
        setJMenuBar(menuBar);

        // 以上为普通菜单，菜单还有一种为弹出式菜单，通过鼠标右击弹出
        final JPopupMenu menu = new JPopupMenu();
        JMenuItem pop = new JMenuItem("编辑");
        menu.add(pop);

        // TODO Auto-generated method stub
        JFileChooser jfc=new JFileChooser();

        JTextArea textArea = new JTextArea();
        // 添加鼠标事件监听器，使用适配器
        textArea.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e)
            {
                if (e.isPopupTrigger())
                {
                    // 显示弹出式菜单
                    menu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
        add(new JScrollPane(textArea));

        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}
