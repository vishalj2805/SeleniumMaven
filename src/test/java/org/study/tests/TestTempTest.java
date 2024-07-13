package org.study.tests;

import io.cucumber.java.hu.Ha;

import java.util.*;

public class TestTempTest {

    public static void main(String[] args) throws InterruptedException {
        String s = "Vishal Jadhav";
        String[] str = s.split(" ");
        String word = str[str.length-1];
        System.out.println(word.length());
    }
}
