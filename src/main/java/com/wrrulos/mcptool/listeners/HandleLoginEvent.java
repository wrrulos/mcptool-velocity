package com.wrrulos.mcptool.listeners;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.LoginEvent;
import com.velocitypowered.api.proxy.Player;
import com.wrrulos.mcptool.utils.MessageUtil;

public class HandleLoginEvent {
    // Subscribe to the LoginEvent
    @Subscribe
    public void onLogin(LoginEvent event) {
        // Get the player from the event
        Player player = event.getPlayer();

        // Get the username and UUID of the player
        String username = player.getUsername();
        String playerUUID = player.getUniqueId().toString();

        // Create a message with the player's username and UUID
        String message = "\n<white>Username: <green>" + username + "\n<white>UUID: <aqua>" + playerUUID + "\n";

        // Send the message to the player
        player.sendMessage(MessageUtil.createMessage(message));
    }
}