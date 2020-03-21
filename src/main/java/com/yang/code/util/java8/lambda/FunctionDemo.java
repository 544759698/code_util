package com.yang.code.util.java8.lambda;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * https://time.geekbang.org/column/article/212374
 * <p>
 * Created by yangguojun01 on 2020/3/21.
 */
public class FunctionDemo {

    public static void main(String[] args) {
        // Predicate接口是输入一个参数，返回布尔值，eg:判断是否为大于0的偶数
        Predicate<Integer> positiveNumber = i -> i > 0;
        Predicate<Integer> evenNumber = i -> i % 2 == 0;
        System.out.println(positiveNumber.and(evenNumber).test(2));

        // Consumer接口是消费一个数据
        Consumer<String> println = System.out::println;
        println.andThen(println).accept("abcdefg");

        // Function接口输入一个数据，计算后输出一个数据
        Function<String, String> upperCase = String::toUpperCase;
        Function<String, String> duplicate = s -> s.concat(s);
        println.accept(upperCase.andThen(duplicate).apply("abcdEFG"));

        // Supplier是输出一个数据的接口
        Supplier<Integer> random = () -> ThreadLocalRandom.current().nextInt();
        System.out.println(random.get());

        // BinaryOperator是输入两个同类型参数返回一个同类型参数
        BinaryOperator<Integer> add = Integer::sum;
        BinaryOperator<Integer> sub = (a, b) -> a - b;
        System.out.println(sub.apply(add.apply(1, 2), add.apply(0, 1)));
    }

}
