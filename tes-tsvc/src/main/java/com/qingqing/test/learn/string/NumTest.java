package com.qingqing.test.learn.string;

/**
 * Created by zhujianxing on 2020/11/6.
 */
public class NumTest {

    public static void main(String[] args) {
        System.out.println(999 * 999);
        System.out.println(new NumTest().mult("123", "456"));
    }

    private String mult(String num1, String num2){
        int[] result = new int[num1.length() + num2.length()];

        int len1 = num1.length();
        int len2 = num2.length();
        for(int i = len2 - 1; i >= 0; i--){
            int numI = Integer.valueOf(String.valueOf(num2.charAt(i)));
            if(numI != 0){
                for(int j = len1 - 1; j >= 0; j--){
                    int numJ = Integer.valueOf(String.valueOf(num1.charAt(j)));
                    int tmpResult = numI * numJ;
                    int x = tmpResult % 10;
                    int y = tmpResult / 10;
                    int xIdx = i + j + 1;
                    add(result, xIdx, x);
                    add(result, xIdx - 1, y);
                }
            }
        }

        StringBuilder sb = null;
        for (int r :result){
            if(sb == null){
                if(r != 0){
                    sb = new StringBuilder();
                    sb.append(r);
                }
            }else{
                sb.append(r);
            }
        }

        return sb == null?  "0" : sb.toString();
    }

    private void add(int[] result, int idx, int value){
        result[idx] = result[idx] + value;
        if(result[idx] >= 10){
            result[idx] = result[idx] % 10;
            add(result, idx - 1, 1);
        }
    }
}
