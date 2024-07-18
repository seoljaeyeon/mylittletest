package com.ksw.vo.forObject.relation;

import java.io.Serializable;
import java.util.Objects;

import com.ksw.vo.forObject.entity.UserVO;

public final class ReportManagementVO implements Serializable {

	private static final long serialVersionUID = 1L;
    private final UserVO userVO;
    private final UserVO managerVO;
    private final UserVO solverVO;

    private ReportManagementVO(Builder builder) {
        this.userVO = builder.userVO;
        this.managerVO = builder.managerVO;
        this.solverVO = builder.solverVO;
    }


	public UserVO getUserVO() {
		return userVO;
	}


	public UserVO getManagerVO() {
		return managerVO;
	}


	public ReportManagementVO(UserVO userVO, UserVO managerVO, UserVO solverVO) {
		super();
		this.userVO = userVO;
		this.managerVO = managerVO;
		this.solverVO = solverVO;
	}


	public UserVO getSolverVO() {
		return solverVO;
	}


	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportManagementVO that = (ReportManagementVO) o;
        return Objects.equals(userVO, that.userVO) &&
                Objects.equals(managerVO, that.managerVO) &&
                Objects.equals(solverVO, that.solverVO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userVO, managerVO, solverVO);
    }

    @Override
    public String toString() {
        return "ReportManagementVO{" +
                "userVO=" + userVO +
                ", managerVO=" + managerVO +
                ", solverVO=" + solverVO +
                '}';
    }

    public static class Builder {
        private UserVO userVO;
        private UserVO managerVO;
        private UserVO solverVO;

        public Builder userVO(UserVO userVO) {
            this.userVO = userVO;
            return this;
        }

        public Builder managerVO(UserVO managerVO) {
            this.managerVO = managerVO;
            return this;
        }

        public Builder solverVO(UserVO solverVO) {
            this.solverVO = solverVO;
            return this;
        }

        public ReportManagementVO build() {
            return new ReportManagementVO(this);
        }
    }
}
