package com.mobile.freeforfun.business.restControllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mobile.freeforfun.business.dto.FavoriteLocalsDto;
import com.mobile.freeforfun.business.service.VoteService;
import com.mobile.freeforfun.business.utils.ApiEndpoints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin
@RequestMapping(ApiEndpoints.APPLICATION_NAME)
public class VoteController {
    private VoteService voteService;

    @Autowired
    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping(value = ApiEndpoints.UPVOTE,
            produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<FavoriteLocalsDto> upVote(@PathVariable("localId") Long localId, @PathVariable("userId") Long userId) {
        return new ResponseEntity<>(voteService.upVote(localId, userId), HttpStatus.OK);
    }

    @PostMapping(value = ApiEndpoints.DOWNVOTE,
            produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<FavoriteLocalsDto> downVote(@PathVariable("localId") Long localId, @PathVariable("userId") Long userId) {
        return new ResponseEntity<>(voteService.downVote(localId, userId), HttpStatus.OK);
    }

    @PostMapping(value = ApiEndpoints.GET_LOCAL,
            produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getLocal(@PathVariable("localId") Long localId, @PathVariable("userId") Long userId) {
        Gson gson = new GsonBuilder().create();
        FavoriteLocalsDto locals = voteService.getLocal(localId, userId);
        String response = gson.toJson(locals);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PostMapping(value = ApiEndpoints.DELETE_VOTE,
            produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<FavoriteLocalsDto> deleteVote(@PathVariable("localId") Long localId, @PathVariable("userId") Long userId) {
        return new ResponseEntity<FavoriteLocalsDto>(voteService.deleteVote(localId,userId),HttpStatus.OK);
    }

    @PostMapping(value = ApiEndpoints.GET_ALL_VOTED_LOCALS,
            produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getAllLocals(){
        Gson gson = new GsonBuilder().create();
        List<FavoriteLocalsDto> locals =voteService.getAll();
        String response = gson.toJson(locals);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
