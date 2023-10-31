package com.wrrulos.mcptool.listeners;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.player.GameProfileRequestEvent;
import com.wrrulos.mcptool.commands.ChangeUUID;
import com.wrrulos.mcptool.commands.ChangeUsername;
import com.velocitypowered.api.util.GameProfile;

import java.util.UUID;

public class HandleGameProfileRequest {
    // Subscribe to the GameProfileRequestEvent
    @Subscribe
    public void onGameProfileRequest(GameProfileRequestEvent event) {
        // Initialize variables to store username and UUID
        String username;
        UUID uuid;

        // Get the fake username and UUID from other classes
        String fakeUsername = ChangeUsername.username;
        UUID fakeUuid = ChangeUUID.uuid;

        // Get the original GameProfile from the event
        GameProfile gameProfile = event.getGameProfile();

        // Check if a fake username is provided, use it; otherwise, use the original username
        if (!fakeUsername.isEmpty()) {
            username = fakeUsername;
        } else {
            username = gameProfile.getName();
        }

        // Check if a fake UUID is provided, use it; otherwise, use the original UUID
        if (fakeUuid != null) {
            uuid = fakeUuid;
        } else {
            uuid = gameProfile.getId();
        }

        // Create a new GameProfile with the adjusted username and UUID
        gameProfile = gameProfile.withId(uuid).withName(username);

        // Set the modified GameProfile back to the event
        event.setGameProfile(gameProfile);
    }
}
