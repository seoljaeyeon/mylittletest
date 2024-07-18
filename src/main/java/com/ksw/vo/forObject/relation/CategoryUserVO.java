package com.ksw.vo.forObject.relation;

import java.io.Serializable;
import java.util.Objects;

import com.ksw.vo.forObject.entity.CategoryVO;
import com.ksw.vo.forObject.entity.UserVO;

public final class CategoryUserVO implements Serializable {

	private static final long serialVersionUID = 1L;
    private final CategoryVO categoryVO;
    private final UserVO userVO;

    private CategoryUserVO(Builder builder) {
        this.categoryVO = builder.categoryVO;
        this.userVO = builder.userVO;
    }


    public CategoryUserVO(CategoryVO categoryVO, UserVO userVO) {
		super();
		this.categoryVO = categoryVO;
		this.userVO = userVO;
	}


	public CategoryVO getCategoryVO() {
		return categoryVO;
	}


	public UserVO getUserVO() {
		return userVO;
	}


	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryUserVO that = (CategoryUserVO) o;
        return Objects.equals(categoryVO, that.categoryVO) &&
                Objects.equals(userVO, that.userVO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryVO, userVO);
    }

    @Override
    public String toString() {
        return "UserCategoryVO{" +
                "categoryVO=" + categoryVO +
                ", userVO=" + userVO +
                '}';
    }

    public static class Builder {
        private CategoryVO categoryVO;
        private UserVO userVO;

        public Builder categoryVO(CategoryVO categoryVO) {
            this.categoryVO = categoryVO;
            return this;
        }

        public Builder userVO(UserVO userVO) {
            this.userVO = userVO;
            return this;
        }

        public CategoryUserVO build() {
            return new CategoryUserVO(this);
        }
    }
}
