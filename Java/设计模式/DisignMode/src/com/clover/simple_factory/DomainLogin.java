package com.clover.simple_factory;

public class DomainLogin implements Login{
    @Override
    public boolean verify(String name, String password) {

        System.out.println("DomainLogin");

        return true;
    }
}
