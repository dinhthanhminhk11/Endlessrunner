package com.example.endlessrunner.bll.managers;

import com.example.endlessrunner.be.Floor;
import com.example.endlessrunner.be.Player;

public class GravityManager {

    public boolean isPlayerNotTouchingFloor(Player player, Floor floor){
        int playerBottom = player.getRect().bottom;
        int floorTop = floor.getRect().top;

        return playerBottom < floorTop;
    }
}

