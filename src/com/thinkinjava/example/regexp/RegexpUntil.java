package com.thinkinjava.example.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexpUntil {
    /**
     * 检测输入内容是否满足正则表达式验证
     *
     * @param input
     * @param regexp
     * @return
     */
    private static boolean validateX(String input, String regexp) {
        if (null != input && input.trim().length() > 0 && null != regexp && regexp.trim().length() > 0) {
            Pattern p = Pattern.compile(regexp);
            Matcher m = p.matcher(input);
            return m.matches();
        }
        return false;
    }

    /**
     * 验证变量名
     * 默认最大长度为32字符
     *
     * @param input
     * @return
     */
    public static boolean valiVariableName(String input) {
        String regexp = "^[a-zA-Z_$][a-zA-Z0-9_$]{31}$";
        return validateX(input, regexp);
    }

    /**
     * 校验美元$3.234
     *
     * @param input
     * @return
     */
    public static boolean valiDollar(String input) {
        String regexp = "^[0-9]+(\\.[0-9]+)?$";
        return validateX(input, regexp);
    }

    /**
     * 校验html标签
     *
     * @param input
     * @return
     */
    public static boolean valiHtmlTag(String input) {
        String regexp = "^<([a-z]*)>([^<>]*<\\/\\1>)?$";
        return validateX(input, regexp);
    }

    /**
     * 校验时间： 09:34 pm
     * 4:59 am
     * 23:59 pm
     *
     * @param input
     * @return
     */
    public static boolean valiDateTime(String input) {
        String regexp = "^(([01]?[0-9])|2[0-4]):[0-5]?[0-9]\\s+[ap]m$";
        return validateX(input, regexp);
    }

    /**
     * 校验url
     *
     * @param input
     * @return
     */
    public static boolean valiURL(String input) {
        String regexp = "^([a-z]{4,8}:\\/\\/)?([a-z0-9]+\\/)*[a-z0-9]+(\\.[a-z0-9]+)+$";
        return validateX(input, regexp);
    }

    /**
     * 校验邮件地址
     *
     * @param input
     * @return
     */
    public static boolean valiMail(String input) {
        String regexp = "^[a-z0-9]+@[a-z]+\\.[a-z]+$";
        return validateX(input, regexp);
    }

    /**
     * 测试
     *
     * @param args
     */
    public static void main(String[] args) {
        String mail = "dsfadfdfdsafads.com";
        System.out.println(valiMail(mail));
    }

}
