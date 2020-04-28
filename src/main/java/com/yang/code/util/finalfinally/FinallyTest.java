package com.yang.code.util.finalfinally;

/**
 * Created by yangguojun01 on 2020/4/28.
 */
public class FinallyTest {

    public static void main(String[] args) {
        try {
            // do something
            System.out.println("aaa");
            System.exit(1);
            System.out.println("bbb");
        } finally {
            System.out.println("ccc");
        }
    }

}
