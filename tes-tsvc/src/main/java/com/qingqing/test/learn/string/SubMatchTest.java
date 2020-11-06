package com.qingqing.test.learn.string;

/**
 * Created by zhujianxing on 2020/11/6.
 */
public class SubMatchTest {

    public boolean checkInclusion(String s1, String s2) {
        if(s1 == null || s2 == null){
            return false;
        }

        if(s2.length() < s1.length()){
            return false;
        }

        // 利用
        int[] nums1 = new int[26];
        int[] nums2 = new int[26];
        for(int i = 0; i < s1.length(); i++){
            nums1[s1.charAt(i) - 'a']++;
            nums2[s2.charAt(i) - 'a']++;
        }

        int matchStartIdx = s1.length() - 1;
        while(matchStartIdx < s2.length()){
            boolean matchResult = isMatch(nums1, nums2);
            if(matchResult){
                return true;
            }

            if(matchStartIdx < s2.length() - 1){
                nums2[s2.charAt(matchStartIdx - s1.length() + 1)  - 'a']--;
                nums2[s2.charAt(matchStartIdx + 1) - 'a'] ++;
            }

            matchStartIdx++;
        }

        return false;
    }

    private boolean isMatch(int[] nums1, int[] nums2){
        for(int i = 0 ; i < nums1.length; i++){
            if(nums1[i] != nums2[i]){
                return false;
            }
        }

        return true;
    }
}
