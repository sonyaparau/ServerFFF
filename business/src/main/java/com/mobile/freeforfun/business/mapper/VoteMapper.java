package com.mobile.freeforfun.business.mapper;
import com.mobile.freeforfun.business.dto.FavoriteLocalsDto;
import com.mobile.freeforfun.business.dto.LocalDto;
import com.mobile.freeforfun.persistence.model.FavouriteLocal;
import com.mobile.freeforfun.persistence.model.Local;

import java.util.List;

public interface VoteMapper {

    FavouriteLocal toEntity(FavoriteLocalsDto localsDto);
    FavoriteLocalsDto toDto(FavouriteLocal locals);
    List<FavoriteLocalsDto> toDtoList(List<FavouriteLocal> allLocals);

}
