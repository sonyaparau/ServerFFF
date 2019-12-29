package com.mobile.freeforfun.business.mapper;

import com.mobile.freeforfun.business.dto.FavoriteLocalsDto;
import com.mobile.freeforfun.business.dto.UserDto;
import com.mobile.freeforfun.persistence.model.FavouriteLocals;
import org.springframework.stereotype.Component;

@Component
public class VoteMapperImpl implements VoteMapper {
    @Override
    public FavouriteLocals toEntity(FavoriteLocalsDto localDto) {
       FavouriteLocals locals= new FavouriteLocals();
       locals.setId(localDto.getId());
       locals.setVoteType(localDto.getVoteType());
       return locals;
    }

    @Override
    public FavoriteLocalsDto toDto(FavouriteLocals locals) {
        return FavoriteLocalsDto.builder()
                .id(locals.getId())
                .voteType(locals.getVoteType())
                .build();
    }
}
