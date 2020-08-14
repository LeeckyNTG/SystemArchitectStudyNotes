package com.clover.factory_method;

/**
 * 具体的产品类
 */
public class TubeLight implements ILight {
    public void TurnOn() {
        System.out.println("TubeLight turns on.");
    }

    public void TurnOff() {
        System.out.println("TubeLight turns off.");
    }

}