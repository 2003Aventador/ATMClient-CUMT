package com.aventador.domain;

import com.aventador.util.CustomerInfoUtil;
import com.aventador.util.TransactionDetails;

import java.util.HashMap;

public class Customer {

    private final String account;//账号
    private String password;//账户密码
    private final String name;//账户持有人
    private double balance;//账户余额
    private final String phoneNumber;//联系方式
    //交易记录

    public Customer(String account, String password, String name, double balance, String phoneNumber) {
        this.account = account;
        this.password = password;
        this.name = name;
        this.balance = balance;
        this.phoneNumber = phoneNumber;
    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public double getBalance() {
        return balance;
    }

    public String getPhone() {
        return phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setBalance(double newBalance) {
        this.balance = newBalance;
        //账户余额发生变更，立即写入文件
        //账户为12346798时为测试时生成的虚假数据
        if (!CustomerInfoUtil.user.getAccount().equals("123456789")) {
            CustomerInfoUtil.writeInfo();
        }
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
        if (!CustomerInfoUtil.user.getAccount().equals("123456789")) {
            CustomerInfoUtil.writeInfo();
        }
    }

    public static void transferAmount(double amount) {
        CustomerInfoUtil.user.balance -= amount;
        CustomerInfoUtil.transferCustomer.balance += amount;
        CustomerInfoUtil.writeInfo();
        TransactionDetails.writeInfo(3, 0);
    }

    @Override
    public String toString() {
        return "account=" + this.account + "&password=" + this.password + "&name=" + this.name +
                "&balance=" + this.balance + "&phoneNumber=" + this.phoneNumber;
    }

}
