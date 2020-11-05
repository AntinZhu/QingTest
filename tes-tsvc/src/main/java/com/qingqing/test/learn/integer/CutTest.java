package com.qingqing.test.learn.integer;

import java.math.BigInteger;

/**
 * Created by zhujianxing on 2020/11/3.
 */
public class CutTest {

    public static void main(String[] args) {
        System.out.print(new CutTest().cuttingRope(3));
    }

    /*
    给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m-1] 。请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。

    示例 1：

    输入: 2
    输出: 1
    解释: 2 = 1 + 1, 1 × 1 = 1
    示例 2:

    输入: 10
    输出: 36
    解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36

    来源：力扣（LeetCode）
    链接：https://leetcode-cn.com/problems/jian-sheng-zi-lcof
    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public int cuttingRope(int n) {
        int[] f = new int[n + 1];
        f[0] = 0;
        f[1] = 1;

        for(int i = 2; i <= n; i++){
            int max = i == n? 0 : i; // 等于传入值时，必须剪一刀。否则可以不减
            for(int j = 1; j <= i/2; j++){
                max = Math.max(max, f[j] * f[i - j]);
            }
            f[i] = max;
        }

        return f[n];
    }

    public int cuttingRope2(int n) {
        BigInteger[] f = new BigInteger[n + 1];
        f[0] = BigInteger.valueOf(0);
        f[1] = BigInteger.valueOf(1);

        for(int i = 2; i <= n; i++){
            BigInteger max = BigInteger.valueOf(i == n? 0 : i); // 等于传入值时，必须剪一刀。否则可以不减
            for(int j = 1; j <= i/2; j++){
                max = max.max(f[j].multiply(f[i - j]));
            }
            f[i] = max;
        }

        return f[n].mod(BigInteger.valueOf(1000000007)).intValue();
    }
}
