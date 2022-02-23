package com.webApiDeveloper.rewards.controller;

import com.webApiDeveloper.rewards.dtos.ApiResponse;
import com.webApiDeveloper.rewards.service.RewardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RewardsRestController {

    private final RewardsService rewardsService;

    @Autowired
    public RewardsRestController(RewardsService rewardsService) {
        this.rewardsService = rewardsService;
    }

    @GetMapping()
    public ResponseEntity<?> getPoints(){
        try {
            return new ResponseEntity<>(new ApiResponse("Success","Data fetched successfully",rewardsService.getRewardsReport()), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(new ApiResponse("Failed","Something went wrong",null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
