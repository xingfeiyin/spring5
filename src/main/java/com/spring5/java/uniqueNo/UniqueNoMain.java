package com.spring5.java.uniqueNo;

import org.apache.commons.lang3.StringUtils;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author yinxf
 * @Date 2020/9/23
 * @Description 唯一单号
 **/
public class UniqueNoMain {

    public static void main(String[] args) {

        method1();

        System.out.println("*************************************************");

        method2();

        method3();
    }

    private static void method3() {

    }

    private static void method2() {
        List<String> orderNos = Collections.synchronizedList(new ArrayList<>());
        IntStream.range(0,8000).parallel().forEach(i->{
            orderNos.add(generateOrderNo());
        });
        List<String> filterOrderNos = orderNos.stream().distinct().collect(Collectors.toList());

        System.out.println("生成订单数："+orderNos.size());
        System.out.println("过滤重复后订单数："+filterOrderNos.size());
        System.out.println("重复订单数："+(orderNos.size()-filterOrderNos.size()));

    }

    /**
     * method2
     */
    private static final AtomicInteger SEQ = new AtomicInteger(1000);
    private static final DateTimeFormatter DF_FMT_PREFIX = DateTimeFormatter.ofPattern("yyMMddHHmmssSS");
    private static ZoneId ZONE_ID = ZoneId.of("Asia/Shanghai");
    public static String generateOrderNo(){
        LocalDateTime dateTime = LocalDateTime.now(ZONE_ID);
        if (SEQ.intValue() > 9990){
            SEQ.getAndSet(1000);
        }
        return dateTime.format(DF_FMT_PREFIX)+SEQ.getAndIncrement();
    }



    /**
     * 此方法有单号重复问题
     */
    private static void method1() {
        final String merchId = "12334";
        List<String> orderNos = Collections.synchronizedList(new ArrayList<>());
        IntStream.range(0,100).parallel().forEach(i->{
            orderNos.add(getYYMMDDHHNumber(merchId));
        });

        List<String> filterOrderNos = orderNos.stream().distinct().collect(Collectors.toList());
        System.out.println("生成订单数："+orderNos.size());
        System.out.println("过滤重复后订单数："+filterOrderNos.size());
        System.out.println("重复订单数："+(orderNos.size()-filterOrderNos.size()));
    }

    /**
     * OD单号生成
     * 订单号生成规则：OD + yyMMddHHmmssSSS + 5位数(商户ID3位+随机数2位) 22位
     */
    private static String getYYMMDDHHNumber(String merchId){
        StringBuffer orderNo = new StringBuffer(new SimpleDateFormat("yyMMddHHmmssSSS").format(new Date()));
        if (StringUtils.isNotBlank(merchId)){
            if (merchId.length() > 3){
                orderNo.append(merchId.substring(0,3));
            }else {
                orderNo.append(merchId);
            }
        }
        int orderLength = orderNo.toString().length();
        String randomNum = getRandomByLength(20-orderLength);
        orderNo.append(randomNum);
        return orderNo.toString();
    }
    /** 生成指定位数的随机数 **/
    private static String getRandomByLength(int size) {
        if (size > 8 || size < 1){
            return "";
        }
        Random ne = new Random();
        StringBuffer endNumStr = new StringBuffer("1");
        StringBuffer staNumStr = new StringBuffer("9");
        for (int i = 0; i < size; i++) {
            endNumStr.append("0");
            staNumStr.append("0");
        }
        int randomNum = ne.nextInt(Integer.valueOf(staNumStr.toString())) + Integer.valueOf(endNumStr.toString());
        return String.valueOf(randomNum);
    }
}
