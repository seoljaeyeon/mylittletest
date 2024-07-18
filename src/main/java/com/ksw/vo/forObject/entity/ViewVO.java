package com.ksw.vo.forObject.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class ViewVO implements Serializable {

	private static final long serialVersionUID = 1L;

    private final Integer viewNo;

    // 생성자
    public ViewVO(Integer viewNo) {
        this.viewNo = viewNo;
    }

    // Getters
    public Integer getViewNo() {
        return viewNo;
    }

    // 빌더 패턴 구현
    public static class Builder {
        private Integer viewNo;

        public Builder viewNo(Integer viewNo) {
            this.viewNo = viewNo;
            return this;
        }
        public ViewVO build() {
            return new ViewVO(viewNo);
        }
    }

    // toString 메소드
    @Override
    public String toString() {
        return "ViewVO{" +
                "viewNo=" + viewNo +
                '}';
    }

    // equals 메소드
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ViewVO viewVO = (ViewVO) o;
        return Objects.equals(viewNo, viewVO.viewNo);
    }

    // hashCode 메소드
    @Override
    public int hashCode() {
        return Objects.hash(viewNo);
    }
}
