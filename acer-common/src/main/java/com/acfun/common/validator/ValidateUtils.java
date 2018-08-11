/*
 * Copyright (c) 2017. www.servingcloud.com Inc. All rights reserved.
 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */

package com.acfun.common.validator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.net.IDN;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.CASE_INSENSITIVE;


/**
 * 校验工具类
 *
 * @author hsky www.servingcloud.com
 * @since 2017年6月4日 下午5:41:51
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ValidateUtils {

    /**
     * 图片文件类型 png,jpg
     */
    public static final String[] IMG_FILE_TYPE = {".png", ".jpg"};
    public static final Pattern ipPattern = Pattern.compile("\\d{1,3}(\\.\\d{1,3}){3}");
    public static final Pattern usernamePattern = Pattern.compile("[a-zA-Z0-9]{4,20}?$");
    public static final Pattern passwordPattern = Pattern.compile("(?![0-9]+$)(?![a-zA-Z]+$)(?!(![0-9A-Za-z])+$)\\S{6,32}");
    public static final Pattern mobilePattern = Pattern.compile("1\\d{10}");
    public static final Pattern codePattern = Pattern.compile("(?!_)\\w{1,64}");

    /**
     * 校验图片是否符合规则,通过返回true，不通过返回false
     *
     * @param fileName 图片名称
     * @return 通过返回true，不通过返回false
     */
    public static boolean validateImageFile(String fileName) {
        for (String string : IMG_FILE_TYPE) {
            if (fileName.endsWith(string)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 校验图片
     *
     * @param fileName    图片名称
     * @param inputStream 图片文件流
     * @return 通过返回true，不通过返回false
     */
    public static boolean validateImageFile(String fileName, InputStream inputStream) throws Exception{
        if (!validateImageFile(fileName)) {
            return false;
        }

        BufferedImage bufReader;
//        try {
          bufReader = ImageIO.read(inputStream);
//        } catch (IOException e) {
//            throw new Exception(e);
////            throw new CoreException("Failed to read input stream", e);
//        }

        int width = bufReader.getWidth();
        int height = bufReader.getHeight();
        return !(width == 0 || height == 0);
    }

    /**
     * 校验IP
     *
     * @param ip IP地址
     * @return 通过返回true，不通过返回false
     */
    public static boolean validateIp(String ip) {
        return ipPattern.matcher(ip).matches();
    }

    /**
     * 校验用户名
     *
     * @param username 用户名
     * @return 通过返回true，不通过返回false
     */
    public static boolean validateUsername(String username) {
        return usernamePattern.matcher(username).matches();
    }

    /**
     * 校验密码
     *
     * @param password 密码
     * @return 通过返回true，不通过返回false
     */
    public static boolean validatePassword(String password) {
        return passwordPattern.matcher(password).matches();
    }

    /**
     * 校验手机号码
     *
     * @param mobile 手机号码
     * @return 通过返回true，不通过返回false
     */
    public static boolean validateMobile(String mobile) {
        return mobilePattern.matcher(mobile).matches();
    }

    /**
     * 校验编号
     *
     * @param code 编号
     * @return 通过返回true，不通过返回false
     */
    public static boolean validateCode(String code) {
        return codePattern.matcher(code).matches();
    }

    private static final String ATOM = "[a-z0-9!#$%&'*+/=?^_`{|}~-]";
    private static final String DOMAIN = ATOM + "+(\\." + ATOM + "+)*";
    private static final String IP_DOMAIN = "\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\]";
    private static final int MAX_LOCAL_PART_LENGTH = 64;
    private static final int MAX_DOMAIN_PART_LENGTH = 255;

    /**
     * Regular expression for the local part of an email address (everything before '@')
     */
    private static final Pattern localPattern = Pattern.compile(
            ATOM + "+(\\." + ATOM + "+)*", CASE_INSENSITIVE
    );

    /**
     * Regular expression for the domain part of an email address (everything after '@')
     */
    private static final Pattern domainPattern = Pattern.compile(
            DOMAIN + "|" + IP_DOMAIN, CASE_INSENSITIVE
    );

    private static String toAscii(String unicodeString) throws IllegalArgumentException {
        String asciiString = "";
        int start = 0;
        int end = unicodeString.length() <= 63 ? unicodeString.length() : 63;
        while (true) {
            asciiString += IDN.toASCII(unicodeString.substring(start, end));
            if (end == unicodeString.length()) {
                break;
            }
            start = end;
            end = start + 63 > unicodeString.length() ? unicodeString.length() : start + 63;
        }
        return asciiString;
    }

    private static boolean matchPart(String part, Pattern pattern, int maxLength) {
        String asciiString;
        try {
            asciiString = toAscii(part);
        } catch (IllegalArgumentException e) {
            return false;
        }
        if (asciiString.length() > maxLength) {
            return false;
        }
        Matcher matcher = pattern.matcher(asciiString);
        return matcher.matches();
    }

    /**
     * 验证邮箱
     *
     * @param value 邮箱
     * @return 验证结果
     */
    public static boolean validateEmail(String value) {
        String[] emailParts = value.toString().split("@", 3);
        if (emailParts.length != 2) {
            return false;
        }
        if (emailParts[0].endsWith(".") || emailParts[1].endsWith(".")) {
            return false;
        }
        if (!matchPart(emailParts[0], localPattern, MAX_LOCAL_PART_LENGTH)) {
            return false;
        }
        return matchPart(emailParts[1], domainPattern, MAX_DOMAIN_PART_LENGTH);
    }

    public static final Pattern openIdPattern = Pattern.compile("(?!_)(\\w|-){1,64}");

    /**
     * 验证open id
     *
     * @param value open id
     * @return 验证结果
     */
    public static boolean validateOpenId(String value) {
        return openIdPattern.matcher(value).matches();
    }

}
