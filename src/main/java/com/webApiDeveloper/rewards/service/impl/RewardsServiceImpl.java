package com.webApiDeveloper.rewards.service.impl;

import com.webApiDeveloper.rewards.service.RewardsService;
import com.webApiDeveloper.rewards.util.CsvUtil;
import com.webApiDeveloper.rewards.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RewardsServiceImpl implements RewardsService {

    private final CsvUtil csvUtil;
    private final DateUtil dateUtil;

    @Autowired
    public RewardsServiceImpl(CsvUtil csvUtil, DateUtil dateUtil) {
        this.csvUtil = csvUtil;
        this.dateUtil = dateUtil;
    }

    //Mehthod to generate reward report
    @Override
    public Object getRewardsReport() {

        List<String[]> sampleData = csvUtil.getData();

        Map<String,Integer> result = new HashMap<>();

        int totalPoints = 0;

        //remove the description part from data list
        sampleData.remove(0);

        for (String[] eachTransaction: sampleData
             ) {
            String monthName = dateUtil.getMonthName(eachTransaction[2]);
            int rewardPoint = calculatePoint(eachTransaction[1]);
            if (!result.containsKey(monthName)){result.put(monthName,rewardPoint);}
            else {result.put(monthName,result.get(monthName)+rewardPoint);}
            totalPoints+=rewardPoint;
        }
        result.put("Total",totalPoints);
        return result;
    }

    //Method to calculate reward point
    private int calculatePoint(String amount){
        int amt = Integer.parseInt(amount);
        if (amt<=50) return 0;
        if (amt<=100) return amt-50;
        return 50+(amt-100)*2;
    }
}
