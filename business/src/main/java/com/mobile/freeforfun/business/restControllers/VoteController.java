package com.mobile.freeforfun.business.restControllers;

import com.mobile.freeforfun.business.dto.FavoriteLocalsDto;
import com.mobile.freeforfun.business.service.VoteService;
import com.mobile.freeforfun.business.utils.ApiEndpoints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = ApiEndpoints.UPVOTE,
            produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<FavoriteLocalsDto> upVote(@PathVariable("localId") Long localId, @PathVariable("userId") Long userId) {
        return new ResponseEntity<>(voteService.upVote(localId, userId), HttpStatus.OK);
    }

    @GetMapping(value = ApiEndpoints.DOWNVOTE,
            produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<FavoriteLocalsDto> downVote(@PathVariable("localId") Long localId, @PathVariable("userId") Long userId) {
        return new ResponseEntity<>(voteService.downVote(localId, userId), HttpStatus.OK);
    }

}
