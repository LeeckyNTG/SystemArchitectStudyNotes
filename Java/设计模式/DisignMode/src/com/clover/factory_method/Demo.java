package com.clover.factory_method;

public class Demo {

    public static void main(String[] args) {
        //先给我来个灯泡
        ICreator creator = new BulbCreator();
        ILight light = creator.CreateLight();
        light.TurnOn();
        light.TurnOff();

        //再来个灯管看看
        creator = new TubeCreator();
        light = creator.CreateLight();
        light.TurnOn();
        light.TurnOff();

    }
}
