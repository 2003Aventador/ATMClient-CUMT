package com.aventador.util;

import cn.hutool.core.io.FileUtil;
import com.aventador.domain.CardSlot;
import com.aventador.domain.Customer;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class CustomerInfoUtil {

    public static ArrayList<Customer> allUsers = new ArrayList<>();

    public static ArrayList<Customer> normalUsers = new ArrayList<>();

    public static ArrayList<Customer> frozenUsers = new ArrayList<>();

    //输入密码失败次数
    public static int passwordFailed = 0;

    //当前操作账户
    public static Customer user;

    //当前用户可取款金额
    //为确保银行正常运行，用户每日可取金额不大于（五万 || 用户余额的百分之20）
    public static BigDecimal customerTodayAvailableCash = new BigDecimal("0");

    //转入对象账户
    public static Customer transferCustomer;

    //转账金额
    public static BigDecimal transferAmount;

    private final static String[] path = {
            "C:\\Users\\lenovo\\Desktop\\Code\\JavaWeb\\ATMSystem\\UserInfo\\normalUserInfo.txt",
            "C:\\Users\\lenovo\\Desktop\\Code\\JavaWeb\\ATMSystem\\UserInfo\\frozenUserInfo.txt"
    };

    public static void readInfo() {

        allUsers.clear();
        normalUsers.clear();
        frozenUsers.clear();
        for (int i = 0; i < 2; i++) {
            ArrayList<Customer> allCustomers = new ArrayList<>();
            List<String> userStrList = FileUtil.readUtf8Lines(path[i]);
            for (String s : userStrList) {
                String[] arr = s.split("&");
                String account = arr[0].split("=")[1];
                String password = arr[1].split("=")[1];
                String name = arr[2].split("=")[1];
                BigDecimal balance = new BigDecimal("" + Double.parseDouble(arr[3].split("=")[1]));
                String phone = arr[4].split("=")[1];
                Customer customer = new Customer(account, password, name, balance, phone);
                allCustomers.add(customer);
            }

            System.out.println("source==CustomerInfoUtil 此时读取文件路径：" + path[i]);
            if (i == 0) {
                normalUsers = allCustomers;
            } else {
                frozenUsers = allCustomers;
            }
            allUsers.addAll(allCustomers);

        }

        TransactionDetails.readInfo();
        System.out.println("source=CustomerInfoUtil -------------------------------------");
        TransactionDetails.allDetails.forEach(System.out::println);
        System.out.println("source=CustomerInfoUtil -------------------------------------");
        allUsers.forEach(System.out::println);
        System.out.println("source=CustomerInfoUtil -------------------------------------");
        normalUsers.forEach(System.out::println);
        System.out.println("source=CustomerInfoUtil -------------------------------------");
        frozenUsers.forEach(System.out::println);

    }

    public static void writeInfo() {
        System.out.println("========================================================");
        System.out.println("source==CustomerInfoUtil 用户信息发生更改，重新写入");
        normalUsers.forEach(System.out::println);
        frozenUsers.forEach(System.out::println);
        System.out.println("========================================================");

        FileUtil.writeLines(normalUsers, path[0], "UTF-8");
        FileUtil.writeLines(frozenUsers, path[1], "UTF-8");
    }

    public static Customer getCus(String account) {
        for (Customer eachUser : allUsers) {
            if (eachUser.getAccount().equals(account)) {
                return eachUser;
            }
        }
        return null;
    }

    public static void freezeUser() {

        //在用户名单中删除用户
        normalUsers.remove(user);
        //将用户写入冻结名单
        frozenUsers.add(user);
        writeInfo();

    }

    public static boolean isFrozen(String account) {

        for (Customer frozenUser : frozenUsers) {
            if (frozenUser.getAccount().equals(account)) {
                return true;
            }
        }
        return false;

    }

    public static void makeTestInfo() {

        //生成虚假用户
        user = new Customer("123456789", "123456",
                "nobody", new BigDecimal("521000.1314"), "13137447066");

        //取款机今日可用余额生成
        Random r = new Random();
        CardSlot.totalCash = new BigDecimal("" + r.nextDouble(80000) + 10000);
        System.out.println("source=CustomerInfoUtil ATM机中今日可取的金额数：" + CardSlot.totalCash);

        //用户今日可取款金额生成
        customerTodayAvailableCash = new BigDecimal("" + Math.min(user.getBalance().multiply(new BigDecimal("0.2")).doubleValue(), 50000));
    }


}
