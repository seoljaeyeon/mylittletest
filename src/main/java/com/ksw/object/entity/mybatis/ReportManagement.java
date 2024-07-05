package com.ksw.object.entity.mybatis;

public class ReportManagement {

    private Integer userNo;
    private Integer managerNo;
    private Integer solverNo;

    // 기본 생성자
    public ReportManagement() {}

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
}
