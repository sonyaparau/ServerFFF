package com.mobile.freeforfun.business.dto;

import com.mobile.freeforfun.persistence.enums.EVoteType;
import com.mobile.freeforfun.persistence.model.FavouriteLocalCompositeKey;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@Builder
public class FavoriteLocalsDto {

    private FavouriteLocalCompositeKey id;
    private EVoteType voteType;
}
