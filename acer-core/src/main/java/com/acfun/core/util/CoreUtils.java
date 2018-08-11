/*
 * Copyright (c) 2017. www.servingcloud.com Inc. All rights reserved.
 * 注意：本内容仅限于内部传阅，禁止外泄以及用于其他的商业目的
 */

package com.acfun.core.util;

import com.baomidou.mybatisplus.plugins.Page;
import com.xiaoleilu.hutool.date.DateUtil;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 核心工具类
 *
 * @author hsky www.servingcloud.com
 * @since 2017年6月4日 下午5:41:51
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public final class CoreUtils {

    /**
     * 校验包含大写字母的正则表达式
     */
    private static final Pattern UPPERCASE_PATTERN = Pattern.compile("[A-Z]");

    private static final String DEFAULT_SALT = "!n~e#w$e%p^o&c*h~";
    private static final String DEFAULT_KEY = "wZRig84LUf4=";

    /**
     * 获取32位的UUID
     *
     * @return
     */
    public static String getUUID() {
        UUID object = UUID.randomUUID();
        String uuid = object.toString();
        uuid = uuid.replaceAll("-", "");
        return uuid;
    }

    /**
     * 获取字母数字的随机数
     *
     * @param length
     * @return
     */
    public static String getRandomNumberOfLettersAndNumbers(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 获取数字的随机数
     *
     * @param length
     * @return
     */
    public static String getRandomNumberOfNumbers(int length) {
        String str = "0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(10);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 驼峰命名转下划线
     *
     * @param humpName
     * @return
     */
    public static String humpNameToUnderline(String humpName) {
        Matcher matcher = UPPERCASE_PATTERN.matcher(humpName);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     *
     * @param page 分页对象
     * 对分页对象的字段进项驼峰转换
     */
    public static void pageToUnderline(Page page) {
        String orderByField = page.getOrderByField();
        if(org.apache.commons.lang3.StringUtils.isNotBlank(orderByField)){
            page.setOrderByField(humpNameToUnderline(orderByField));
        }
    }

    /**
     * 获取最大长度的字符串
     *
     * @param str    字符串
     * @param length 最大长度
     * @return 新字符串
     */
    public static String getMaxLengthString(String str, int length) {
        if (StringUtils.isEmpty(str)) {
            return "";
        }
        if (str.length() > length) {
            return str.substring(0, length - 3) + "...";
        } else {
            return str;
        }
    }

    /**
     * 获取字符串参数的参数集合
     *
     * @param param 参数
     * @return 参数集合
     */
    public static Map<String, String> getRawParam(String param) {
        String[] params = param.split("&");
        Map<String, String> map = new HashMap<>();
        for (String str : params) {
            String[] kv = str.split("=");
            if (kv.length >= 2) {
                String key = kv[0];
                String value = kv[1];
                map.put(key, value);
            }
        }
        return map;
    }

    /**
     * 根据配置文件获取HikariCP数据源
     *
     * @param relaxedPropertyResolver
     * @return
     */
    public static HikariDataSource getHikariDataSource(RelaxedPropertyResolver relaxedPropertyResolver) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setPoolName(relaxedPropertyResolver.getProperty("pool-name"));

        hikariConfig.setJdbcUrl(relaxedPropertyResolver.getProperty("url"));
        hikariConfig.setUsername(relaxedPropertyResolver.getProperty("username"));
        hikariConfig.setPassword(relaxedPropertyResolver.getProperty("password"));

        hikariConfig.addDataSourceProperty("cachePrepStmts",
                relaxedPropertyResolver.getProperty("cache-prep-stmts"));
        hikariConfig.addDataSourceProperty("prepStmtCacheSize",
                relaxedPropertyResolver.getProperty("prep-stmt-cache-size"));
        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit",
                relaxedPropertyResolver.getProperty("prep-stmt-cache-sql-limit"));

        hikariConfig.addDataSourceProperty("useServerPrepStmts", true);
        hikariConfig.addDataSourceProperty("useLocalSessionState", true);
        hikariConfig.addDataSourceProperty("useLocalTransactionState", true);
        hikariConfig.addDataSourceProperty("rewriteBatchedStatements", true);
        hikariConfig.addDataSourceProperty("cacheResultSetMetadata", true);
        hikariConfig.addDataSourceProperty("cacheServerConfiguration", true);
        hikariConfig.addDataSourceProperty("elideSetAutoCommits", true);
        hikariConfig.addDataSourceProperty("maintainTimeStats", false);

        hikariConfig.setMinimumIdle(relaxedPropertyResolver.getProperty("minimum-idle", Integer.class));
        hikariConfig
                .setMaximumPoolSize(relaxedPropertyResolver.getProperty("maximum-pool-size", Integer.class));
        hikariConfig.setIdleTimeout(relaxedPropertyResolver.getProperty("idle-timeout", Long.class));
        hikariConfig
                .setConnectionTimeout(relaxedPropertyResolver.getProperty("connection-timeout", Long.class));
        hikariConfig.setMaxLifetime(relaxedPropertyResolver.getProperty("max-lifetime", Long.class));
        return new HikariDataSource(hikariConfig);
    }


    /**
     * 加密供应商门户的密码。
     *
     * @param password 密码
     * @return 加密后的密码
     */
    public static String encryptSupplierUserPassword(String password) {
        return encryptSupplierUserPassword(encryptSupplierUserPassword(password, DEFAULT_KEY) + DEFAULT_SALT, DEFAULT_SALT);
    }

    /**
     * 用户密码加密,由于外包使用该算法，所以验证时也只能使用该算法
     */
    private static String encryptSupplierUserPassword(String text, String key) {
        if (org.apache.commons.lang3.StringUtils.isBlank(key)) {
            key = DEFAULT_KEY;
        }
        String _md5Text = text + key;
        MessageDigest _mdInst = null;
        try {
            _mdInst = MessageDigest.getInstance("MD5");
            _mdInst.reset();
            _mdInst.update(_md5Text.getBytes("UTF-8"));

            byte[] _byteArray = _mdInst.digest();
            StringBuffer md5StrBuff = new StringBuffer();
            for (int i = 0; i < _byteArray.length; i++) {
                if (Integer.toHexString(0xFF & _byteArray[i]).length() == 1) {
                    md5StrBuff.append("0")
                            .append(Integer.toHexString(0xFF & _byteArray[i]));
                } else {
                    md5StrBuff
                            .append(Integer.toHexString(0xFF & _byteArray[i]));
                }
            }
            _md5Text = md5StrBuff.toString();
        } catch (NoSuchAlgorithmException e) {
            log.error("加密异常:", e);
        } catch (Exception e) {
            log.error("加密异常:", e);
        }
        return _md5Text;
    }

    /**
     * 忽略null值
     *
     * @param val
     * @return
     */
    public static BigDecimal ignorgNull(BigDecimal val) {
        return val == null ? BigDecimal.ZERO : val;
    }

    public static BigDecimal ignorgNullAddByList(List<Object> list) {
        BigDecimal data = BigDecimal.ZERO;
        for (Object val : list) {
            if (val != null) {
                try {
                    data = data.add(new BigDecimal(val.toString()));
                } catch (Exception e) {
                }
            }
        }
        return data;
    }

    public static BigDecimal ignorgNullAdd(BigDecimal... val) {
        BigDecimal sum = BigDecimal.ZERO;
        for (BigDecimal obj : val) {
            sum = ignorgNull(sum).add(ignorgNull(obj));
        }
        return sum;
    }

    public static BigDecimal ignorgNullAdd(BigDecimal val1, BigDecimal val2) {
        if(val1==null){
            val1 = BigDecimal.ZERO;
        }
        if(val2==null){
            val2 = BigDecimal.ZERO;
        }
        return ignorgNull(val1).add(ignorgNull(val2));
    }

    public static BigDecimal ignorgNullSub(BigDecimal val1, BigDecimal val2) {
        if(val1==null){
            val1 = BigDecimal.ZERO;
        }
        if(val2==null){
            val2 = BigDecimal.ZERO;
        }
        return ignorgNull(val1).subtract(ignorgNull(val2));
    }

    public static int ignorgNullCompareTo(BigDecimal val1, BigDecimal val2) {
        return ignorgNull(val1).compareTo(ignorgNull(val2));
    }

    public static BigDecimal ignorgNullSub(String val1, String val2) {

        BigDecimal v1 = new BigDecimal(val1);
        BigDecimal v2 = new BigDecimal(val2);
        return ignorgNull(v1).subtract(ignorgNull(v2));
    }

    public static BigDecimal ignorgNullSub(BigDecimal subtraction, BigDecimal... mnuend) {
        for (BigDecimal obj : mnuend) {
            subtraction = ignorgNullSub(subtraction, obj);
        }
        return subtraction;
    }

    public static BigDecimal ignorgNullDivide(BigDecimal val1, BigDecimal val2) {
        double v1 = ignorgNull(val1).doubleValue();
        double v2 = ignorgNull(val2).doubleValue();
        if (v2 == 0) {
            return BigDecimal.ZERO;
        }
        BigDecimal bigDecimal = new BigDecimal(v1 / v2);
        return bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 相除
     *
     * @param val1
     * @param val2
     * @param num  保留几位小数
     * @return
     */
    public static BigDecimal ignorgNullDivide(BigDecimal val1, BigDecimal val2, int num) {
        double v1 = ignorgNull(val1).doubleValue();
        double v2 = ignorgNull(val2).doubleValue();
        if (v2 == 0) {
            return new BigDecimal(v1);
        }
        if (num < 0) {
            num = 2;
        }
        BigDecimal bigDecimal = new BigDecimal(v1 / v2);
        return bigDecimal.setScale(num, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal ignorgNullMultiply(BigDecimal val1, BigDecimal val2) {
        double v1 = ignorgNull(val1).doubleValue();
        double v2 = ignorgNull(val2).doubleValue();
        return new BigDecimal(v1 * v2).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     *
     * @param val1 值1
     * @param val2 值2
     * @param num 保留小数点位数
     * @return 乘积
     */
    public static BigDecimal ignorgNullMultiply(BigDecimal val1, BigDecimal val2,int num) {
        double v1 = ignorgNull(val1).doubleValue();
        double v2 = ignorgNull(val2).doubleValue();
        return new BigDecimal(v1 * v2).setScale(num, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal ignorgNullMultiply(BigDecimal... vals) {
        BigDecimal res = BigDecimal.ONE;
        for (BigDecimal val : vals) {
            res = CoreUtils.ignorgNullMultiply(res, val).setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        return res;
    }

    public static BigDecimal ignorgNullMin(BigDecimal val1, BigDecimal val2) {
        if (val1 == null || val2 == null) {
            return new BigDecimal(0);
        }
        return val1.min(val2);
    }



    /**
     * 小数点校验
     *
     * @param str 字符串型
     * @param num 几位小数
     * @return
     */
    public static boolean isNumber(String str, int num) {
        Pattern pattern = Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0," + num + "})?$"); // 判断小数点后2位的数字的正则表达式
        Matcher match = pattern.matcher(str);
        return match.matches();
    }


    /**
     *  yyyy-MM  转为 yyyy-MM-01 00:00:00
     *  该月第一天 具体到时间
     * @param month  月份
     * @return 日期
     */
    public static String getStartDate(String month) {
        if (StringUtils.isEmpty(month)) {
            return null;
        }
        return month + "-01 00:00:00";
    }

    /**
     *  yyyy-MM  转为 yyyy-MM-01 00:00:00
     *  该月 下个月的第一天 具体到时间
     * @param month  月份
     * @return 日期
     */
    public static String getEndDate(String month) {
        if (StringUtils.isEmpty(month)) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateUtil.parseDate(month + "-01"));
        calendar.add(Calendar.MONTH, 1);
        return DateUtil.formatDate(calendar.getTime()) + " 00:00:00";
    }

    /**
     *  yyyy-MM  转为 yyyy-MM-01
     *  该月第一天
     * @param month  月份
     * @return 日期
     */
    public static String getMonthStart(String month) {
        if (StringUtils.isEmpty(month)) {
            return null;
        }
        return month + "-01";
    }

    /**
     *  yyyy-MM  转为 yyyy-MM-01
     *  该月 下个月的第一天
     * @param month  月份
     * @return 日期
     */
    public static String getMonthEnd(String month) {
        if (StringUtils.isEmpty(month)) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateUtil.parseDate(month + "-01"));
        calendar.add(Calendar.MONTH, 1);
        return DateUtil.formatDate(calendar.getTime());
    }

    public static String getLastMonth(String month) {
        Date date = DateUtil.parse(month, "yyyy-MM");
        return DateUtil.format(DateUtil.offsetMonth(date, -1), "yyyy-MM");
    }

    public static String getNextYearMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        //增加一年
        cal.add(Calendar.YEAR, 1);
        return DateUtils.getMonth(cal.getTime());
    }



    /**
     * 取小
     */
    public static BigDecimal getMin(BigDecimal value1, BigDecimal value2) {
        if (value1 == null) {
            value1 = BigDecimal.ZERO;
        }
        if (value2 == null) {
            value2 = BigDecimal.ZERO;
        }
        return value1.compareTo(value2) > 0 ? value2 : value1;
    }

    /**
     * 取小---大于零
     */
    public static BigDecimal getPositiveMin(BigDecimal value1, BigDecimal value2) {
        if (value1 == null) {
            value1 = BigDecimal.ZERO;
        }
        if (value2 == null) {
            value2 = BigDecimal.ZERO;
        }
        if (value1.compareTo(BigDecimal.ZERO) > 0 && value2.compareTo(BigDecimal.ZERO) > 0) {
            return value1.compareTo(value2) > 0 ? value2 : value1;
        } else {
            return BigDecimal.ZERO;
        }
    }


    public static BigDecimal ignorgNullAdd(String... vals) {
        BigDecimal count = BigDecimal.ZERO;
        for (String obj : vals) {
            if (obj != null) {
                try {
                    BigDecimal tmp = new BigDecimal(obj);
                    count = count.add(tmp);
                } catch (Exception e) {
                }
            }
        }
        return count;
    }

    /**
     * 导出上限
     *
     * @return
     */
    public static Page getExportPage() {
        Page page = new Page();
        page.setSize(30000);
        return page;
    }

    public static boolean isBlank(String s) {
        if (s == null || s.trim().length() == 0 || "null".equalsIgnoreCase(s)) {
            return true;
        }
        return false;
    }

    public static boolean isNotBlank(String s) {
        return !isBlank(s);
    }

    /**
     * 转换日期存在/的问题 适用于2018/1/1或者2018/01/11
     * @param s
     * @return
     */
    public static String formatDateStr(String s) {
        if (isBlank(s)) {
            return "";
        }
        if (!s.contains("/")) {
            return s;
        }
        String tmp = s.substring(s.indexOf("/"), s.length());
        if (tmp.contains("/")) {
            String[] str = s.split("/");
            String year = str[0];
            String month = str[1].length() == 1 ? "0" + str[1] : str[1];
            String day = str[2].length() == 1 ? "0" + str[2] : str[2];
            return year + "-" + month + "-" + day;
        }
        return s;
    }

    /**
     *  返回一个 格式为%的字符串 税率
     * @param b 税率 * 100 的BigDecimal
     * @return 税率
     */
    public static String getTaxRate(BigDecimal b) {
        if(b==null){
            return null;
        }
        return b.toString()+"%";
    }
}
