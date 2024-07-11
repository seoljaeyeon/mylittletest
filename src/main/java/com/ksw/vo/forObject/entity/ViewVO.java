package com.ksw.vo.forObject.entity;

import java.sql.Timestamp;
import java.util.Objects;

public class ViewVO {

    private final Integer viewNo;
    private final Timestamp createdAt;

    // 생성자
    public ViewVO(Integer viewNo, Timestamp createdAt) {
        this.viewNo = viewNo;
        this.createdAt = createdAt;
    }

    // Getters
    public Integer getViewNo() {
        return viewNo;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    // 빌더 패턴 구현
    public static class Builder {
        private Integer viewNo;
        private Timestamp createdAt;

        public Builder viewNo(Integer viewNo) {
            this.viewNo = viewNo;
            return this;
        }

        public Builder createdAt(Timestamp createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public ViewVO build() {
            return new ViewVO(viewNo, createdAt);
        }
    }

    // toString 메소드
    @Override
    public String toString() {
        return "ViewVO{" +
                "viewNo=" + viewNo +
                ", createdAt=" + createdAt +
                '}';
    }

    // equals 메소드
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ViewVO viewVO = (ViewVO) o;
        return Objects.equals(viewNo, viewVO.viewNo) &&
                Objects.equals(createdAt, viewVO.createdAt);
    }

    // hashCode 메소드
    @Override
    public int hashCode() {
        return Objects.hash(viewNo, createdAt);
    }
}
