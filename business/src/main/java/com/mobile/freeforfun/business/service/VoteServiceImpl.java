package com.mobile.freeforfun.business.service;

import com.mobile.freeforfun.business.dto.FavoriteLocalsDto;
import com.mobile.freeforfun.business.mapper.VoteMapper;
import com.mobile.freeforfun.business.mapper.VoteMapperImpl;
import com.mobile.freeforfun.persistence.enums.EVoteType;
import com.mobile.freeforfun.persistence.model.FavouriteLocals;
import com.mobile.freeforfun.persistence.model.Local;
import com.mobile.freeforfun.persistence.model.User;
import com.mobile.freeforfun.persistence.model.FavoriteLocalsCompositeKey;
import com.mobile.freeforfun.persistence.repo.FavoriteLocalsRepository;
import com.mobile.freeforfun.persistence.repo.LocalRepository;
import com.mobile.freeforfun.persistence.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class VoteServiceImpl implements VoteService {

    private FavoriteLocalsRepository favoriteLocalsRepository;
    private UserRepository userRepository;
    private LocalRepository localRepository;
    private VoteMapper voteMapper;

    @Autowired
    public VoteServiceImpl(FavoriteLocalsRepository favoriteLocalsRepository, UserRepository userRepository, LocalRepository localRepository, VoteMapper voteMapper) {
        this.favoriteLocalsRepository = favoriteLocalsRepository;
        this.userRepository = userRepository;
        this.localRepository = localRepository;
        this.voteMapper = voteMapper;
    }

    @Override
    public FavoriteLocalsDto upVote(Long localId, Long userId) {

//        Local local = localRepository.getOne(localId);
//        User user = userRepository.getOne(userId);
        FavouriteLocals vote = new FavouriteLocals(new FavoriteLocalsCompositeKey(localId, userId),EVoteType.UPVOTE);
        return voteMapper.toDto(favoriteLocalsRepository.save(vote));
    }

    @Override
    public FavoriteLocalsDto downVote(Long localId, Long userId) {
//        Local local = localRepository.getOne(localId);
//        User user = userRepository.getOne(userId);
        FavouriteLocals vote = new FavouriteLocals(new FavoriteLocalsCompositeKey(localId, userId),EVoteType.DOWNVOTE);
        return voteMapper.toDto(favoriteLocalsRepository.save(vote));
    }

    @Override
    public List<FavouriteLocals> getAllVotesOfALocal(Long localId) {
        List<FavouriteLocals> allVotes = favoriteLocalsRepository.findAll();
        List<FavouriteLocals> allLocalsVotes = new ArrayList<>();

        allVotes.forEach(voteEntity -> {
            if (localId == voteEntity.getId().getLocalId()) {
                allLocalsVotes.add(voteEntity);
            }
        });
        return allLocalsVotes;
    }

    @Override
    public List<User> getAllUsersWhoVotedALocal(Long localId) {
        List<FavouriteLocals> allVotes = favoriteLocalsRepository.findAll();
        List<User> allUsersWhoVoted = new ArrayList<>();

        allVotes.forEach(voteEntity -> {
            if (localId == voteEntity.getId().getLocalId()) {
                Long userId=voteEntity.getId().getUserId();
                User userEntity=new User();
                userEntity.setId(userId);
                allUsersWhoVoted.add(userEntity);
            }
        });
        return allUsersWhoVoted;
    }

}
