package com.aventador.clientUI;

import com.aventador.domain.CardSlot;
import com.aventador.util.CustomerInfoUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

public class EnquiryBalance extends JFrame {

    JButton jb1 = new JButton("转账");
    JButton jb2 = new JButton("取款");
    JButton jb3 = new JButton("存款");
    JButton jb4 = new JButton("返回");
    JButton jb5 = new JButton("退卡");

    public EnquiryBalance() {

        initJFrame();

        initView();

        addListener();

        this.setVisible(true);

    }

    private void addListener() {

        //Transfer
        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TransferInputAccount();
                dispose();
            }
        });

        //Withdraw
        jb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                CardSlot.getMoney();
            }
        });

        //Deposit
        jb3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                CardSlot.putMoney();
            }
        });

        //return last
        jb4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ServiceSelection();
            }
        });

        //CardReturned
        jb5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new CardReturned();
            }
        });

    }

    private void initView() {

        //中文提示  后续可以添加英文提示
        JLabel welText = new JLabel("<HTML>请查看您的账户余额</HTML>", SwingConstants.CENTER);
        welText.setSize(450, 100);
        Font welTextFont = new Font("楷体", Font.BOLD, 15);//设置字体、字号
        // 粗体样式常量
        //welText.setForeground(new Color(111,222,0));//设置颜色
        welText.setFont(welTextFont);

        // 创建小框架
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(80, 50, 450, 220);
        Color borderColor = Color.decode("#7A8A99");
        panel.setBorder(BorderFactory.createLineBorder(borderColor, 2));
        panel.setOpaque(false); // 使面板透明
        this.getContentPane().add(panel);
        panel.add(welText);

        // 添加第一个标签和文本框
        JLabel jLabel1 = new JLabel("账户总余额:");
        jLabel1.setSize(80, 20);
        jLabel1.setBounds(50, 100, 150, 20);
        panel.add(jLabel1);

        JTextField totalBalance = new JTextField();
        totalBalance.setBounds(220, 100, 150, 20);
        totalBalance.setEditable(false);
        panel.add(totalBalance);

        // 添加第二个标签和文本框
        JLabel jLabel2 = new JLabel("账户当日可用余额：");
        jLabel2.setSize(80, 20);
        jLabel2.setBounds(50, 130, 150, 20);
        panel.add(jLabel2);

        JTextField availableBalance = new JTextField();
        availableBalance.setBounds(220, 130, 150, 20);
        availableBalance.setEditable(false);
        panel.add(availableBalance);

        //test code:
        //CustomerInfoUtil.makeTestInfo();

        try {
            totalBalance.setText("" + CustomerInfoUtil.user.getBalance());
            availableBalance.setText("" + CustomerInfoUtil.user.getBalance().multiply(new BigDecimal("0.2")));
        } catch (NullPointerException k) {
            System.out.println("EnquiryBalance界面没有打开初始化代码导致user为空");
        }


        int _x = this.getWidth() - 190;
        int y = 250;
        jb1.setBounds(_x, y - 20, 150, 40);
        jb2.setBounds(_x, y + 40, 150, 40);
        jb3.setBounds(_x, y + 100, 150, 40);
        jb4.setBounds(_x, y + 160, 150, 40);
        jb5.setBounds(40, y + 160, 150, 40);

        jb1.setContentAreaFilled(false);
        jb2.setContentAreaFilled(false);
        jb3.setContentAreaFilled(false);
        jb4.setContentAreaFilled(false);
        jb5.setContentAreaFilled(false);


        this.getContentPane().add(jb1);
        this.getContentPane().add(jb2);
        this.getContentPane().add(jb3);
        this.getContentPane().add(jb4);
        this.getContentPane().add(jb5);


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
