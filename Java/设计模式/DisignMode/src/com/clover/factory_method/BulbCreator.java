package com.clover.factory_method;

/**
 * 具体工厂类
 */
public class BulbCreator implements ICreator {
    public ILight CreateLight() {
        return new BulbLight();
    }
}