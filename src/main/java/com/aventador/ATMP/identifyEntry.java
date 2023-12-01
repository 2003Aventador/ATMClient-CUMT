package com.aventador.ATMP;

import com.aventador.clientUI.Welcome;
import com.aventador.registerUI.RegisterUsers;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class identifyEntry extends JFrame implements MouseListener {

    JButton jb1 = new JButton("管理员");
    JButton jb2 = new JButton("用户");

    public identifyEntry() {

        initJFrame();

        initView();

        this.setVisible(true);

    }

    private void initView() {

        //选择语言界面
        JLabel identifyText = new JLabel("请选择您要获取的权限", SwingConstants.CENTER);
        identifyText.setSize(800, 300);
        Font welTextFont = new Font("楷体", Font.BOLD, 30);//设置字体、字号
        // 粗体样式常量
        //welText.setForeground(new Color(111,222,0));//设置颜色
        identifyText.setFont(welTextFont);
        this.getContentPane().add(identifyText);

        //添加语言选择按钮
        jb1.setBounds(250, 250, 80, 40);
        jb1.setContentAreaFilled(false);//设置按钮为透明，这样不会挡住后面的背景

        jb2.setBounds(460, 250, 80, 40);
        jb2.setContentAreaFilled(false);//设置按钮为透明，这样不会挡住后面的背景

        Color borderColor = Color.decode("#E2E9F1");
        jb1.setBorder(new LineBorder(borderColor, 1));
        jb2.setBorder(new LineBorder(borderColor, 1));

        this.getContentPane().add(jb1);
        this.getContentPane().add(jb2);

        jb1.addMouseListener(this);
        jb2.addMouseListener(this);

        jb1.setContentAreaFilled(false);
        jb2.setContentAreaFilled(false);

        //添加背景图片
        String path = "image/RegisterBack.jpg";
        JLabel background = new JLabel(new ImageIcon(path));
        background.setBounds(0, 0, 800, 500);
        this.add(background, BorderLayout.CENTER);
        background.setLayout(new BorderLayout());

    }

    private void initJFrame() {

        this.setResizable(false);//禁用最大化窗口
        this.setSize(800, 500);//设置宽高
        this.setTitle("中矿银行ATM机系统客户端");//设置标题
        this.setLocationRelativeTo(null);//居中
        this.setAlwaysOnTop(true);//置顶
        this.setLayout(null);//取消内部默认布局
        this.setDefaultCloseOperation(3);//关闭窗口后自动退出程序

    }


    @Override
    public void mouseClicked(MouseEvent e) {
        Object source = e.getSource();
        if (source == jb1) {
            //管理员登录
            new RegisterUsers();
        } else if (source == jb2) {
            //用户登录
            new Welcome();
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
