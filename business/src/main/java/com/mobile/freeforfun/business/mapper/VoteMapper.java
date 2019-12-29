package com.mobile.freeforfun.business.mapper;
import com.mobile.freeforfun.business.dto.FavoriteLocalsDto;
import com.mobile.freeforfun.persistence.model.FavouriteLocal;

public interface VoteMapper {

    FavouriteLocal toEntity(FavoriteLocalsDto localsDto);
    FavoriteLocalsDto toDto(FavouriteLocal locals);
}
