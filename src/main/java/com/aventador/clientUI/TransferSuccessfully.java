package com.aventador.clientUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransferSuccessfully extends JFrame {

    //必须设置构造函数权限为public
    public TransferSuccessfully() {

        initFrame();//初始化ui界面

        initView();//添加文字、图片素材

        this.setVisible(true);

        //等待三秒之后进入下一个界面
        //不能直接使用Thread.Sleep方法，此方法会使整个线程睡眠3秒导致组件无法加载
        // Use a Timer to delay the exit by 3 seconds
        Timer timer = new Timer(2000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //put your code here
                new ServiceSelection();
                dispose();
            }
        });
        timer.setRepeats(false); // Set to false to only execute once
        timer.start();


    }

    public void initFrame() {

        this.setResizable(false);//禁用最大化窗口
        this.setSize(800, 500);//设置宽高
        this.setTitle("中矿银行ATM机系统客户端");//设置标题
        this.setLocationRelativeTo(null);//居中
        this.setAlwaysOnTop(true);//置顶
        this.setLayout(new BorderLayout());//取消内部默认布局
        this.setDefaultCloseOperation(3);//关闭窗口后自动退出程序

    }

    public void initView() {

        //欢迎字样
        JLabel welText = new JLabel("转账成功，即将返回", SwingConstants.CENTER);
        welText.setSize(800, 400);
        Font welTextFont = new Font("楷体", Font.BOLD, 30);//设置字体、字号
        // 粗体样式常量
        //welText.setForeground(new Color(111,222,0));//设置颜色
        welText.setFont(welTextFont);
        this.getContentPane().add(welText);


        //添加背景图片
        String path = "image/background.jpg";
        JLabel background = new JLabel(new ImageIcon(path));
        background.setBounds(0, 0, 800, 500);
        this.add(background, BorderLayout.CENTER);
        background.setLayout(new BorderLayout());
    }


}
