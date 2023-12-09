package com.aventador.domain;

import com.aventador.util.GetCash;
import com.aventador.util.PutCash;

public final class CardSlot {

    //卡槽中刚被放入/刚被吐出的金额数
    public static double cashAmountJustNow;

    //ATM今日可取的总金额数
    public static double totalCash;

    public CardSlot() {

    }

    public static void putMoney() {
        new PutCash();
    }

    public static void getMoney() {
        new GetCash();
    }

}
