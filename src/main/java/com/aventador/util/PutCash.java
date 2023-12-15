package com.aventador.util;

import com.aventador.domain.CardSlot;
import com.aventador.clientUI.DepositSuccessfully;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

public final class PutCash extends JFrame {
    private final JTextField integerTextField = new JTextField();
    private final JTextField decimalTextField = new JTextField();
    private final JButton okButton = new JButton("确定");
    private final JButton clearButton = new JButton("清除");
    private final JLabel label = new JLabel("请放入您的现金");

    public PutCash() {
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
                    System.out.println("========================================================");
                    System.out.println("source==PutCash 存款前user信息为：" + CustomerInfoUtil.user);
                    System.out.println("source==PutCash 存款前取款机可用余额为：" + CardSlot.totalCash);
                    BigDecimal amount = new BigDecimal("" + Double.parseDouble(integerText + "." + decimalText));
                    System.out.println("source==PutCash 卡槽中放入的金额数为：" + amount);
                    CardSlot.cashAmountJustNow = new BigDecimal("" + amount);
                    //CardSlot.totalCash += amount;
                    CardSlot.totalCash = CardSlot.totalCash.add(amount);
                    integerTextField.setText("");
                    decimalTextField.setText("");
                    TransactionDetails.writeInfo(1, new BigDecimal("0"));
                    new DepositSuccessfully();
                    System.out.println("source==PutCash 存款后user信息为：" + CustomerInfoUtil.user);
                    System.out.println("source==PutCash 存款后取款机可用余额为：" + CardSlot.totalCash);
                    System.out.println("========================================================");
                    dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(PutCash.this, "请放入有效金额");
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
        setLayout(null);

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
            System.out.println("卡槽存款界面背景图片没有被加载出来");
        }
    }

    private void initJFrame() {
        setTitle("中矿银行自动取款机卡槽");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

}

