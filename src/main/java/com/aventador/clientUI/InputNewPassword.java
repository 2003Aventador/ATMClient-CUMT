package com.aventador.clientUI;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class InputNewPassword extends JFrame implements MouseListener {


    private final static JPasswordField password = new JPasswordField(6);

    private Font passwordFont;

    public static String newPassword;

    JLabel sinText = new JLabel("<HTML>请  输  入  新  密  码</HTML>", SwingConstants.CENTER);

    JButton seePassword = new JButton("查看");
    JButton confirm = new JButton("确认");
    JButton clear = new JButton("清除");

    public InputNewPassword() {

        initJFrame();

        initView();

        this.setVisible(true);

    }

    private void initView() {

        //中文提示  后续可以添加英文提示
        sinText.setSize(800, 250);
        Font sinTextFont = new Font("楷体", Font.BOLD, 30);//设置字体、字号
        // 粗体样式常量
        //welText.setForeground(new Color(111,222,0));//设置颜色
        sinText.setFont(sinTextFont);
        this.getContentPane().add(sinText);

        password.setSize(200, 30);
        password.setEchoChar('\u2605');
        passwordFont = password.getFont();

        inputPassword();

        //密码框底部居中
        int x = (this.getWidth() - password.getWidth()) / 2;
        int y = 200; // Adjust the Y coordinate as needed
        password.setBounds(x, y, 200, 30);
        this.getContentPane().add(password);

        //查看按钮
        seePassword.setBounds(x + 250, y + 5, 60, 20);
        //清除按钮边框
        //seePassword.setBorderPainted(false);
        //消除按钮背景
        seePassword.setContentAreaFilled(false);
        this.getContentPane().add(seePassword);
        seePassword.addMouseListener(this);

        //确认按钮
        confirm.setBounds(x + 10, y + 50, 60, 30);
        confirm.setContentAreaFilled(false);
        this.getContentPane().add(confirm);
        confirm.addMouseListener(this);

        //清除按钮
        clear.setBounds(x + 120, y + 50, 60, 30);
        clear.setContentAreaFilled(false);
        this.getContentPane().add(clear);
        clear.addMouseListener(this);

        seePassword.setContentAreaFilled(false);
        confirm.setContentAreaFilled(false);
        clear.setContentAreaFilled(false);

        //添加背景图片
        String path = "image/background.jpg";
        JLabel background = new JLabel(new ImageIcon(path));
        background.setBounds(0, 0, 800, 500);
        this.add(background, BorderLayout.CENTER);
        background.setLayout(new BorderLayout());

    }

    public static void inputPassword() {
        //limit--6 digits
        AbstractDocument document = (AbstractDocument) password.getDocument();
        document.setDocumentFilter(new DocumentFilter() {
            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                    throws BadLocationException {
                // Allow only six characters
                if ((fb.getDocument().getLength() + text.length() - length) > 6) {
                    return;
                }

                // Allow only Arabic numerals
                if (text.matches("\\d*")) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });
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

    @Override
    public void mouseClicked(MouseEvent e) {
        Object source = e.getSource();
        if (source == confirm) {
            String s = String.valueOf(password.getPassword());
            newPassword = s;
            password.setText("");
            dispose();
            new InputNewPasswordConfirmed();
        } else if (source == clear) {
            password.setText("");
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
        Object source = e.getSource();
        if (source == seePassword) {
            password.setEchoChar((char) 0);
            //set font to match the button font
            /*
            When you set the font of the JPasswordField to match the font of the button,
            it ensures that the characters and the asterisks are displayed with the same font style and size.
            This is particularly relevant if the default fonts for these components are different,
            and you want a cohesive and uniform look
             */
            password.setFont(seePassword.getFont());
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Object source = e.getSource();
        if (source == seePassword) {
            password.setEchoChar('\u2605');
            password.setFont(passwordFont);
        }
    }
}
