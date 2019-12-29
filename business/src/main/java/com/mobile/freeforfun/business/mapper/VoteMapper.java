package com.mobile.freeforfun.business.mapper;
import com.mobile.freeforfun.business.dto.FavoriteLocalsDto;
import com.mobile.freeforfun.persistence.model.FavouriteLocals;

public interface VoteMapper {

    FavouriteLocals toEntity(FavoriteLocalsDto localsDto);
    FavoriteLocalsDto toDto(FavouriteLocals locals);
}
