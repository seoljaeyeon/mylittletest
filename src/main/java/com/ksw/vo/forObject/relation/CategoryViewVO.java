package com.ksw.vo.forObject.relation;


import java.io.Serializable;
import java.util.Objects;

import com.ksw.vo.forObject.entity.CategoryVO;
import com.ksw.vo.forObject.entity.UserVO;
import com.ksw.vo.forObject.entity.ViewVO;

public class CategoryViewVO implements Serializable {

	private static final long serialVersionUID = 1L;

    private final CategoryVO categoryVO;
    private final ViewVO viewVO;
    private final UserVO userVO;

    // 생성자
    public CategoryViewVO(Builder builder) {
    	this.categoryVO = builder.categoryVO;
    	this.viewVO = builder.viewVO;
    	this.userVO = builder.userVO;
    }

    // Getters
    public CategoryVO getCategoryVO() {
        return categoryVO;
    }

    public ViewVO getViewVO() {
        return viewVO;
    }

    // 빌더 패턴 구현
    public static class Builder {
        private CategoryVO categoryVO;
        private ViewVO viewVO;
        private UserVO userVO;

        public Builder categoryVO(CategoryVO categoryVO) {
            this.categoryVO = categoryVO;
            return this;
        }

        public Builder viewVO(ViewVO viewVO) {
            this.viewVO = viewVO;
            return this;
        }
        
        public Builder userVO(UserVO userVO) {
            this.userVO = userVO;
            return this;
        }

        public CategoryViewVO build() {
            return new CategoryViewVO(this);
        }
    }

    // toString 메소드
    @Override
    public String toString() {
        return "CategoryViewVO{" +
                "categoryVO=" + categoryVO +
                ", viewVO=" + viewVO +
                ", userVO=" + userVO +
                '}';
    }

    // equals 메소드
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryViewVO that = (CategoryViewVO) o;
        return Objects.equals(categoryVO, that.categoryVO) &&
                Objects.equals(viewVO, that.viewVO) &&
                Objects.equals(userVO, that.userVO);
    }

    // hashCode 메소드
    @Override
    public int hashCode() {
        return Objects.hash(categoryVO, viewVO, userVO);
    }
}
