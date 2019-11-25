# news_manage_background
# 管理员模块
## 审核模块
### 审核新闻
#### 审核新闻发布者的申请

```json
{
  "interface": "manage/newsApply",
  "note": "审核新闻发布申请",
  "get": [
    {
      "code": 200,
      "message": "成功获得新闻申请的列表",
      "data": [{
        "newsId": "新闻id",
        "newsTitle": "新闻标题",
        "newsTitleImgUrl": "新闻标题图片地址",
        "newsDetail": "新闻内容"
      }]
    },
    {
      "code": 500,
      "message": "权限不足",
      "data": {}
    }
  ],
  "put": [
    {
      "code": 200,
      "message": "修改成功",
      "data": {

      }
    },
    {
      "code": 500,
      "message": "权限不足",
      "data": {}
    }
  ]
}
```

---

#### 审核用户举报的文章
```json
{
  "interface": "manage/newsReport",
  "note": "审核用户举报的新闻",
  "get": [
    {
      "code": 200,
      "message": "成功获得用户的举报的列表",
      "data": [{
        "reportId": "举报id",
        "newsId": "举报的新闻id",
        "newsTitle": "新闻标题",
        "newsTitleImgUrl": "新闻标题图片地址",
        "newsDetail": "新闻内容"
      }]
    },
    {
      "code": 500,
      "message": "权限不足",
      "data": {}
    }
  ],
  "put": [
    {
      "code": 200,
      "message": "修改成功",
      "data": {
        "newsId": "新闻id",
        "newsTitle": "新闻标题",
        "newsTitleImgUrl": "新闻标题图片地址",
        "newsDetail": "新闻内容"
      }
    },
    {
      "code": 500,
      "message": "权限不足",
      "data": {}
    }
  ]
}
```
---
### 审核用户
#### 审核实名认证
```.json
{
  "interface": "manage/userVerified",
  "note": "审核用户实名认证",
  "get": [
    {
      "code": 200,
      "message": "成功获得用户的实名认证的列表",
      "data": [{
        "userId": "用户id",
        "userIdNumber":"用户身份证号码",
        "userIdCardUrl": "用户身份证照片地址",
        "userName": "用户姓名"
      }]
    },
    {
      "code": 500,
      "message": "权限不足",
      "data": {}
    }
  ],
  "put": [
    {
      "code": 200,
      "message": "修改成功",
      "data": {

      }
    },
    {
      "code": 500,
      "message": "权限不足",
      "data": {}
    }
  ]
}
```
---
#### 审核用户举报的用户
```.json
{
  "interface": "manage/userReport",
  "note": "审核用户举报的用户",
  "get": [
    {
      "code": 200,
      "message": "成功获得用户的举报的用户列表",
      "data": [{
        "reportId": "举报id",
        "userId": "用户id",
        "reportedId": "被举报者id",
        "context":"举报的内容"
      }]
    },
    {
      "code": 500,
      "message": "权限不足",
      "data": {}
    }
  ],
  "put": [
    {
      "code": 200,
      "message": "修改成功",
      "data": {
      }
    },
    {
      "code": 500,
      "message": "权限不足",
      "data": {}
    }
  ]
}
```
---
#### 审核用户成为新闻发布者
```.json
{
  "interface": "manage/userApplyToNewsMaker",
  "note": "审核用户申请成为新闻发布者",
  "get": [
    {
      "code": 200,
      "message": "成功获得用户申请成为新闻发布者的列表",
      "data": [{
        "applyId": "申请id",
        "userId": "用户id",
        "userName": "用户姓名",
        "context":"申请原因"
      }]
    },
    {
      "code": 500,
      "message": "权限不足",
      "data": {}
    }
  ],
  "put": [
    {
      "code": 200,
      "message": "修改成功",
      "data": {
      }
    },
    {
      "code": 500,
      "message": "权限不足",
      "data": {}
    }
  ]
}
```
---
## 管理模块
### 管理用户
```.json
{
  "interface": "manage/user",
  "note": "管理新闻",
  "get": [
    {
      "code": 200,
      "message": "成功获得用户闻列表",
      "data": [
        {
          "userId": "用户id",
          "userName": "用户姓名",
          "logoUrl":"头像地址"
        }
      ]
    },
    {
      "code": 500,
      "message": "权限不足",
      "data": {}
    }
  ],
  "put": [
    {
      "code": 200,
      "message": "修改成功",
      "data": {
      }
    },
    {
      "code": 500,
      "message": "权限不足",
      "data": {}
    }
  ]
}
```
---
### 管理新闻
```.json
{
  "interface": "manage/news",
  "note": "管理新闻",
  "get": [
    {
      "code": 200,
      "message": "成功获得新闻列表",
      "data": [
        {
          "newsId": "新闻id",
          "newsTitle": "新闻标题",
          "newsTitleImgUrl": "新闻标题图片地址",
          "newsDetail": "新闻内容"
        }
      ]
    },
    {
      "code": 500,
      "message": "权限不足",
      "data": {}
    }
  ],
  "put": [
    {
      "code": 200,
      "message": "修改成功",
      "data": {
      }
    },
    {
      "code": 500,
      "message": "权限不足",
      "data": {}
    }
  ]
}
```
---

## 各个包的意思
### common
各种通用类，数据等
### entity
实体类，对应数据库
### manage
管理员模块
### model
存放dto和vo的地方，请求和响应的实体
### user
用户模块
### util
工具类

##管理员权限分类
用户管理权限（增，删，改），每个管理员都可以查用户




##日志配置
项目统一使用slf4j框架