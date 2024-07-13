package com.ksw.vo.forObject.relation;

import java.io.Serializable;
import java.util.Objects;

import com.ksw.vo.forObject.entity.CategoryVO;
import com.ksw.vo.forObject.entity.FavoriteVO;
import com.ksw.vo.forObject.entity.UserVO;

public final class FavoriteCategoryVO implements Serializable {

	private static final long serialVersionUID = 1L;
    private final UserVO userVO;
    private final CategoryVO categoryVO;
    private final FavoriteVO favoriteVO;

    private FavoriteCategoryVO(Builder builder) {
        this.userVO = builder.userVO;
        this.categoryVO = builder.categoryVO;
        this.favoriteVO = builder.favoriteVO;
    }

    public UserVO getUserVO() {
		return userVO;
	}

	public CategoryVO getCategoryVO() {
		return categoryVO;
	}

	public FavoriteVO getFavoriteVO() {
		return favoriteVO;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavoriteCategoryVO that = (FavoriteCategoryVO) o;
        return Objects.equals(userVO, that.userVO) &&
                Objects.equals(categoryVO, that.categoryVO) &&
                Objects.equals(favoriteVO, that.favoriteVO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userVO, categoryVO, favoriteVO);
    }

    @Override
    public String toString() {
        return "FavoriteCategoryVO{" +
                "userVO=" + userVO +
                ", categoryVO=" + categoryVO +
                ", favoriteVO=" + favoriteVO +
                '}';
    }

    public static class Builder {
        private UserVO userVO;
        private CategoryVO categoryVO;
        private FavoriteVO favoriteVO;

        public Builder userVO(UserVO userVO) {
            this.userVO = userVO;
            return this;
        }

        public Builder categoryVO(CategoryVO categoryVO) {
            this.categoryVO = categoryVO;
            return this;
        }

        public Builder favoriteVO(FavoriteVO favoriteVO) {
            this.favoriteVO = favoriteVO;
            return this;
        }

        public FavoriteCategoryVO build() {
            return new FavoriteCategoryVO(this);
        }
    }
}
