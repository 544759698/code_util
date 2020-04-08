package com.yang.code.util.java8.stream;

import java.util.stream.LongStream;

/**
 * Created by yangguojun01 on 2020/4/8.
 */
public class Sum100w {

    public static void main(String[] args) {
        //Java 8 并行流的实现
        long l = System.currentTimeMillis();
        long reduce = LongStream.rangeClosed(0, 10000000L).parallel().reduce(0, Long::sum);
        long l1 = System.currentTimeMillis();
        System.out.println("invoke = " + reduce+"  time: " + (l1-l));
    }

}
