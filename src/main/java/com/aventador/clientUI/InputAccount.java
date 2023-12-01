package com.aventador.clientUI;

import com.aventador.domain.Customer;
import com.aventador.util.CustomerInfoUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class InputAccount extends JFrame implements MouseListener {

    private JLabel label;
    private JTextField textField;
    private JButton confirmButton;
    private JButton clearButton;

    private String account;

    public InputAccount() {

        initJFrame();

        initView();

        this.setVisible(true);
    }

    private void initView() {

        label = new JLabel("请输入您的账号:");
        label.setBounds(350, 150, 200, 20);
        this.add(label);

        textField = new JTextField(20);
        textField.setBounds(290, 200, 210, 30);
        textField.setFont(new Font("楷体", Font.BOLD, 15));
        this.add(textField);

        confirmButton = new JButton("确定");
        confirmButton.setBounds(300, 250, 70, 30);
        this.add(confirmButton);

        clearButton = new JButton("清除");
        clearButton.setBounds(420, 250, 70, 30);
        this.add(clearButton);

        confirmButton.addMouseListener(this);
        clearButton.addMouseListener(this);

        confirmButton.setContentAreaFilled(false);
        clearButton.setContentAreaFilled(false);

        try {
            JLabel background = new JLabel(new ImageIcon(ImageIO.read(new File("image/background.jpg"))));
            background.setBounds(0, 0, 800, 500);
            this.getContentPane().add(background);
        } catch (IOException e) {
            System.out.println("输入账号界面背景图片没有加载成功");
        }
    }

    private void initJFrame() {
        this.setResizable(false);
        this.setSize(800, 500);
        this.setTitle("中矿银行ATM机系统客户端");
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object source = e.getSource();
        if (source == confirmButton) {
            account = textField.getText();
            //CustomerInfoUtil.readInfo(); // --测试时打开
            try {
                for (Customer user : CustomerInfoUtil.normalUsers) {
                    if (user.getAccount().equals(account)) {
                        new InputPassword();
                        CustomerInfoUtil.user = user;
                        CustomerInfoUtil.customerTodayAvailableCash = Math.min(user.getBalance() * 0.2, 50000);
                        this.dispose();
                    }
                }
                //normalUsers里没找到
                if (this.isVisible()) {
                    showDialog(account);
                }
            } catch (NullPointerException k) {
                System.out.println("请打开InputAccount中mouseClicked中的注释");
            }
        } else if (source == clearButton) {
            textField.setText("");
        }
    }

    private void showDialog(String account) {

        String content = "无此账号，请重新你输入";

        for (Customer frozenUser : CustomerInfoUtil.frozenUsers) {
            if (frozenUser.getAccount().equals(account)) {
                content = "账户已被冻结，请联系管理员";
            }
        }

        //无此账号，弹窗提示
        JDialog warDialog = new JDialog();
        warDialog.setSize(200, 150);
        warDialog.setAlwaysOnTop(true);
        warDialog.setLocationRelativeTo(null);
        warDialog.setModal(true);

        JLabel warning = new JLabel(content);
        warning.setBounds(0, 0, 200, 150);
        warDialog.getContentPane().add(warning);

        warDialog.setVisible(true);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
