package com.yang.code.util.java8.stream;

import java.math.BigInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by yangguojun01 on 2020/3/30.
 */
public class StreamDemo {

    public static void main(String[] args) {
        //        Stream.of(1, 2, "a").map(item -> item.getClass().getName()).forEach(System.out::println);
        //        Stream.iterate(1, item -> item * 2).limit(10).forEach(System.out::println);
        Stream.iterate(BigInteger.ZERO, n -> n.add(BigInteger.TEN)).limit(3).forEach(System.out::println);
        IntStream.iterate(1, n -> n + 1).skip(5).limit(10).filter(n -> n % 2 != 0).forEach(System.out::println);
    }

}
