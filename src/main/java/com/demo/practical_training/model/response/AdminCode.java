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
    ADMIN_NOT_ALLOW_REVIEW(false,24004,"举报用户失败！"),
    ADMIN_ALLOW_USEREXISTENCE(false,24005,"封号失败！"),
    ADMIN_NOT_ALLOW_USEREXISTENCE(true,24004,"封号成功！"),
    ADMIN_ALLOW_NEWSPUBLISH(true,24005,"新闻发布成功！"),
    ADMIN_NOT_ALLOW_NEWSPUBLISH(false,24004,"新闻发布失败！"),
    ADMIN_NEWSPUBLISH_WAIT(false,24004,"新闻发布等待审核！"),
    ADMIN_ALLOW_NEWSEXISTENCE(true,24005,"新闻下架成功！"),
    ADMIN_NOT_ALLOW_NEWEXISTENCE(false,24004,"新闻下架失败！");


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
