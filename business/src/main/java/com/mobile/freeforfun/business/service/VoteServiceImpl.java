package com.mobile.freeforfun.business.service;

import com.mobile.freeforfun.business.dto.FavoriteLocalsDto;
import com.mobile.freeforfun.business.mapper.VoteMapper;
import com.mobile.freeforfun.persistence.enums.EVoteType;
import com.mobile.freeforfun.persistence.model.FavouriteLocal;
import com.mobile.freeforfun.persistence.model.FavouriteLocalCompositeKey;
import com.mobile.freeforfun.persistence.model.Local;
import com.mobile.freeforfun.persistence.model.User;
import com.mobile.freeforfun.persistence.repo.FavoriteLocalsRepository;
import com.mobile.freeforfun.persistence.repo.LocalRepository;
import com.mobile.freeforfun.persistence.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VoteServiceImpl implements VoteService {

    private FavoriteLocalsRepository favoriteLocalsRepository;
    private UserRepository userRepository;
    private LocalRepository localRepository;
    private VoteMapper voteMapper;

    @Autowired
    public VoteServiceImpl(FavoriteLocalsRepository favoriteLocalsRepository, UserRepository userRepository,
            LocalRepository localRepository, VoteMapper voteMapper) {
        this.favoriteLocalsRepository = favoriteLocalsRepository;
        this.userRepository = userRepository;
        this.localRepository = localRepository;
        this.voteMapper = voteMapper;
    }

    @Override
    public FavoriteLocalsDto upVote(Long localId, Long userId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Local> local = localRepository.findById(localId);
        if(local.isPresent() && user.isPresent()){
            FavouriteLocal vote = new FavouriteLocal();
            vote.setId(new FavouriteLocalCompositeKey(localId, userId));
            vote.setLocal(local.get());
            vote.setUser(user.get());
            vote.setVoteType(EVoteType.UPVOTE);
            return voteMapper.toDto(favoriteLocalsRepository.save(vote));
        }
        return null;
    }

    @Override
    public FavoriteLocalsDto downVote(Long localId, Long userId) {
        Optional<Local> local = localRepository.findById(localId);
        Optional<User> user = userRepository.findById(userId);
        if(local.isPresent() && user.isPresent()) {
            FavouriteLocal vote = new FavouriteLocal();
            vote.setId(new FavouriteLocalCompositeKey(localId, userId));
            vote.setLocal(local.get());
            vote.setUser(user.get());
            vote.setVoteType(EVoteType.DOWNVOTE);
            return voteMapper.toDto(favoriteLocalsRepository.save(vote));
        }
        return null;
    }

    @Override
    public List<FavouriteLocal> getAllVotesOfALocal(Long localId) {
        List<FavouriteLocal> allVotes = favoriteLocalsRepository.findAll();
        List<FavouriteLocal> allLocalsVotes = new ArrayList<>();
        allVotes.forEach(voteEntity -> {
            if (localId.equals(voteEntity.getId().getLocalId())) {
                allLocalsVotes.add(voteEntity);
            }
        });
        return allLocalsVotes;
    }

    @Override
    public List<User> getAllUsersWhoVotedALocal(Long localId) {
        List<FavouriteLocal> allVotes = favoriteLocalsRepository.findAll();
        List<User> allUsersWhoVoted = new ArrayList<>();
        allVotes.forEach(voteEntity -> {
            if (localId.equals(voteEntity.getId().getLocalId())) {
                Long userId=voteEntity.getId().getUserId();
                User userEntity=new User();
                userEntity.setId(userId);
                allUsersWhoVoted.add(userEntity);
            }
        });
        return allUsersWhoVoted;
    }
}
