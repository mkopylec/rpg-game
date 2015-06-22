package com.github.mkopylec.rpggame.application;

import com.github.mkopylec.ddd.buildingblocks.ApplicationService;
import com.github.mkopylec.rpggame.domain.world.Location;

import java.util.List;
import java.util.UUID;

@ApplicationService
public class PlayerActionService {

    public void moveHero(Location location) {

    }

    public List<DroppedItem> viewDroppedItems() {
        return null;
    }

    public List<DroppedItem> viewBackpackItems() {
        return null;
    }

    public void pickUpDroppedItem(UUID itemId) {

    }

    public void useItem(UUID itemId) {

    }
}
