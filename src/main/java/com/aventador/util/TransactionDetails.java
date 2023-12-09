package com.aventador.util;

import cn.hutool.core.io.FileUtil;
import com.aventador.domain.CardSlot;
import com.aventador.domain.Customer;
import com.aventador.domain.Transaction;

import java.util.*;

public final class TransactionDetails {

    //当前凭证对象
    public static Transaction actor;

    //记载所有未分类的交易记录
    public static ArrayList<Transaction> allDetails = new ArrayList<>();

    //记载每个用户已经分类的交易记录
    public static HashMap<String, ArrayList<Transaction>> allDetailsForEachCustomer = new HashMap<>();

    private final static String path = "C:\\Users\\lenovo\\Desktop\\Code\\JavaWeb\\ATMSystem\\userInfo\\transactionDetails.txt";


    //立即写入新交易信息
    public static void writeInfo(int option, double amount) {
        if (option == 1) {
            //存款
            actor = new Transaction(CustomerInfoUtil.user.getAccount(), CustomerInfoUtil.user.getAccount(),
                    CustomerInfoUtil.user.getAccount(), 1, CardSlot.cashAmountJustNow, "null");
        } else if (option == 2) {
            //取款
            actor = new Transaction(CustomerInfoUtil.user.getAccount(), CustomerInfoUtil.user.getAccount(),
                    CustomerInfoUtil.user.getAccount(), 2, amount, "null");
        } else if (option == 3) {
            //转账
            actor = new Transaction(CustomerInfoUtil.user.getAccount(), CustomerInfoUtil.user.getAccount(),
                    CustomerInfoUtil.transferCustomer.getAccount(), 3, CustomerInfoUtil.transferAmount, "null");
        } else {
            System.out.println("source==TransactionDetails 交易操作写入错误 报错原因：option数值异常");
        }

        allDetails.add(actor);
        FileUtil.writeLines(allDetails, path, "UTF-8");
        TransactionDetails.readInfo();
    }

    //读取所有交易信息
    public static void readInfo() {

        allDetails.clear();
        allDetailsForEachCustomer.clear();
        List<String> list = FileUtil.readUtf8Lines(path);
        for (String s : list) {
            String[] info = s.split("&");
            String account = info[0].split(":")[1];//交易账户信息
            String name = info[1].split(":")[1];//交易账户持有人姓名
            String phoneNumber = info[2].split(":")[1];//账户持有人联系方式
            int option = Integer.parseInt(info[3].split(":")[1]);//账户进行的操作
            String active = info[4].split(":")[1];//交易发起人账户
            String passive = info[5].split(":")[1];//被动交易账户
            double amount = Double.parseDouble(info[6].split(":")[1]);//交易金额
            String localTime = info[7].split(":")[1];//交易时间

            //找到账户为account的所有用户信息并加载到内存
            for (Customer eachUser : CustomerInfoUtil.allUsers) {
                if (eachUser.getAccount().equals(account)) {
                    allDetails.add(new Transaction(account, active, passive, option, amount, localTime));
                    break;
                }
            }
        }

        allDetails.sort(new Comparator<Transaction>() {
            @Override
            public int compare(Transaction o1, Transaction o2) {
                return o2.getLocalTime().compareTo(o1.getLocalTime());
            }
        });

        System.out.println("source==TransactionDetails80 ===========================================================");
        allDetails.forEach(System.out::println);
        System.out.println("source==TransactionDetails82 ===========================================================");

        //解析分类交易记录
        if (allDetails != null) {
            boolean flag = false;
            for (Transaction eachDetail : allDetails) {
                String account = eachDetail.getAccount();
                Set<Map.Entry<String, ArrayList<Transaction>>> entries = allDetailsForEachCustomer.entrySet();
                for (Map.Entry<String, ArrayList<Transaction>> entry : entries) {
                    if (entry.getKey().equals(account)) {
                        flag = true;
                        ArrayList<Transaction> transList = entry.getValue();
                        transList.add(eachDetail);
                        allDetailsForEachCustomer.put(account, transList);
                    }
                }
                if (!flag) {
                    ArrayList<Transaction> newList = new ArrayList<>();
                    newList.add(eachDetail);
                    allDetailsForEachCustomer.put(account, newList);
                }
                flag = false;
            }
        }


    }

    public static ArrayList<Transaction> getTransactionDetails() {

        ArrayList<Transaction> transList = new ArrayList<>();
        String account = CustomerInfoUtil.user.getAccount();
        Set<Map.Entry<String, ArrayList<Transaction>>> entries = allDetailsForEachCustomer.entrySet();
        for (Map.Entry<String, ArrayList<Transaction>> entry : entries) {
            if (entry.getKey().equals(account)) {
                transList = entry.getValue();
            }
        }
        transList.forEach(System.out::println);
        return transList;
    }


}
