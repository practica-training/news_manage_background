package com.demo.practical_training.entity;

import javax.persistence.Entity;

/**
 * 管理员权限
 * 1.超级管理员具有全部权限
 * 2.权限分为
 *      审核新闻举报 0
 *      审核用户实名认证 0
 *
 *      审核用户举报 1
 *      管理用户（封号） 1
 *
 *      审核新闻发布 2
 *      管理新闻 （对新闻进行下架）2
 *
 *      审核用户申请为新闻发布者 3
 *      管理员新闻发布者(将新闻发布者进行降级） 3
 *
 *      审核管理员账号申请 4
 *      管理所有管理员的权限 4
 *
 * 管理员分为5个等级
 *
 */
//@Entity
public class Power {

}
