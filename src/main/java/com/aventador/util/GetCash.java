package com.aventador.util;

import com.aventador.domain.CardSlot;
import com.aventador.clientUI.WaitForGetMoney;
import com.aventador.domain.Transaction;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class GetCash extends JFrame {

    private final JTextField integerTextField = new JTextField();
    private final JTextField decimalTextField = new JTextField();
    private final JButton okButton = new JButton("确定");
    private final JButton clearButton = new JButton("清除");
    private final JLabel label = new JLabel("请输入您的取款金额");

    public GetCash() {
        initJFrame();
        initView();
        initAction();
        setVisible(true);
    }

    private void initAction() {
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String integerText = integerTextField.getText();
                String decimalText = decimalTextField.getText();

                try {
                    double amount = Double.parseDouble(integerText + "." + decimalText);

                    //判断用户是否能取款成功

                    //生成虚假测试信息
                    //CustomerInfoUtil.makeTestInfo();
                    //用户可取款金额
                    double customerAvailable = CustomerInfoUtil.customerTodayAvailableCash;
                    try {
                        System.out.println("========================================================");
                        System.out.println("source==GetCash 取款前user信息为：" + CustomerInfoUtil.user.toString());
                        System.out.println("source==GetCash 取款前取款机可用余额为：" + CardSlot.totalCash);
                        System.out.println("source==GetCash 今日用户可取款金额为：" + CustomerInfoUtil.customerTodayAvailableCash);
                        if (amount <= Math.min(customerAvailable, CardSlot.totalCash)) {
                            //取款成功
                            CustomerInfoUtil.user.setBalance(CustomerInfoUtil.user.getBalance() - amount);
                            CardSlot.totalCash -= amount;
                            CustomerInfoUtil.customerTodayAvailableCash -= amount;
                            TransactionDetails.writeInfo(2, amount);
                            System.out.println("source==GetCash 取款后user信息为：" + CustomerInfoUtil.user.toString());
                            System.out.println("source==GetCash 取款后取款机可用余额为：" + CardSlot.totalCash);
                            System.out.println("========================================================");
                            //等待取钞界面
                            new WaitForGetMoney();
                            dispose();
                        } else if (amount > CustomerInfoUtil.user.getBalance()) {
                            //您的余额不足
                            JOptionPane.showMessageDialog(GetCash.this, "您的余额不足！");
                            System.out.println("========================================================");
                        } else if (amount > CustomerInfoUtil.customerTodayAvailableCash) {
                            JOptionPane.showMessageDialog(GetCash.this, "您今日的取款额度不足！");
                            System.out.println("========================================================");
                        } else if (amount > CardSlot.totalCash) {
                            //取款机现金不足，请到柜台办理业务
                            JOptionPane.showMessageDialog(GetCash.this, "ATM机现金不足，请到柜台办理业务!");
                            System.out.println("========================================================");
                        }
                    } catch (NullPointerException k) {
                        k.printStackTrace();
                        System.out.println("GetCash类中没有生成虚假信息，请调用makeTestInfo方法");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(GetCash.this, "请输入有效取款金额");
                    integerTextField.setText("");
                    decimalTextField.setText("");
                }
            }
        });

        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                integerTextField.setText("");
                decimalTextField.setText("");
            }
        });
    }

    private void initView() {

        label.setBounds(350, 150, 200, 20);
        add(label);

        integerTextField.setBounds(290, 200, 100, 20);
        add(integerTextField);

        decimalTextField.setBounds(410, 200, 100, 20);
        add(decimalTextField);

        okButton.setBounds(300, 250, 80, 30);
        add(okButton);

        clearButton.setBounds(420, 250, 80, 30);
        add(clearButton);

        okButton.setContentAreaFilled(false);
        clearButton.setContentAreaFilled(false);

        try {
            JLabel background = new JLabel(new ImageIcon(ImageIO.read(new File("image/background.jpg"))));
            background.setBounds(0, 0, 800, 500);
            this.getContentPane().add(background);
        } catch (IOException e) {
            System.out.println("卡槽取款界面背景图片没有被加载出来");
        }
    }

    private void initJFrame() {

        setTitle("中矿银行自动取款机卡槽");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

    }


}
