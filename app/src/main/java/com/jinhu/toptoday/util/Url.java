package com.jinhu.toptoday.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 类的用途：
 * Created by jinhu
 * 2017/4/12  19:01
 */

public class Url {
    //登录状态
    public static final int LogED = 1;
    public static final int UNLOG = 0;

    //
    public static final String ADD_BASE = "http://result.eolinker.com/k2BaduF2a6caa275f395919a66ab1dfe4b584cc60685573";
    public static final String PARAM_01 = "tt";
    public static final String PARAM_02 = "shehui";
    public static final String PARAM_03 = "gn";
    public static final String PARAM_04 = "yl";
    public static final String PARAM_05 = "ty";
    public static final String PARAM_06 = "js";
    public static final String PARAM_07 = "kj";
    public static final String PARAM_08 = "cj";
    public static final String PARAM_09 = "ss";
    public static final String PARAM_10 = "gj";

    public static final String[] TITLES = {"头条", "社会", "国内", "娱乐", "体育", "军事", "科技", "财经", "时尚", "国际"};

    public static final String[] ADDS = {PARAM_01, PARAM_02, PARAM_03, PARAM_04, PARAM_05, PARAM_06, PARAM_07, PARAM_08, PARAM_09, PARAM_10};

    //频道和条目对应
    public static Map<String, String> getADD() {
        Map<String, String> map = new HashMap<>();
        //List<Map<String, String>> urlList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            map.put(TITLES[i], ADDS[i]);
            //urlList.add(map);
        }
        return map;
    }

    //系统常量
    public static final String SYSTEM_TYPE = "android";
    public static final String SYSTEM_VERSION = "V1.0";
    public static final String SYSTEM_SHARE_NAME = "Yokey_Nsg";

    //public static final String LINK_MAIN = "http://169.254.159.111/";
    public static final String LINK_MAIN = "http://192.168.23.226/";   // 服务器域名地址
    //public static final String LINK_MAIN = "http://169.254.28.80/";
    //public static final String LINK_MAIN = "http://192.168.56.1/";
    //public static final String LINK_MAIN = "http://192.168.1.105/";

    public static final String LINK_WAP = LINK_MAIN + "wap/";
    public static final String LINK_WAP_FIND_PASSWORD = LINK_WAP + "tmpl/member/find_password.html";

    public static final String LINK_MOBILE = LINK_MAIN + "mobile/index.php?act=";
    public static final String LINK_MOBILE_INDEX = LINK_MOBILE + "index";                                                          //首页 GET
    public static final String LINK_MOBILE_LOGIN = LINK_MOBILE + "login";                                                          //登录 POST
    public static final String LINK_MOBILE_REG = LINK_MOBILE + "login&op=register";                                                //注册 POST
    public static final String LINK_MOBILE_LOGOUT = LINK_MOBILE + "logout";                                                        //注销 POST
    public static final String LINK_MOBILE_USER = LINK_MOBILE + "member_index";                                                    //个人中心 POST
    public static final String LINK_MOBILE_CLASS = LINK_MOBILE + "goods_class";                                                    //分类 GET
    public static final String LINK_MOBILE_CART = LINK_MOBILE + "member_cart&op=cart_list";                                        //购物车 POST
    public static final String LINK_MOBILE_CART_DEL = LINK_MOBILE + "member_cart&op=cart_del";                                     //购物车删除 POST
    public static final String LINK_MOBILE_CART_ADD = LINK_MOBILE + "member_cart&op=cart_add";                                     //购物车添加 POST
    public static final String LINK_MOBILE_AREA = LINK_MOBILE + "area&op=index";                                     //地区列表 POST
    public static final String LINK_MOBILE_ORDER = LINK_MOBILE + "member_order&op=order_list";                                     //所有订单 POST
    public static final String LINK_MOBILE_ORDER_CANCEL = LINK_MOBILE + "member_order&op=order_cancel";                            //取消订单 POST
    public static final String LINK_MOBILE_ORDER_REFOUND = LINK_MOBILE + "member_refund&op=get_refund_list&page=100";              //退款订单 GET
    public static final String LINK_MOBILE_ORDER_REFOUND_INFO = LINK_MOBILE + "member_refund&op=get_refund_info";                  //退款订单详细 GET
    public static final String LINK_MOBILE_ORDER_RETURN = LINK_MOBILE + "member_return&op=get_return_list&page=100";               //退货订单 GET
    public static final String LINK_MOBILE_ADDRESS = LINK_MOBILE + "member_address&op=address_list";                               //收货地址 POST
    public static final String LINK_MOBILE_ADDRESS_ADD = LINK_MOBILE + "member_address&op=address_add";                            //添加收货地址 POST
    public static final String LINK_MOBILE_ADDRESS_DEL = LINK_MOBILE + "member_address&op=address_del";                            //删除收货地址 POST
    public static final String LINK_MOBILE_ADDRESS_EDIT = LINK_MOBILE + "member_address&op=address_edit";

}
