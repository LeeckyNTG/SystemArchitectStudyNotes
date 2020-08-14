package com.clover.factory_method;

/**
 * 具体的工厂类
 */
public class TubeCreator implements ICreator {
    public ILight CreateLight() {
        return new TubeLight();
    }
}