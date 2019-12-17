package com.demo.practical_training.entity;

/**
 * 管理员权限
 * 1.超级管理员具有全部权限
 * 2.权限分为
 *      用户与新闻举报
 *      审核新闻举报 0
 *          返回根据举报时间降序，而且审核状态为"未审核"的举报列表
 *          {
 *           id:"举报id",
 *           newsId:"新闻id",
 *           newsTitle:"新闻标题",
 *           userName:"举报者",
 *           reason:"举报原因",
 *           reportTime:"举报时间"
 *         },
 *          对新闻进行审核，可以是
 *              忽略该举报
 *                  发送举报id
 *                  后台将状态改为审核完成
 *                  返回：
 *                      {
 *                          code：10000，
 *                          message:"忽略成功，将状态修改成已经审核"
 *                      }
 *              下架新闻
 *                  发送新闻id
 *                  将所有有关ß该新闻的举报全部改成违规
 *                  将新闻进行下架
 *
 *                  返回：
 *                      {
 *                          code:10000,
 *                          message:"成功将该新闻下架"
 *                      }
 *
 *      审核用户举报 0
 *          返回根据举报时间降序，而且审核状态为"未审核"的举报列表
 *          {
     *          reportId: "举报id",
    *           userName: "举报者",
    *           reportedUserName: "被举报者1",
    *           reportedUserId: "被举报者id1",
    *           comment: "评论内容",
    *           reason: "举报原因",
    *           reportTime: "举报时间"
 *           }
 *          对用户的举报进行审核
 *              忽略该举报
*                   发送举报id
*                   后台将状态改为忽略
 *                  返回：
 *                      {
 *                          code:10000,
 *                          message:"忽略成功，将状态修改成已经审核"
 *                      }
 *              惩罚用户
 *                  发送用户id和惩罚天数
 *                  将所有有关该用户的id举报全部改成违规
 *                  对用户进行惩罚
 *                  返回：
 *                      {
 *                          code:10000,
 *                          message:"成功将该用户进行惩罚"
 *                      }
 *
 *      用户新闻申请
 *      审核用户实名认证 1
 *          根据申请时间降序，而且审核状态为"未审核"的申请列表
 *          {
 *              id:"实名认证的id",
 *              userId:"申请实名认证的用户id",
 *              userName:"申请者姓名",
 *              registrationTime:"用户的注册时间",
 *              idCard:"申请者的身份证号码",
 *              realName:"申请者的真实姓名",
 *              photo:"申请者拍的身份证照片"
 *          }
 *          申请的审核可以是
 *              审核不通过
 *                  发送用户实名认证id，不通过的原因
 *                  将状态改为不通过
 *              审核通过
 *                  发送用户实名认证id
 *                  将用户的申请改为通过
 *                  将User状态改为已审核
 *
 *      审核新闻发布 1
 *          根据申请时间降序，而且审核状态为"未审核"的申请列表
 *          {
 *           newsId: "新闻id",
 *           newsTitle: "新闻标题",
 *           content: "新闻内容",
 *           newsTypeList: ["类型1", "类型2"],
 *           newsAvatar: "新闻封面",
 *           createTime:"创建时间"
 *         }
 *         对新闻的审核发布可以是
 *              审核通过
 *                  发送审核id
 *                  将新闻状态改成发布，并将发布时间改成now
 *               审核不通过
 *                  发送审核id，不通过原因
 *                  将新闻状态改成不通过，并将不通过原因写在新闻类中
 *
 *
 *
 *
 *    ##### 用户新闻管理
 *      管理用户（封号） 2
 *          根据违规在前，不违规在后，申请时间降序
 *           {
     *           userId: "用户id",
     *           userName: "用户姓名",
     *           violationNumber: "违规次数",
     *           userState: "是否违规",
     *           normalDate: "恢复正常能够说话的日子",
     *           registrationTime: "注册时间"
 *              注意注册时间格式为yyyy-mm-dd
 *          },
 *          对用户的操作可以是
 *              对用户进行禁言操作
                    发送用户id和惩罚天数和惩罚原因
 *                  将用户状态进行修改,并将正常时间改成该时间,
 *
 *                  返回：
 *                      {
 *                          code:10000,
 *                          message:"成功将该用户进行惩罚"
 *                      }
 *               对用户解除禁言操作
 *                  发送用户id
 *                  将用户状态进行修改，将正常时间改成空
                    返回：
 *                      {
 *                          code:10000,
 *                          message: "成功将该用户解除禁言"
 *                      }
 *
 *      管理新闻 （对新闻进行下架）2(未做)
 *             根据违规在前，不违规在后，发布时间降序
 *  *           {
 *      *           newsId: "新闻id",
 *                  newsAvatar:"新闻封面路径",
 *      *           newsTitle: "新闻标题",
 *      *           newsState: "是否违规",
 *                  violationReason:"违规原因",
 *      *           content: "新闻内容",
             *      readNumber:"阅读数",
 *                  likeNumber:"点赞数",
 *                  publishTime:"发布时间"
 *  *              注意发布时间格式为yyyy-mm-dd
 *  *          },
 *  *          对新闻的操作可以是
 *  *              对新闻进行下架操作
 *                     发送新闻id和惩罚原因
 *  *                  将新闻状态进行修改,并修改failureReason
 *  *                  返回：
 *  *                      {
 *  *                          code:10000,
 *  *                          message:"成功将该用户进行惩罚"
 *  *                      }
 *  *               对新闻进行解除下架操作（这里是给管理员在违规误处理的时候，可以对违规进行修改）
 *  *                  发送新闻id
 *  *                  将新闻状态改成正常
 *                     返回：
 *  *                      {
 *  *                          code:10000,
 *  *                          message: "成功将新闻变成正常"
 *  *                      }
 *
 *      新闻发布者管理
 *      审核用户申请为新闻发布者 3
            根据申请时间降序，而且审核状态为"未审核"的申请列表
 *          {
 *              id:"申请的id",
 *              userId:"申请新闻发布者的用户id",
 *              userName:"新闻发布者姓名",
 *              registrationTime:"新闻发布者的注册时间",
 *              reason:"申请原因"
 *          }
 *          申请的审核可以是
 *              审核不通过
 *                  发送申请的id，不通过的原因
 *                  将状态改为不通过
 *              审核通过
 *                  发送申请的id
 *                  将用户的申请改为通过
 *                  将UserApplyToNewsMaker状态改为已审核
 *
 *
 *
 *      管理员新闻发布者(将新闻发布者进行降级） 3（未做）
            根据申请时间降序，而且审核状态为"未审核"的申请列表
 *          {
 *              userId:"新闻发布者id",
 *              userName:"新闻发布者名",
 *              registrationTime:"新闻发布者的注册时间",
 *              newsListSize:"发布的新闻数"
 *          }
 *          新闻发布者管理可以是
 *              将新闻发布者降级为普通用户
 *                  发送新闻发布者的id
 *                  将新闻发布者进行降级
 *
 *      超级管理员
 *      管理管理员 4
            根据权力等级时间降序
 *          {
 *  *              adminId:"管理员id",
 *  *              adminName:"管理员用户名",
 *  *              power:"权力等级"
 *  *          }
 *          对管理员的管理可以是
 *             修改管理员的权力
 *                  发送管理员的id,新的权力等级
 *                  修改管理员等级
 *      日志查看功能 4
 *
 *
 * 管理员分为5个等级
 *
 */
//@Entity
    @Deprecated
public class Power {

}
