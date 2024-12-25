package com.UpwardPollingsystemapplication.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.UpwardPollingsystemapplication.Entity.Vote;
import com.UpwardPollingsystemapplication.repository.VoteRepo;

@Service
public class VoteService {

	@Autowired
	private VoteRepo repo;
	public List<String> getAllNames() {
		return repo.findAllPartnames();
		
	}
	public List<Vote> geetALl(){
		return repo.findAll();
	}
	
    public void recordVote(Long voteId) {
        // Add logic to save the vote
        Optional<Vote> voteOptional = repo.findById(voteId);
        if (voteOptional.isPresent()) {
            Vote vote = voteOptional.get();
            vote.setCount(vote.getCount() + 1); // Increment the vote count
            repo.save(vote);
        } else {
            throw new RuntimeException("Vote ID not found");
        }
    }
}
