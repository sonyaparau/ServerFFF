package com.mobile.freeforfun.business.service;

import com.mobile.freeforfun.business.dto.FavoriteLocalsDto;
import com.mobile.freeforfun.persistence.model.FavouriteLocals;
import com.mobile.freeforfun.persistence.model.User;

import java.util.List;

public interface VoteService {

    FavoriteLocalsDto upVote(Long localId, Long userId);
    FavoriteLocalsDto downVote(Long localId, Long userId);

    List<FavouriteLocals> getAllVotesOfALocal(Long localId);
    List<User> getAllUsersWhoVotedALocal(Long localId);


}
