package org.example.imooc_oa.Utils;

import org.apache.commons.codec.digest.DigestUtils;

public class Md5Utils {
    public static String md5Digest(String input) {
        return DigestUtils.md5Hex(input);
    }
    public static String md5Digest(String input, Integer salt) {
        char[] chars = input.toCharArray();
        for (int i = 0; i < input.length(); i++) {
            chars[i] = (char) (chars[i] + salt);
        }
        String md5 = new String(chars);
        return DigestUtils.md5Hex(md5);
    }

}
