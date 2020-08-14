package com.clover.simple_factory;

public class PasswordLogin implements Login {

    @Override
    public boolean verify(String name, String password) {
        // TODO Auto-generated method stub
        /**
         * 业务逻辑
         */
        System.out.println("PasswordLogin");
        return true;
    }
}