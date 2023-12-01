package com.aventador.clientUI;

import com.aventador.domain.CardSlot;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServiceSelection extends JFrame {

    JButton jb1 = new JButton("查询账户余额");
    JButton jb2 = new JButton("其他业务");
    JButton jb3 = new JButton("退卡");
    JButton jb4 = new JButton("转账");
    JButton jb5 = new JButton("取款");
    JButton jb6 = new JButton("存款");
    JButton jb7 = new JButton("修改密码");

    public ServiceSelection() {

        initJFrame();

        initView();

        addJButtonListener();

        this.setVisible(true);

    }

    private void addJButtonListener() {

        //查询账户余额
        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new EnquiryBalance();
            }
        });

        //其他业务
        jb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MoreOption();
            }
        });


        //退卡
        jb3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CardReturned();
                dispose();
            }
        });

        //转账
        jb4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TransferInputAccount();
                dispose();
            }
        });

        //取款
        jb5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                CardSlot.getMoney();
            }
        });

        //存款
        jb6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardSlot.putMoney();
                dispose();
            }
        });

        //修改密码
        jb7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new InputOldPassword();
                dispose();
            }
        });

    }

    private void initView() {

        //中文提示  后续可以添加英文提示
        JLabel welText = new JLabel("<HTML>请  选  择  服  务</HTML>", SwingConstants.CENTER);
        welText.setSize(800, 300);
        Font welTextFont = new Font("楷体", Font.BOLD, 30);//设置字体、字号
        // 粗体样式常量
        //welText.setForeground(new Color(111,222,0));//设置颜色
        welText.setFont(welTextFont);
        this.getContentPane().add(welText);

        int _x = this.getWidth() - 190;
        int y = 250;
        jb1.setBounds(40, y + 40, 150, 40);
        jb2.setBounds(40, y + 100, 150, 40);
        jb3.setBounds(40, y + 160, 150, 40);
        jb4.setBounds(_x, y - 20, 150, 40);
        jb5.setBounds(_x, y + 40, 150, 40);
        jb6.setBounds(_x, y + 100, 150, 40);
        jb7.setBounds(_x, y + 160, 150, 40);

        jb1.setContentAreaFilled(false);
        jb2.setContentAreaFilled(false);
        jb3.setContentAreaFilled(false);
        jb4.setContentAreaFilled(false);
        jb5.setContentAreaFilled(false);
        jb6.setContentAreaFilled(false);
        jb7.setContentAreaFilled(false);


        this.getContentPane().add(jb1);
        this.getContentPane().add(jb2);
        this.getContentPane().add(jb3);
        this.getContentPane().add(jb4);
        this.getContentPane().add(jb5);
        this.getContentPane().add(jb6);
        this.getContentPane().add(jb7);

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
