package com.demo.practical_training.common;

/**
 * 存放常量的地方
 */
public class Const {
    public static final int MANAGENEWSLEVEL = 2;
    public static final String USERINSESSION = "USERINSESSION";
    //新闻状态 0草稿 1审核中 2审核失败 3已发布  -1已删除 -2违规 -3下架
    public static final int NEWS_OFF = -3;
    public static final int NEWS_DISABLE = -2;
    public static final int NEWS_DELETED = -1;
    public static final int NEWS_PUBLISH = 3;
    public static final int NEWS_AUDIT_FAILURE = 2;
    public static final int NEWS_UNDER_REVIEW = 1;
    public static final int NEWS_DRAFT = 0;
    //用户状态 1正常用户 2新闻发布者 0已注销 -1违规禁言 -2违规封号
    public static final int USER_BANNED = -1;
    public static final int USER_OFF = -2;
    public static final int USER_LOGGED_OUT = 0;
    public static final int USER_NEWS_PUBLISHER = 2;
    public static final int USER_NORMAL_USER = 1;
    //是否实名认证 1是 0否
    public static final int USER_NOT_VERIFIED = 0;
    public static final int USER_VERIFIED = 1;
}
