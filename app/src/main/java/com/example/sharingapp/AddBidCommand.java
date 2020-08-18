package com.example.sharingapp;

import android.content.Context;

import java.util.concurrent.ExecutionException;

/**
 * Command to add a bid
 */
public class AddBidCommand extends Command {

    //private BidList bid_list;
    private Bid bid;
    //private Context context;

    public AddBidCommand(Bid bid) {
        //this.bid_list = bid_list;
        this.bid = bid;
        //this.context = context;
    }

    // Save bid locally
    public void execute(){
        ElasticSearchManager.AddBidTask add_bid_task = new ElasticSearchManager.AddBidTask();
        add_bid_task.execute(bid);
//        super.setIsExecuted(bid_list.saveBids(context));
        try {
            if(add_bid_task.get()) {
                super.setIsExecuted(true);
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            super.setIsExecuted(false);
        }
    }
}
