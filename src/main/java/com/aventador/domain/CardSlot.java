package com.aventador.domain;

import com.aventador.util.GetCash;
import com.aventador.util.PutCash;

import java.math.BigDecimal;

public final class CardSlot {

    //卡槽中刚被放入/刚被吐出的金额数
    public static BigDecimal cashAmountJustNow;

    //ATM今日可取的总金额数
    public static BigDecimal totalCash;

    public CardSlot() {

    }

    public static void putMoney() {
        new PutCash();
    }

    public static void getMoney() {
        new GetCash();
    }

}
