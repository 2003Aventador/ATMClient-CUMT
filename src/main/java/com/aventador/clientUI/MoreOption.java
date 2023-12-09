package com.aventador.clientUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MoreOption extends JFrame {

    JButton jb1 = new JButton("打印交易明细");
    JButton jb2 = new JButton("联系我们");
    JButton jb3 = new JButton("返回");

    public MoreOption() {

        initJFrame();

        initView();

        addJButtonListener();

        this.setVisible(true);

    }

    private void addJButtonListener() {

        //打印凭证
        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new PrintVoucher();
            }
        });

        //联系我们
        jb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ContactMe();
            }
        });

        //return
        jb3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ServiceSelection();
                dispose();
            }
        });

    }

    private void initView() {

        //中文提示  后续可以添加英文提示
        JLabel jLabel = new JLabel("<HTML>请  选  择  服  务</HTML>", SwingConstants.CENTER);
        jLabel.setSize(800, 300);
        Font welTextFont = new Font("楷体", Font.BOLD, 30);//设置字体、字号
        // 粗体样式常量
        //welText.setForeground(new Color(111,222,0));//设置颜色
        jLabel.setFont(welTextFont);
        this.getContentPane().add(jLabel);

        int _x = this.getWidth() - 190;
        int y = 250;
        jb1.setBounds(40, y + 160, 150, 40);
        jb2.setBounds(_x, y + 100, 150, 40);
        jb3.setBounds(_x, y + 160, 150, 40);

        jb1.setContentAreaFilled(false);
        jb2.setContentAreaFilled(false);
        jb3.setContentAreaFilled(false);

        this.getContentPane().add(jb1);
        this.getContentPane().add(jb2);
        this.getContentPane().add(jb3);

        //添加背景图片
        String path = "image/background.jpg";
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
        this.setLayout(new BorderLayout());//取消内部默认布局
        this.setDefaultCloseOperation(3);//关闭窗口后自动退出程序

    }

}
