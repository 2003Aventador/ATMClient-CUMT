package com.aventador.clientUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WaitForGetMoney extends JFrame {

    public WaitForGetMoney() {

        initJFrame();

        initView();

        this.setVisible(true);

        //不能直接使用Thread.Sleep方法，此方法会使整个线程睡眠3秒导致组件无法加载
        // Use a Timer to delay the exit by 3 seconds
        Timer timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ServiceSelection();
            }
        });
        timer.setRepeats(false); // Set to false to only execute once
        timer.start();

    }

    private void initView() {

        //提示字样
        JLabel getText = new JLabel("请取走您的现金", SwingConstants.CENTER);
        getText.setSize(800, 350);
        Font getTextFont = new Font("楷体", Font.BOLD, 30);//设置字体、字号
        // 粗体样式常量
        //getText.setForeground(new Color(111,222,0));//设置颜色
        getText.setFont(getTextFont);
        this.getContentPane().add(getText);

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
