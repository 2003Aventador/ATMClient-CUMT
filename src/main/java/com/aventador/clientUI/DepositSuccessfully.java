package com.aventador.clientUI;

import com.aventador.domain.CardSlot;
import com.aventador.domain.Customer;
import com.aventador.util.CustomerInfoUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class DepositSuccessfully extends JFrame {

    //按钮
    JButton jb1 = new JButton("返回");
    JButton jb2 = new JButton("退卡");

    public DepositSuccessfully() {

        initJFrame();

        initView();

        addListener();

        infoProcessing();


    }

    private void addListener() {

        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ServiceSelection();
                dispose();
            }
        });

        jb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //CardReturned
                new CardReturned();
                dispose();
            }
        });

    }

    private void infoProcessing() {
        //更新后台用户信息
        try {
            for (Customer eachUser : CustomerInfoUtil.normalUsers) {
                if (eachUser.getAccount().equals(CustomerInfoUtil.user.getAccount())) {
                    eachUser.setBalance(eachUser.getBalance() + CardSlot.cashAmountJustNow);
                }
            }
        } catch (NullPointerException k) {
            System.out.println("存款成功界面未读入用户信息");
        }

    }

    private void initView() {

        //提示字样
        JLabel success = new JLabel("存款成功");
        success.setBounds(320, 180, 150, 30);

        //设置字体、字号
        Font sucTextFont = new Font("楷体", Font.BOLD, 30);
        // 粗体样式常量
        success.setFont(sucTextFont);
        this.getContentPane().add(success);


        jb1.setBounds(610, 410, 150, 40);
        jb2.setBounds(40, 410, 150, 40);

        jb1.setContentAreaFilled(false);
        jb2.setContentAreaFilled(false);

        this.getContentPane().add(jb1);
        this.getContentPane().add(jb2);

        //背景图片
        try {
            JLabel background = new JLabel(new ImageIcon(ImageIO.read(new File("image/background.jpg"))));
            background.setBounds(0, 0, 800, 500);
            this.getContentPane().add(background);
        } catch (IOException e) {
            System.out.println("存款成功界面背景图片没有被加载出来");
        }

        this.setVisible(true);

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
