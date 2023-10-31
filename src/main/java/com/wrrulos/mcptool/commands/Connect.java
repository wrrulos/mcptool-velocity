package com.wrrulos.mcptool.commands;

import com.velocitypowered.api.command.RawCommand;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.server.RegisteredServer;
import com.velocitypowered.api.proxy.server.ServerInfo;
import com.wrrulos.mcptool.MCPToolVelocity;
import com.wrrulos.mcptool.utils.MessageUtil;

import java.net.InetSocketAddress;

public class Connect implements RawCommand {
    // Get the Velocity ProxyServer instance
    ProxyServer server = MCPToolVelocity.getServer();

    public void execute(RawCommand.Invocation invocation) {
        int port;
        // Split the command arguments into an array
        String[] args = ((String)invocation.arguments()).split(" ");
        Player player = (Player)invocation.source();

        // Define various messages for user feedback
        String prefix = "<dark_gray>[<dark_purple>!<dark_gray>] ";
        String missingArgument = prefix + "<red>You must enter a Server (IP:PORT)!";
        String invalidServer = prefix + "<red>The server entered is invalid!";
        String invalidPort = prefix + "<red>The entered port is invalid!";
        String connecting = "<dark_gray>[<green>+<dark_gray>] <white>Connecting to <green>" + args[0] + "..";

        // Check if the command arguments are empty
        if (((String)invocation.arguments()).isEmpty()) {
            invocation.source().sendMessage(MessageUtil.createMessage(missingArgument));
            return;
        }

        // Split the server address into host and port
        String[] addressParts = args[0].split(":");
        if (addressParts.length < 2) {
            invocation.source().sendMessage(MessageUtil.createMessage(invalidServer));
            return;
        }
        String host = addressParts[0];

        try {
            // Parse the port as an integer
            port = Integer.parseInt(addressParts[1]);
        } catch (NumberFormatException error) {
            invocation.source().sendMessage(MessageUtil.createMessage(invalidPort));
            return;
        }

        // Notify the user about the connection attempt
        invocation.source().sendMessage(MessageUtil.createMessage(connecting));

        // Create the InetSocketAddress for the server
        InetSocketAddress address = new InetSocketAddress(host, port);
        ServerInfo tempServerInfo = new ServerInfo("tempServer", address);
        RegisteredServer tempServer = this.server.registerServer(tempServerInfo);

        // Initiate a connection request for the player
        player.createConnectionRequest(tempServer).fireAndForget();

        // Unregister the temporary server information
        this.server.unregisterServer(tempServerInfo);
    }
}
