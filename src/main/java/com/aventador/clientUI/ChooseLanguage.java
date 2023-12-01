package com.aventador.clientUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ChooseLanguage extends JFrame implements MouseListener {

    JButton jb1 = new JButton("<html>中&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp文<br>Chinese</html>");
    JButton jb2 = new JButton("<html>英&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp文<br>English</html>");

    public ChooseLanguage() {

        initJFrame();

        initView();

        //等待控件全部加载完后再令框架可见
        this.setVisible(true);
    }

    private void initView() {

        //选择语言界面
        JLabel welText = new JLabel("请选择您的语言", SwingConstants.CENTER);
        welText.setSize(800, 250);
        Font welTextFont = new Font("楷体", Font.BOLD, 30);//设置字体、字号
        // 粗体样式常量
        //welText.setForeground(new Color(111,222,0));//设置颜色
        welText.setFont(welTextFont);
        this.getContentPane().add(welText);

        //添加语言选择按钮
        jb1.setBounds(50, 380, 80, 40);
        jb1.setContentAreaFilled(false);//设置按钮为透明，这样不会挡住后面的背景

        jb2.setBounds(660, 380, 80, 40);
        jb2.setContentAreaFilled(false);//设置按钮为透明，这样不会挡住后面的背景


        this.getContentPane().add(jb1);
        this.getContentPane().add(jb2);

        jb1.addMouseListener(this);
        jb2.addMouseListener(this);

        jb1.setContentAreaFilled(false);
        jb2.setContentAreaFilled(false);

        //添加背景图片
        String path = "image/background.jpg";
        JLabel background = new JLabel(new ImageIcon(path));
        background.setBounds(0, 0, 800, 500);
        this.add(background,BorderLayout.CENTER);
        background.setLayout(new BorderLayout());
    }

    private void initJFrame() {

        this.setResizable(false);//禁用最大化窗口
        this.setSize(800, 500);//设置宽高
        this.setTitle("中矿银行ATM机系统客户端");//设置标题
        this.setLocationRelativeTo(null);//居中
        this.setAlwaysOnTop(true);//置顶
        this.setLayout(new BorderLayout());//取消内部默认布局
        this.setDefaultCloseOperation(3);//关闭窗口后自动退出程序

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == jb1) {
            //中文
            /*
            Reason:
                Swing is not thread-safe, and all Swing components should be accessed and manipulated on the Event Dispatch Thread (EDT).
                The SwingUtilities.invokeLater is a convenient way to ensure that a piece of code is executed on the EDT.
                In this case, when creating a new instance of inputPassword,
                using SwingUtilities.invokeLater helps ensure that the new frame and its components are created and made visible on the EDT, avoiding potential threading issues.

                By making these adjustments, you ensure a more reliable and robust initialization and display of Swing components in your application.
             */
            SwingUtilities.invokeLater(() -> {
                new InputAccount();
                this.dispose();
            });
        } else if (e.getSource() == jb2) {
            //英文

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
