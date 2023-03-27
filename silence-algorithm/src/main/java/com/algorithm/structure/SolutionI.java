package com.algorithm.structure;

import cn.hutool.core.net.url.UrlQuery;
import com.alibaba.fastjson.JSON;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class SolutionI {
    public static void main(String[] args) {
        String s = "qcxy_biz_data=Yml6VHlwZT0xJnBheVR5cGU9YWxpcGF5JmlzQmFsYW5jZT1O&gmt_create=2022-09-06+10%3A26%3A10&charset=utf-8&seller_email=system%40xiaocaiyin.com&notify_time=2022-09-06+10%3A51%3A22&subject=%E8%AE%A2%E5%8D%95%E4%BF%A1%E6%81%AF%3A2022090603100587&sign=kX8fF98eQDuFItoZ4DdAyf5skd%2FSxl1za%2Ff7Ya0L4ZNQCukO5%2BayVsSeIrmLYja6AHh50OjOCIwaSMz5Bxjl8T3ntGKxaP8DBYojDytEbwVz%2F18%2F5eI6HFObSLam1LSZMZiZgpIpm7RIZlgLuXrJATWN7GTJOUk7gKEomCMhTonbX7mHL2pqpo%2BHor1tFiLr6b%2FY5XgUMhzeddyZkreP6o8VD1O54htjHR7i9TxaAZi2b1M%2B0Jm9XsCH8han5MGYbm59rJAhSNtxXsbO4nBJNqX4eZJufFTzeJDQjPP7WA2%2BQa%2Bgorur7JS3wMn%2B28dbJd4WAw2li6NMlPjZy85GnQ%3D%3D&buyer_id=2088522585926484&version=1.0&notify_id=2022090600222102610026481446705574&notify_type=trade_status_sync&out_trade_no=2022090603100587XCY2419&total_amount=3011.00&trade_status=WAIT_BUYER_PAY&trade_no=2022090622001426481407892095&auth_app_id=2017062607571700&buyer_logon_id=389***%40qq.com&app_id=2017062607571700&sign_type=RSA2&seller_id=2088721240772181";
        UrlQuery urlQuery = UrlQuery.of(s, StandardCharsets.UTF_8);
        Map<String, String> queryMap = (Map<String, String>) (Map) urlQuery.getQueryMap();
        System.out.println(JSON.toJSONString(queryMap));
    }
}
