package com.aventador.domain;

import com.aventador.util.CustomerInfoUtil;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public final class Transaction {

    private final String account;
    private final String name;
    private final String phone;
    private final String active;//主动交易账户
    private final String passive;//被动交易账户
    private final int option;//交易种类
    /*
        1.存款
        2.取款
        3.转账
     */
    private final BigDecimal amount;//交易金额
    private String localTime;//交易时间

    public Transaction(String account, String active, String passive, int option, BigDecimal amount, String localTime) {
        Customer cus = CustomerInfoUtil.getCus(account);
        assert cus != null;
        this.account = cus.getAccount();
        this.name = cus.getName();
        this.phone = cus.getPhone();
        this.active = active;
        this.passive = passive;
        this.option = option;
        this.amount = new BigDecimal("" + amount.doubleValue());
        if (localTime.equals("null")) {
            LocalDateTime now = LocalDateTime.now();
            //年份
            this.localTime = now.getYear() + "-";
            //月份
            String str = "" + now.getMonthValue();
            if (str.length() == 1) {
                str = "0" + str;
            }
            this.localTime += str + "-";
            //日数
            str = "" + now.getDayOfMonth();
            if (str.length() == 1) {
                str = "0" + str;
            }
            this.localTime += str + "-";
            //hour
            str = "" + now.getHour();
            if (str.length() == 1) {
                str = "0" + str;
            }
            this.localTime += str + "-";
            //minute
            str = "" + now.getMinute();
            if (str.length() == 1) {
                str = "0" + str;
            }
            this.localTime += str + "-";
            //second
            str = "" + now.getSecond();
            if (str.length() == 1) {
                str = "0" + str;
            }
            this.localTime += str;
        } else {
            this.localTime = localTime;
        }
    }

    public String toString() {
        System.out.println("source==Transaction 交易信息输出");
        return "Account:" + account + "&Name:" + name + "&phone:" + phone +
                "&option:" + option +
                "&Active account:" + active + "&Passive account:" + passive +
                "&transaction amount:" + amount.doubleValue() + "&transaction time:" + localTime;
    }

    public String getAccount() {
        return account;
    }

    public String getPassive() {
        return passive;
    }

    public int getOption() {
        return option;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getLocalTime() {
        return localTime;
    }
}
