package com.mobile.freeforfun.business.mapper;

import com.mobile.freeforfun.business.dto.FavoriteLocalsDto;
import com.mobile.freeforfun.persistence.model.FavouriteLocal;
import org.springframework.stereotype.Component;

@Component
public class VoteMapperImpl implements VoteMapper {
    @Override
    public FavouriteLocal toEntity(FavoriteLocalsDto localDto) {
       FavouriteLocal locals= new FavouriteLocal();
       locals.setId(localDto.getId());
       locals.setVoteType(localDto.getVoteType());
       return locals;
    }

    @Override
    public FavoriteLocalsDto toDto(FavouriteLocal locals) {
        return FavoriteLocalsDto.builder()
                .id(locals.getId())
                .voteType(locals.getVoteType())
                .build();
    }
}
