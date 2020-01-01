package com.mobile.freeforfun.business.mapper;

import com.mobile.freeforfun.business.dto.FavoriteLocalsDto;
import com.mobile.freeforfun.business.dto.LocalDto;
import com.mobile.freeforfun.persistence.model.FavouriteLocal;
import com.mobile.freeforfun.persistence.model.Local;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VoteMapperImpl implements VoteMapper {
    @Override
    public FavouriteLocal toEntity(FavoriteLocalsDto localDto) {
       FavouriteLocal locals= new FavouriteLocal();
       locals.setId(localDto.getId());
       locals.setVoteType(localDto.getVote());
       return locals;
    }

    @Override
    public FavoriteLocalsDto toDto(FavouriteLocal locals) {
        return FavoriteLocalsDto.builder()
                .id(locals.getId())
                .vote(locals.getVoteType())
                .build();
    }

    @Override
    public List<FavoriteLocalsDto> toDtoList(List<FavouriteLocal> allLocals) {
        List<FavoriteLocalsDto> localDtos = new ArrayList<>();
        allLocals.forEach(local ->
                localDtos.add(toDto(local))
        );
        return localDtos;
    }
}
