package com.mobile.freeforfun.business.service;

import com.mobile.freeforfun.business.dto.FavoriteLocalsDto;
import com.mobile.freeforfun.business.exceptions.BusinessException;
import com.mobile.freeforfun.business.mapper.VoteMapper;
import com.mobile.freeforfun.persistence.enums.EVoteType;
import com.mobile.freeforfun.persistence.model.FavouriteLocal;
import com.mobile.freeforfun.persistence.model.FavouriteLocalCompositeKey;
import com.mobile.freeforfun.persistence.model.Local;
import com.mobile.freeforfun.persistence.model.User;
import com.mobile.freeforfun.persistence.repo.FavoriteLocalRepository;
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

    private FavoriteLocalRepository favoriteLocalRepository;
    private UserRepository userRepository;
    private LocalRepository localRepository;
    private VoteMapper voteMapper;

    @Autowired
    public VoteServiceImpl(
			FavoriteLocalRepository favoriteLocalRepository, UserRepository userRepository,
            LocalRepository localRepository, VoteMapper voteMapper) {
        this.favoriteLocalRepository = favoriteLocalRepository;
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
            vote.setVoteType(EVoteType.UPVOTE);
            vote.setUser(user.get());
            return voteMapper.toDto(favoriteLocalRepository.save(vote));
        }
        return null;
    }

    @Override
    public FavoriteLocalsDto downVote(Long localId, Long userId) {
        Optional<Local> local = localRepository.findById(localId);
        Optional<User> user = userRepository.findById(userId);

        FavouriteLocalCompositeKey id = new FavouriteLocalCompositeKey(localId,userId);
        Optional favouriteLocal = favoriteLocalRepository.findById(id);

        if(local.isPresent() && user.isPresent()) {
            FavouriteLocal vote = new FavouriteLocal();
            vote.setId(new FavouriteLocalCompositeKey(localId, userId));
            vote.setLocal(local.get());
            vote.setUser(user.get());
            vote.setVoteType(EVoteType.DOWNVOTE);
            return voteMapper.toDto(favoriteLocalRepository.save(vote));
        }
        return null;
    }

    @Override
    public List<FavouriteLocal> getAllVotesOfALocal(Long localId) {
        List<FavouriteLocal> allVotes = favoriteLocalRepository.findAll();
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
        List<FavouriteLocal> allVotes = favoriteLocalRepository.findAll();
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

    @Override
    public FavoriteLocalsDto getLocal(Long localId, Long userId) {
        Optional<FavouriteLocal> local = favoriteLocalRepository.findById(new FavouriteLocalCompositeKey(localId,userId));
        if(local.isPresent())
            return voteMapper.toDto(local.get());
        return null;
    }

    @Override
    public FavoriteLocalsDto deleteVote(Long localId, Long userId) {
        Optional<FavouriteLocal> local = favoriteLocalRepository.findById(new FavouriteLocalCompositeKey(localId,userId));
        if(local.isPresent())
        {
            favoriteLocalRepository.deleteById(new FavouriteLocalCompositeKey(localId,userId));
            return voteMapper.toDto(local.get());
        }
        return null;
    }

    @Override
    public List<FavoriteLocalsDto> getAll() {
        return voteMapper.toDtoList(favoriteLocalRepository.findAll());
    }


}
