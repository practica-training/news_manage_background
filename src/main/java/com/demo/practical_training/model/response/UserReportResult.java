package com.demo.practical_training.model.response;

import com.demo.practical_training.common.response.ResponseResult;
import com.demo.practical_training.common.response.ResultCode;
import com.demo.practical_training.entity.UserReport;
import lombok.Data;

/**
 * 用户举报数据模型
 */
@Data
public class UserReportResult extends ResponseResult {
    UserReport UserReport;

    public UserReportResult(ResultCode resultCode, UserReport UserReport) {
        super(resultCode);
        this.UserReport = UserReport;
    }
}
