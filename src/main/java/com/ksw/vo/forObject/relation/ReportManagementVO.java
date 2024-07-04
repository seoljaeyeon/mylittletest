package com.ksw.vo.forObject.relation;

import java.util.Objects;

public final class ReportManagementVO {
    private final Integer userNo;
    private final Integer managerNo;
    private final Integer solverNo;

    private ReportManagementVO(Builder builder) {
        this.userNo = builder.userNo;
        this.managerNo = builder.managerNo;
        this.solverNo = builder.solverNo;
    }

    public Integer getUserNo() {
        return userNo;
    }

    public Integer getManagerNo() {
        return managerNo;
    }

    public Integer getSolverNo() {
        return solverNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportManagementVO that = (ReportManagementVO) o;
        return Objects.equals(userNo, that.userNo) &&
                Objects.equals(managerNo, that.managerNo) &&
                Objects.equals(solverNo, that.solverNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userNo, managerNo, solverNo);
    }

    @Override
    public String toString() {
        return "ReportManagementVO{" +
                "userNo=" + userNo +
                ", managerNo=" + managerNo +
                ", solverNo=" + solverNo +
                '}';
    }

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

        public ReportManagementVO build() {
            return new ReportManagementVO(this);
        }
    }
}
