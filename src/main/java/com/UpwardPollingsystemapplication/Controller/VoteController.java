package com.UpwardPollingsystemapplication.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.UpwardPollingsystemapplication.DTO.VoteRequest;
import com.UpwardPollingsystemapplication.service.VoteService;

import jakarta.servlet.http.HttpSession;

@Controller
public class VoteController {

	@Autowired
	private VoteService voteService;
	@PostMapping("/submit-vote")
	@ResponseBody
	public String submitVote(@RequestBody VoteRequest voteRequest, HttpSession session) {
	    // Check if the user is logged in
	    System.out.println("VoteController: Received vote data");
	    System.out.println("Vote ID: " + voteRequest.getVoteId());
	    System.out.println("Vote Name: " + voteRequest.getVoteName());

	    try {
	        // Call service to process the vote
	        voteService.recordVote(voteRequest.getVoteId());
	        return "success";
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "error";
	    }
	}

	@GetMapping("/getresult")
	public String getAllvotesdetails(Model model) {
		model.addAttribute("resiltvotes", voteService.geetALl());
		return "viewresult";
	}
}
