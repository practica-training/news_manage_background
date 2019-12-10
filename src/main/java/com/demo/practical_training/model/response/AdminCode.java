package com.demo.practical_training.model.response;

import com.demo.practical_training.common.response.ResultCode;
import lombok.ToString;

/**
 * 自定义异常信息
 */
@ToString
public enum AdminCode implements ResultCode {
    ADMIN_ALLOW_NEWS(true,24001,"举报新闻成功,此新闻已加入违规新闻！"),
    ADMIN_NOT_ALLOW_NEWS(false,24002,"举报新闻失败！"),
    ADMIN_WAIT_NEWS(false,24002,"等待审核！"),
    ADMIN_ALLOW_USER(true,24003,"用户实名认证成功！"),
    ADMIN_NOT_ALLOW_USER(false,24004,"用户实名认证失败！"),
    ADMIN_ALLOW_REVIEW(true,24005,"举报用户成功！"),
    ADMIN_NOT_ALLOW_REVIEW(false,24006,"举报用户失败！"),
    ADMIN_ALLOW_USEREXISTENCE(false,10000,"成功将该用户解除禁言！"),
    ADMIN_NOT_ALLOW_USEREXISTENCE(true,10000,"成功将该用户进行惩罚！"),
    ADMIN_ALLOW_NEWSPUBLISH(true,24009,"新闻发布成功！"),
    ADMIN_NOT_ALLOW_NEWSPUBLISH(false,24010,"新闻发布失败！"),
    ADMIN_NEWSPUBLISH_WAIT(false,24011,"新闻发布等待审核！"),
    ADMIN_ALLOW_NEWSEXISTENCE(true,24012,"新闻下架成功！"),
    ADMIN_NOT_ALLOW_NEWEXISTENCE(false,24013,"新闻下架失败！"),
    ADMIN_ALLOW_USERPUBLISH(true,24014,"允许成为新闻发布者！"),
    ADMIN_NOT_ALLOW_USERPUBLISH(false,24015,"不允许成为新闻发布者！"),
    ADMIN_ALLOW_BECOMEUSER(true,24014,"降级为普通用户！"),
    ADMIN_NOT_ALLOW_BECOMEUSER(false,24015,"降级为普通用户失败！");


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
