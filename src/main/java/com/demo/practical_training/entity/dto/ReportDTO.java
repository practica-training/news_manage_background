package com.demo.practical_training.entity.dto;

import lombok.Data;

@Data
public class ReportDTO {
        private String userId;
        private String reportedId;
        private String reportReason;
}
