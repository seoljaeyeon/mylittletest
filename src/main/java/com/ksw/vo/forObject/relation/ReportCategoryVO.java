package com.ksw.vo.forObject.relation;

import java.io.Serializable;
import java.util.Objects;

import com.ksw.vo.forObject.entity.CategoryVO;
import com.ksw.vo.forObject.entity.ReportVO;
import com.ksw.vo.forObject.entity.UserVO;

public final class ReportCategoryVO implements Serializable {

	private static final long serialVersionUID = 1L;
    private final UserVO userVO;
    private final CategoryVO categoryVO;
    private final ReportVO reportVO;

    private ReportCategoryVO(Builder builder) {
        this.userVO = builder.userVO;
        this.categoryVO = builder.categoryVO;
        this.reportVO = builder.reportVO;
    }


	public UserVO getUserVO() {
		return userVO;
	}



	public CategoryVO getCategoryVO() {
		return categoryVO;
	}



	public ReportVO getReportVO() {
		return reportVO;
	}



	public ReportCategoryVO(UserVO userVO, CategoryVO categoryVO, ReportVO reportVO) {
		super();
		this.userVO = userVO;
		this.categoryVO = categoryVO;
		this.reportVO = reportVO;
	}


	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportCategoryVO that = (ReportCategoryVO) o;
        return Objects.equals(userVO, that.userVO) &&
                Objects.equals(categoryVO, that.categoryVO) &&
                Objects.equals(reportVO, that.reportVO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userVO, categoryVO, reportVO);
    }

    @Override
    public String toString() {
        return "ReportCategoryVO{" +
                "userVO=" + userVO +
                ", categoryVO=" + categoryVO +
                ", reportVO=" + reportVO +
                '}';
    }

    public static class Builder {
        private UserVO userVO;
        private CategoryVO categoryVO;
        private ReportVO reportVO;

        public Builder userVO(UserVO userVO) {
            this.userVO = userVO;
            return this;
        }

        public Builder categoryVO(CategoryVO categoryVO) {
            this.categoryVO = categoryVO;
            return this;
        }

        public Builder reportVO(ReportVO reportVO) {
            this.reportVO = reportVO;
            return this;
        }

        public ReportCategoryVO build() {
            return new ReportCategoryVO(this);
        }
    }
}
