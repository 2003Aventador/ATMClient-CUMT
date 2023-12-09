package com.aventador.clientUI;

import com.aventador.domain.Customer;
import com.aventador.domain.Transaction;
import com.aventador.util.CustomerInfoUtil;
import com.aventador.util.TransactionDetails;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PrintVoucher extends JFrame {

    //交易信息
    ArrayList<Transaction> list = new ArrayList<>();

    JTextField f1 = new JTextField();
    JTextField f2 = new JTextField();
    JTextField f3 = new JTextField();
    JTextField f4 = new JTextField();
    JTextField f5 = new JTextField();
    JTextField f6 = new JTextField();
    JTextField f7 = new JTextField();


    //当前显示第count条交易信息
    //0 <= i <= 9
    static int i = 0;

    JButton jb1 = new JButton("返回");
    JButton jb2 = new JButton("打印下一条");

    public PrintVoucher() {

        initJFrame();

        initView();

        showInfo();

        addListener();

    }

    private void showInfo() {

        list = TransactionDetails.getTransactionDetails();
        showListInfo();
        if (list.size() == 0) {
            JOptionPane.showMessageDialog(this, "当前账户暂无交易记录");
            new MoreOption();
            dispose();
        }

        try {
            Transaction trans = list.get(i);
            String account = trans.getAccount();
            Customer cus = CustomerInfoUtil.getCus(account);
            f1.setText("" + account);
            f2.setText("" + cus.getName());
            f3.setText("" + cus.getPhone());
            f4.setText("" + trans.getPassive());
            int option = trans.getOption();
            if (option == 1) {
                f5.setText("存款");
            } else if (option == 2) {
                f5.setText("取款");
            } else if (option == 3) {
                f5.setText("转账");
            }
            f6.setText("" + trans.getAmount());
            String[] arr = trans.getLocalTime().split("-");
            f7.setText("" + arr[0] + "-" + arr[1] + "-" + arr[2] + "  " + arr[3] + ":" + arr[4] + ":" + arr[5]);
        } catch (NullPointerException k) {
            System.out.println("source==PrintVoucher 没有生成虚假数据导致null");
        } catch (IndexOutOfBoundsException k) {
            System.out.println("source==PrintVoucher 账号没有交易记录");
        }

    }

    private void addListener() {

        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MoreOption();
            }
        });


        jb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //更换下一条凭证
                showListInfo();
                i = (i + 1) % Math.min(10, list.size());
                Transaction trans = list.get(i);
                String account = trans.getAccount();
                Customer cus = CustomerInfoUtil.getCus(account);
                f1.setText("" + account);
                f2.setText("" + cus.getName());
                f3.setText("" + cus.getPhone());
                f4.setText("" + trans.getPassive());
                int option = trans.getOption();
                if (option == 1) {
                    f5.setText("存款");
                } else if (option == 2) {
                    f5.setText("取款");
                } else if (option == 3) {
                    f5.setText("转账");
                }
                f6.setText("" + trans.getAmount());
                String[] arr = trans.getLocalTime().split("-");
                f7.setText("" + arr[0] + "-" + arr[1] + "-" + arr[2] + "  " + arr[3] + ":" + arr[4] + ":" + arr[5]);
            }
        });
    }

    private void showListInfo() {

        System.out.println("++++++++++++++++++++++++++++++ source==PrintVoucher +++++++++++++++++++++++++++++++++++++");
        System.out.println("现在该账号共有" + list.size() + "条交易记录，List集合中记录的数据为：");
        list.forEach(System.out::println);
        System.out.println("++++++++++++++++++++++++++++++ source==PrintVoucher +++++++++++++++++++++++++++++++++++++");

    }

    private void initView() {

        //中文提示  后续可以添加英文提示
        JLabel welText = new JLabel("<HTML>请取走您的交易凭证</HTML>", SwingConstants.CENTER);
        welText.setSize(450, 100);
        Font welTextFont = new Font("楷体", Font.BOLD, 20);//设置字体、字号
        // 粗体样式常量
        //welText.setForeground(new Color(111,222,0));//设置颜色
        welText.setFont(welTextFont);

        // 创建小框架
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds((this.getWidth() - 480) / 2, 50, 480, 330);
        Color borderColor = Color.decode("#7A8A99");
        panel.setBorder(BorderFactory.createLineBorder(borderColor, 2));
        panel.setOpaque(false); // 使面板透明
        this.getContentPane().add(panel);
        panel.add(welText);

        // 添加第一个标签和文本框
        JLabel jLabel1 = new JLabel("交易发起者姓名:");
        jLabel1.setSize(80, 20);
        jLabel1.setBounds(50, 100, 150, 20);
        panel.add(jLabel1);

        f1.setBounds(220, 100, 150, 20);
        f1.setEditable(false);
        panel.add(f1);

        // 添加第二个标签和文本框
        JLabel jLabel2 = new JLabel("交易发起账户：");
        jLabel2.setSize(80, 20);
        jLabel2.setBounds(50, 130, 150, 20);
        panel.add(jLabel2);

        f2.setBounds(220, 130, 150, 20);
        f2.setEditable(false);
        panel.add(f2);

        // 添加第三个标签和文本框
        JLabel jLabel3 = new JLabel("交易发起者联系方式：");
        jLabel3.setSize(80, 20);
        jLabel3.setBounds(50, 160, 150, 20);
        panel.add(jLabel3);

        f3.setBounds(220, 160, 150, 20);
        f3.setEditable(false);
        panel.add(f3);

        // 添加第四个标签和文本框
        JLabel jLabel4 = new JLabel("被动交易账户：");
        jLabel4.setSize(80, 20);
        jLabel4.setBounds(50, 190, 150, 20);
        panel.add(jLabel4);

        f4.setBounds(220, 190, 150, 20);
        f4.setEditable(false);
        panel.add(f4);

        // 添加第五个标签和文本框
        JLabel jLabel5 = new JLabel("交易类型：");
        jLabel5.setSize(80, 20);
        jLabel5.setBounds(50, 220, 150, 20);
        panel.add(jLabel5);

        f5.setBounds(220, 220, 150, 20);
        f5.setEditable(false);
        panel.add(f5);

        // 添加第六个标签和文本框
        JLabel jLabel6 = new JLabel("金额变动情况：");
        jLabel6.setSize(80, 20);
        jLabel6.setBounds(50, 250, 150, 20);
        panel.add(jLabel6);

        f6.setBounds(220, 250, 150, 20);
        f6.setEditable(false);
        panel.add(f6);

        // 添加第七个标签和文本框
        JLabel jLabel7 = new JLabel("交易时间：");
        jLabel7.setSize(80, 20);
        jLabel7.setBounds(50, 280, 150, 20);
        panel.add(jLabel7);

        f7.setBounds(220, 280, 150, 20);
        f7.setEditable(false);
        panel.add(f7);

        int _x = this.getWidth() - 190;
        int y = 250;

        jb1.setBounds(40, y + 160, 150, 40);
        jb2.setBounds(_x, y + 160, 150, 40);

        jb1.setContentAreaFilled(false);
        jb2.setContentAreaFilled(false);

        this.getContentPane().add(jb1);
        this.getContentPane().add(jb2);


        //添加背景图片
        String path = "image/background.jpg";
        JLabel background = new JLabel(new ImageIcon(path));
        background.setBounds(0, 0, 800, 500);
        this.add(background, BorderLayout.CENTER);
        background.setLayout(new BorderLayout());

        this.setVisible(true);


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
