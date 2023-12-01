package com.aventador.clientUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class ContactMe extends JFrame {

    JButton jb = new JButton("返回");

    public ContactMe() {

        initJFrame();

        initView();

        this.setVisible(true);

    }

    private void initView() {

        //欢迎字样
        JLabel jLabel = new JLabel("有更多需求，请联系我们：", SwingConstants.CENTER);
        jLabel.setSize(800, 100);
        Font welTextFont = new Font("楷体", Font.BOLD, 20);//设置字体、字号
        // 粗体样式常量
        //welText.setForeground(new Color(111,222,0));//设置颜色
        jLabel.setFont(welTextFont);
        this.getContentPane().add(jLabel);

        jb.setBounds(610, 410, 150, 40);
        jb.setContentAreaFilled(false);
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MoreOption();
            }
        });
        this.getContentPane().add(jb);

        JLabel contactMe = null;
        try {
            contactMe = new JLabel(new ImageIcon(ImageIO.read(new File("image/WeChat.png"))));
        } catch (IOException e) {
            System.out.println("更多操作界面微信二维码没有加载出来");
        }
        contactMe.setBounds((this.getWidth() - 300) / 2, 100, 300, 300);
        this.getContentPane().add(contactMe);

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
}
