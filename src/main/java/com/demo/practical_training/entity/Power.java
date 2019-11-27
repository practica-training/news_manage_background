package com.demo.practical_training.entity;

import javax.persistence.Entity;

/**
 * 管理员权限
 * 1.超级管理员具有全部权限
 * 2.权限分为
 *      用户与新闻举报
 *      审核新闻举报 0
 *      审核用户举报 0
 *
 *      用户新闻申请
 *      审核用户实名认证 1
 *      审核新闻发布 1
 *
 *      用户新闻管理
 *      管理用户（封号） 2
 *      管理新闻 （对新闻进行下架）2
 *
 *      新闻发布者管理
 *      审核用户申请为新闻发布者 3
 *      管理员新闻发布者(将新闻发布者进行降级） 3
 *
 *      超级管理员
 *      管理管理员 4
 *      日志查看功能 4
 *
 * 管理员分为5个等级
 *
 */
//@Entity
    @Deprecated
public class Power {

}
