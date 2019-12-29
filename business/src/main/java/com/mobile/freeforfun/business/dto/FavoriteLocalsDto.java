package com.mobile.freeforfun.business.dto;

import com.mobile.freeforfun.persistence.enums.EVoteType;
import com.mobile.freeforfun.persistence.model.FavoriteLocalsCompositeKey;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@Builder
public class FavoriteLocalsDto {
    private FavoriteLocalsCompositeKey id;
    private EVoteType voteType;
}
