package com.cisdi.transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: cxh
 * @Description:
 * @Date: 2022/8/10 13:35
 */
public class Test {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.stream().forEach(e->{

            System.out.println("11");
        });
        System.out.println(list);
    }
}
