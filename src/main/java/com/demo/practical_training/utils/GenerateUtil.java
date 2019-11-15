package com.demo.practical_training.utils;

import org.springframework.util.DigestUtils;

import java.util.UUID;

/**
 * 生成各种ID的工具
 * 生成规则: 识别字符 + 16位md5(UUID + 3位随机数)
 */
public class GenerateUtil {
    /**
     * 生成UUID
     * @return
     */
    private static String getUUID(){
        String uuid = UUID.randomUUID().toString();
        return uuid.replace("-", "");
    }

    /**
     * 随机生成 num 位的随机数
     * @param num
     * @return
     */
    private static String getRandomNumber(int num){
        String randomNumber = "";
        for(int i =0;i < num; i++){
            int number = (int) (Math.random() * 10);
            randomNumber += "" + number;
        }
        return randomNumber;
    }

    /**
     * 通过识别字符生成对应的ID
     * @return
     */
    private static String generateID(String characterRecognition){
        //获取uuid
        String uuid = getUUID();
        //获取3位随机数
        String randomNumber = getRandomNumber(3);
        //md5加密uuid和3位随机数
        String ID = characterRecognition + DigestUtils.md5DigestAsHex((uuid + randomNumber).getBytes());
        return ID;
    }

    /**
     * 生成用户ID
     * @return
     */
    public static String generateUserID(){
        return generateID("U");
    }

    /**
     * 生成新闻ID
     * @return
     */
    public static String generateNewsID(){
        return generateID("N");
    }

    /**
     * 生成管理员ID
     * @return
     */
    public static String generateAdminID(){
        return generateID("A");
    }

    /**
     * 生成评论ID
     * @return
     */
    public static String generateCommentID(){
        return generateID("C");
    }

    /**
     * 生成消息ID
     * @return
     */
    public static String generateMessageID(){
        return generateID("M");
    }

    /**
     * 生成新闻标签ID
     * @return
     */
    public static String generateNewsLabelID(){
        return generateID("L");
    }

    /**
     * 生成用户举报表ID
     * @return
     */
    public static String generateUserReportID(){
        return generateID("P");
    }

    /**
     * 生成新闻举报表ID
     * @return
     */
    public static String generateNewsReportID(){
        return generateID("Q");
    }

    /**
     * 生成用户违规表ID
     * @return
     */
    public static String generateUserViolationID(){
        return generateID("R");
    }

    /**
     * 生成新闻违规表ID
     * @return
     */
    public static String generateNewsViolationID(){
        return generateID("S");
    }

    /**
     * 生成新闻类型ID
     * @return
     */
    public static String generateNewsTypeID(){
        return generateID("T");
    }

    /**
     * 生成实名认证表ID
     * @return
     */
    public static String generateUserVerifiedID(){
        return generateID("V");
    }

    /**
     * 生成用户管理日志ID
     * @return
     */
    public static String generateUserManagementLogID(){
        return generateID("X");
    }

    /**
     * 生成新闻管理日志ID
     * @return
     */
    public static String generateNewsManagementLogID(){
        return generateID("Y");
    }
}
