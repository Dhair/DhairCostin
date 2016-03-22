package com.dhair.datastructs.algorithm.container;

import java.math.BigInteger;

/**
 * Creator: dengshengjin on 16/2/26 17:31
 * Email: deng.shengjin@zuimeia.com
 */
public class ObjectReference {
    String str = new String("abc");
    char[] ch = {'a', 'b', 'c'};
    int[] intArr = {1, 2, 3};

    public void exchange(String str, char[] ch, int[] intArr) {
        str = "gbc";
        ch[0] = 'g';
        intArr[0] = 4;
    }

//    public static void main(String[] args) {
//        ObjectReference objectReference = new ObjectReference();
//        objectReference.exchange(objectReference.str, objectReference.ch, objectReference.intArr);
//        System.out.print(objectReference.str + "  and  ");
//        System.out.print(objectReference.ch);
//        System.out.print(objectReference.intArr);
//    }

    public static void main(String[] args) {
//        StringBuffer sb = new StringBuffer("Hello ");
//        System.out.println("Before change, sb = " + sb);
//        changeData(sb);
//        System.out.println("After changeData(n), sb = " + sb);
//
//        Scanner cin=new Scanner(System.in);
//        int i;
//        BigInteger t1,t2;
//        while(cin.hasNext())
//        {
//            t1=cin.nextBigInteger();
//            t2=cin.nextBigInteger();
//            t1=t1.add(t2);
//            System.out.println(t1);
//        }

        BigInteger integer=new BigInteger("121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212121212");
        System.out.println(integer);
    }
    public static void changeData(StringBuffer strBuf) {
        StringBuffer sb2 = new StringBuffer("Hi ");
        strBuf = sb2;
        sb2.append("World!");
    }
}
