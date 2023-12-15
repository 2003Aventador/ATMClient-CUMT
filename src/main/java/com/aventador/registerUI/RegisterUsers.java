package com.aventador.registerUI;

import com.aventador.domain.Customer;
import com.aventador.util.CustomerInfoUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

public final class RegisterUsers extends JFrame implements MouseListener {

    ArrayList<Customer> allUsers = new ArrayList<>();

    JTextField accountText = new JTextField();
    JTextField passwordText = new JTextField();
    JTextField nameText = new JTextField();
    JTextField balanceText = new JTextField();
    JTextField phoneText = new JTextField();

    JLabel accText = new JLabel("请输入初始账户：");
    JLabel passText = new JLabel("请输入账户初始密码：");
    JLabel naText = new JLabel("请输入账户持有人姓名：");
    JLabel balText = new JLabel("请输入账户初始余额：");
    JLabel phoText = new JLabel("请输入账户持有人电话：");

    JButton confirm = new JButton("提交");
    JButton clear = new JButton("清除");

    public RegisterUsers() {

        initFrame();

        initView();

        //调用Util方法读取用户信息
        CustomerInfoUtil.readInfo();

        this.setVisible(true);

    }

    private void initView() {

        confirm.setContentAreaFilled(false);
        clear.setContentAreaFilled(false);

        //初始化提示标签：
        accText.setBounds(200, 100, 150, 30);
        passText.setBounds(200, 150, 150, 30);
        naText.setBounds(200, 200, 150, 30);
        balText.setBounds(200, 250, 150, 30);
        phoText.setBounds(200, 300, 150, 30);

        //初始化文本框：
        accountText.setBounds(400, 100, 200, 30);
        passwordText.setBounds(400, 150, 200, 30);
        nameText.setBounds(400, 200, 200, 30);
        balanceText.setBounds(400, 250, 200, 30);
        phoneText.setBounds(400, 300, 200, 30);

        //初始化按钮
        confirm.setBounds(300, 350, 60, 30);
        clear.setBounds(450, 350, 60, 30);

        this.getContentPane().add(accText);
        this.getContentPane().add(passText);
        this.getContentPane().add(naText);
        this.getContentPane().add(balText);
        this.getContentPane().add(phoText);

        this.getContentPane().add(accountText);
        this.getContentPane().add(passwordText);
        this.getContentPane().add(nameText);
        this.getContentPane().add(balanceText);
        this.getContentPane().add(phoneText);

        this.getContentPane().add(confirm);
        this.getContentPane().add(clear);

        confirm.addMouseListener(this);
        clear.addMouseListener(this);

        //添加背景图片
        JLabel background = null;
        try {
            background = new JLabel(new ImageIcon(ImageIO.read(new File("image/RegisterBack.jpg"))));
        } catch (IOException e) {
            System.out.println("管理员端注册界面背景图片未加载出来");
        }
        background.setBounds(0, 0, 800, 470);
        this.getContentPane().add(background);
    }

    public void initFrame() {

        this.setResizable(false);//禁用最大化窗口
        this.setSize(800, 500);//设置宽高
        this.setTitle("中矿银行ATM机系统客户注册页面");//设置标题
        this.setLocationRelativeTo(null);//居中
        this.setAlwaysOnTop(true);//置顶
        this.setLayout(null);//取消内部默认布局
        this.setDefaultCloseOperation(3);//关闭窗口后自动退出程序

    }


    public void cleanText() {
        accountText.setText("");
        passwordText.setText("");
        nameText.setText("");
        balanceText.setText("");
        phoneText.setText("");
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object source = e.getSource();
        if (source == confirm) {
            try {
                Customer newCustomer = new Customer(accountText.getText(), passwordText.getText(), nameText.getText(),
                        new BigDecimal("" + Double.parseDouble(balanceText.getText())), phoneText.getText());
                //新用户写入文件-去重操作
                for (Customer eachUser : CustomerInfoUtil.allUsers) {
                    if (eachUser.getAccount().equals(newCustomer.getAccount())) {
                        CustomerInfoUtil.allUsers.remove(eachUser);
                    }
                }
                CustomerInfoUtil.normalUsers.add(newCustomer);
                CustomerInfoUtil.writeInfo();
                cleanText();
            } catch (NumberFormatException k) {
                k.printStackTrace();
                System.out.println("source==RegisterUsers 空数据异常已捕获，请继续使用!");
            }
        } else if (source == clear) {
            cleanText();
        }
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
