package com.aventador.clientUI;

import com.aventador.util.CustomerInfoUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

public class TransferInputAmount extends JFrame {

    private final JTextField integerTextField = new JTextField();
    private final JTextField decimalTextField = new JTextField();
    private final JButton okButton = new JButton("确定");
    private final JButton clearButton = new JButton("清除");
    private final JLabel label = new JLabel("请输入您的转账金额");

    public TransferInputAmount() {
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
                    BigDecimal amount = new BigDecimal("" + Double.parseDouble(integerText + "." + decimalText));
                    BigDecimal userTransferAmount = CustomerInfoUtil.user.getBalance();
                    try {
//                        if (amount <= userTransferAmount) {
                        if (amount.doubleValue() <= userTransferAmount.doubleValue()) {
                            //转账信息确认
                            CustomerInfoUtil.transferAmount = amount;
                            new TransferConfirmedInfo();
                            dispose();
                        } else {
                            //您的余额不足
                            JOptionPane.showMessageDialog(TransferInputAmount.this, "您的余额不足！");
                            System.out.println("========================================================");
                        }
                    } catch (NullPointerException k) {
                        k.printStackTrace();
                        System.out.println("TransferInputAccount类中没有生成虚假信息");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(TransferInputAmount.this, "请输入有效转账金额");
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
            System.out.println("转账界面背景图片没有被加载出来");
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
