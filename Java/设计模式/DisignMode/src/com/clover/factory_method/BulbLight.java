package com.clover.factory_method;

import java.io.Console;


/**
 * 具体的产品类
 */
public class BulbLight implements ILight {
    public void TurnOn() {
        System.out.println("BulbLight turns on.");
    }

    public void TurnOff() {
        System.out.println("BulbLight turns off.");
    }
}