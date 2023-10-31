package com.wrrulos.mcptool;

import com.google.inject.Inject;
import com.velocitypowered.api.command.Command;
import com.velocitypowered.api.command.CommandManager;
import com.velocitypowered.api.event.EventManager;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import com.wrrulos.mcptool.commands.ChangeUUID;
import com.wrrulos.mcptool.commands.ChangeUsername;
import com.wrrulos.mcptool.commands.Connect;
import com.wrrulos.mcptool.listeners.HandleGameProfileRequest;
import com.wrrulos.mcptool.listeners.HandleLoginEvent;
import org.slf4j.Logger;

@Plugin(
        id = "mcptool",
        name = "MCPTool-Velocity",
        version = BuildConstants.VERSION
)

public class MCPToolVelocity {

    // A reference to the Velocity ProxyServer
    private static ProxyServer server;

    // Logger for plugin messages
    private Logger logger;

    @Inject
    public MCPToolVelocity(ProxyServer server, Logger logger) {
        // Initialize the server and logger
        MCPToolVelocity.server = server;
        this.logger = logger;
    }

    // Get the Velocity ProxyServer instance
    public static ProxyServer getServer() {
        return server;
    }

    // Register commands and event listeners on plugin initialization
    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        CommandManager commandManager = server.getCommandManager();
        EventManager eventManager = server.getEventManager();

        // Register custom commands and event listeners
        commandManager.register("username", (Command)new ChangeUsername(), new String[0]);
        commandManager.register("uuid", (Command)new ChangeUUID(), new String[0]);
        commandManager.register("connect", (Command)new Connect(), new String[0]);
        eventManager.register(this, new HandleLoginEvent());
        eventManager.register(this, new HandleGameProfileRequest());

        // Log successful plugin loading
        this.logger.info("The MCPTool plugin loaded successfully.");
    }
}
