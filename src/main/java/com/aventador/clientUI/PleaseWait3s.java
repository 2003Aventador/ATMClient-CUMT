package com.aventador.clientUI;

import com.aventador.domain.CardSlot;
import com.aventador.util.CustomerInfoUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.Random;

public class PleaseWait3s extends JFrame {

    public PleaseWait3s() {

        initJFrame();

        initView();

        this.setVisible(true);

        //读取所有用户信息
        CustomerInfoUtil.readInfo();

        //取款机今日可用余额生成
        Random r = new Random();
        CardSlot.totalCash = new BigDecimal("" + r.nextDouble(80000) + 10000);
        System.out.println("source=CustomerInfoUtil ATM机中今日可取的金额数：" + CardSlot.totalCash);

        //不能直接使用Thread.Sleep方法，此方法会使整个线程睡眠3秒导致组件无法加载
        // Use a Timer to delay the exit by 3 seconds
        Timer timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ChooseLanguage();
                dispose();
            }
        });
        timer.setRepeats(false); // Set to false to only execute once
        timer.start();

    }

    private void initView() {

        //提示字样
        JLabel welText = new JLabel("读取银行卡中，请等候...", SwingConstants.CENTER);
        welText.setSize(800, 350);
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
