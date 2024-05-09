package net.enjoy.springboot.registrationlogin.global;

import net.enjoy.springboot.registrationlogin.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class GlobalData {
    public static List<Product> cart;
    static {
        cart = new ArrayList<Product>();
    }
}