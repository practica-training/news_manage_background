package com.demo.practical_training.model.response;

import com.demo.practical_training.common.response.ResultCode;
import lombok.ToString;

/**
 * 自定义异常信息
 */
@ToString
public enum AdminCode implements ResultCode {

    ADMIN_ALLOW_NEWS(true,24001,"举报新闻成功,此新闻已加入违规新闻！"),
    ADMIN_ALLOW_NEWS_FAIL(false,24002,"举报新闻失败！"),

    ADMIN_ALLOW_NEWS_MISS(true,24001,"忽略新闻成功执行成功！"),
    ADMIN_ALLOW_NEWS_MISS_FAIL(false,24002,"忽略新闻成功执行失败！"),

    ADMIN_ALLOW_USER(true,24003,"用户实名认证执行成功！"),
    ADMIN_ALLOW_USER_FAIL(false,24003,"用户实名认证执行失败！"),

    ADMIN_NOT_ALLOW_USER(true,24004,"用户实名认证失败执行成功！"),
    ADMIN_NOT_ALLOW_USER_FAIL(false,24004,"用户实名认证失败执行错误！"),

    ADMIN_ALLOW_REVIEW(true,24005,"举报用户成功执行成功！！"),
    ADMIN_ALLOW_REVIEW_FAIL(true,24005,"举报用户成功执行失败！！"),

    ADMIN_ALLOW(true,24005,"举报用户忽略成功执行成功！！"),
    ADMIN_ALLOW_FAIL(true,24005,"举报用户忽略成功执行失败！！"),



    ADMIN_ALLOW_USEREXISTENCE(true,10000,"成功将该用户解除禁言执行成功！！"),
    ADMIN_ALLOW_USEREXISTENCE_FAIL(true,10000,"成功将该用户解除禁言执行失败！"),


    ADMIN_NOT_ALLOW_USEREXISTENCE(true,10000,"成功将该用户进行禁言执行成功！！"),
    ADMIN_NOT_ALLOW_USEREXISTENCE_FAIL(true,10000,"成功将该用户进行禁言执行失败！！"),

    ADMIN_ALLOW_NEWSPUBLISH(true,24009,"新闻发布成功执行成功！"),
    ADMIN_ALLOW_NEWSPUBLISH_FAIL(false,24009,"新闻发布成功执行失败！"),

    ADMIN_NOT_ALLOW_NEWSPUBLISH(false,24010,"新闻发布失败执行成功！"),
    ADMIN_NOT_ALLOW_NEWSPUBLISH_FAIL(false,24010,"新闻发布失败执行失败！"),


    ADMIN_ALLOW_NEWSEXISTENCE(true,10000,"新闻下架成功！"),
    ADMIN_ALLOW_NEWSEXISTENCE_FAIL(true,10000,"新闻下架失败！"),

    ADMIN_NOT_ALLOW_NEWEXISTENCE(true,10000,"将新闻解除下架执行成功！"),
    ADMIN_NOT_ALLOW_NEWEXISTENCE_FAIL(false,10000,"将新闻解除下架执行失败！"),

    ADMIN_ALLOW_USERPUBLISH(true,24014,"允许成为新闻发布者成功！"),
    ADMIN_ALLOW_USERPUBLISH_FAIL(false,24014,"允许成为新闻发布者失败！"),

    ADMIN_NOT_ALLOW_USERPUBLISH(true,24015,"不允许成为新闻发布者成功！"),
    ADMIN_NOT_ALLOW_USERPUBLISH_FAIL(true,24015,"不允许成为新闻发布者失败！"),

    ADMIN_ALLOW_BECOMEUSER(true,24014,"降级为普通用户成功！"),
    ADMIN_ALLOW_BECOMEUSER_FAIL(false,24014,"降级为普通用户失败！"),

    ADMIN_BECOMEUSER(true,24015,"修改管理员等级成功！"),
    ADMIN_NOT_BECOMEUSER(false,24015,"修改管理员等级失败！");


    //操作代码
    boolean success;
    //操作代码
    int code;
    //提示信息
    String message;

    private AdminCode(boolean success, int code, String message){
        this.success = success;
        this.code = code;
        this.message = message;
    }

    @Override
    public boolean success() {
        return success;
    }

    @Override
    public int code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }
}
