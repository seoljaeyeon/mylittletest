package com.ksw.dto;

public class ReportManagementDTO {

    private Integer userNo;
    private Integer managerNo;
    private Integer solverNo;

    // 기본 생성자
    public ReportManagementDTO() {}

    // Getter 및 Setter
    public Integer getUserNo() {
        return userNo;
    }

    public void setUserNo(Integer userNo) {
        this.userNo = userNo;
    }

    public Integer getManagerNo() {
        return managerNo;
    }

    public void setManagerNo(Integer managerNo) {
        this.managerNo = managerNo;
    }

    public Integer getSolverNo() {
        return solverNo;
    }

    public void setSolverNo(Integer solverNo) {
        this.solverNo = solverNo;
    }

    // 빌더 패턴 구현
    public static class Builder {
        private Integer userNo;
        private Integer managerNo;
        private Integer solverNo;

        public Builder userNo(Integer userNo) {
            this.userNo = userNo;
            return this;
        }

        public Builder managerNo(Integer managerNo) {
            this.managerNo = managerNo;
            return this;
        }

        public Builder solverNo(Integer solverNo) {
            this.solverNo = solverNo;
            return this;
        }

        public ReportManagementDTO build() {
            ReportManagementDTO reportManagementDTO = new ReportManagementDTO();
            reportManagementDTO.userNo = this.userNo;
            reportManagementDTO.managerNo = this.managerNo;
            reportManagementDTO.solverNo = this.solverNo;
            return reportManagementDTO;
        }
    }
}
