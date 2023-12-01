package com.aventador.clientUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class CardReturned extends JFrame {

    public CardReturned() {

        initJFrame();

        initView();

        this.setVisible(true);

        //不能直接使用Thread.Sleep方法，此方法会使整个线程睡眠3秒导致组件无法加载
        // Use a Timer to delay the exit by 3 seconds
        Timer timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        timer.setRepeats(false); // Set to false to only execute once
        timer.start();

    }

    private void initView() {

        JLabel success = new JLabel("退卡成功，请取走您的卡");
        success.setBounds(240, 180, 400, 30);
        Font sucTextFont = new Font("楷体", Font.BOLD, 30);//设置字体、字号
        // 粗体样式常量
        success.setFont(sucTextFont);
        this.getContentPane().add(success);

        //背景图片
        try {
            JLabel background = new JLabel(new ImageIcon(ImageIO.read(new File("image/background.jpg"))));
            background.setBounds(0, 0, 800, 500);
            this.getContentPane().add(background);
        } catch (IOException e) {
            System.out.println("退卡成功界面背景图片没有被加载出来");
        }

        /*
        使用这段代码不能加载图片：
        try {
            JLabel background = new JLabel(new ImageIcon(ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResource("image/background.jpg")))));
            background.setBounds(0, 0, 800, 500);
            this.getContentPane().add(background);
        } catch (NullPointerException e) {
            System.out.println("退卡界面背景图片没有加载成功");
        } catch (IOException e) {
            System.out.println("退卡界面没有加载成功");
        }

        两端代码之间有什么不同，为什么一个能加载图片而另一个不能：
        Resource Location:
            When you use getClass().getClassLoader().getResource("image/background.jpg"),
            it's crucial to have the image directory in the root of your classpath.
            The classpath is where Java looks for resources.
            If the directory structure is not set up correctly, the resource may not be found.

            Class Loader Differences:
            The class loader used by getClass().getClassLoader().getResource(...) might behave differently
            depending on the runtime environment or project setup.
            It's possible that in some scenarios, the class loader is not able to locate the resource properly.

            Execution Environment:
            The behavior might be influenced by the environment in which the program is executed.
            For example, if the program is run from an integrated development environment (IDE) or a command line,
            the working directory might vary, affecting how resources are resolved.

            Print Debugging:
            To investigate further, you can add print statements to see the actual URLs obtained by the two approaches.
            This can help identify any differences in the paths.
            Test code:
                URL url = getClass().getClassLoader().getResource("image/background.jpg");
                System.out.println("URL from getResource: " + url);
            By printing the URL, you can check whether it points to the correct location.

            Resource Loading Context:
            The way resources are loaded can be influenced by the context in which the application is running.
            For example, if you're running the code from an executable JAR file,
            the behavior might differ from running it in an IDE.

            To summarize, the discrepancy could be related to differences in class
            loading behavior, resource locations, or execution environments.
            Adding print statements to output the URLs can provide valuable information for debugging.
            If the resource is found but still not loaded,
            it's also worth checking the content of the JAR file or classpath to ensure that the resource is packaged correctly.
         */


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
