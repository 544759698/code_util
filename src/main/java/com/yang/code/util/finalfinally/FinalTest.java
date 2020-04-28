package com.yang.code.util.finalfinally;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yangguojun01 on 2020/4/28.
 */
public class FinalTest {

    public static void main(String[] args) {
        // strList的引用不可变，但对象自身内容可变
        final List<String> strList = new ArrayList<>();
        strList.add("Hello");
        strList.add("World");
        // Immutable 不可变的
        List<String> unmodifiableList = Arrays.asList("Hello", "World");
        unmodifiableList.add("Again");
    }

}
