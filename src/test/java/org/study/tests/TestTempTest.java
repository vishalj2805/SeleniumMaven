package org.study.tests;

import io.cucumber.java.hu.Ha;

import java.util.*;

public class TestTempTest {

    public static void main(String[] args) throws InterruptedException {
        int[] num = {1,1,1,3,3,2,2,4,4,5,5,6,6,9,7,7,8,8};

        int unique = 0;

        for (int i=0;i<num.length;i++){
            int currentNum = num[i];
            int count= 0;
            for (int j=0;j<num.length;j++){
                if (num[i]==num[j]){
                    count++;
                }
            }
            if (count==1){
                unique=num[i];
            }
        }
        System.out.println(unique);
    }
}
