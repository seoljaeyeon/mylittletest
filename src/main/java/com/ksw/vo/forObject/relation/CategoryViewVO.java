package com.ksw.vo.forObject.relation;


import java.util.Objects;

import com.ksw.vo.forObject.entity.CategoryVO;
import com.ksw.vo.forObject.entity.ViewVO;

public class CategoryViewVO {

    private final CategoryVO categoryVO;
    private final ViewVO viewVO;

    // 생성자
    public CategoryViewVO(CategoryVO categoryVO, ViewVO viewVO) {
        this.categoryVO = categoryVO;
        this.viewVO = viewVO;
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

        public Builder categoryVO(CategoryVO categoryVO) {
            this.categoryVO = categoryVO;
            return this;
        }

        public Builder viewVO(ViewVO viewVO) {
            this.viewVO = viewVO;
            return this;
        }

        public CategoryViewVO build() {
            return new CategoryViewVO(categoryVO, viewVO);
        }
    }

    // toString 메소드
    @Override
    public String toString() {
        return "CategoryViewVO{" +
                "categoryVO=" + categoryVO +
                ", viewVO=" + viewVO +
                '}';
    }

    // equals 메소드
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryViewVO that = (CategoryViewVO) o;
        return Objects.equals(categoryVO, that.categoryVO) &&
                Objects.equals(viewVO, that.viewVO);
    }

    // hashCode 메소드
    @Override
    public int hashCode() {
        return Objects.hash(categoryVO, viewVO);
    }
}
