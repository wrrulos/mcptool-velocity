package com.wrrulos.mcptool.commands;


import com.velocitypowered.api.command.RawCommand;
import com.wrrulos.mcptool.utils.MessageUtil;

import java.util.UUID;

public class ChangeUUID implements RawCommand {
    // A static UUID to store the new UUID
    public static UUID uuid = null;

    public void execute(RawCommand.Invocation invocation) {
        // Split the command arguments into an array
        String[] args = ((String)invocation.arguments()).split(" ");

        // Define various messages for user feedback
        String prefix = "<dark_gray>[<dark_purple>!<dark_gray>] ";
        String missingArgument = prefix + "<red>You must enter a UUID!";
        String invalidUUID = prefix + "<red>The entered UUID is invalid!";
        String newUUIDMessage = "<dark_gray>[<dark_purple>SPOOFING<dark_gray>] <white>Now you will enter with the UUID <green>" + args[0];

        // Check if the command arguments are empty
        if (((String)invocation.arguments()).isEmpty()) {
            invocation.source().sendMessage(MessageUtil.createMessage(missingArgument));
            return;
        }

        try {
            // Try to parse the input string as a UUID
            uuid = UUID.fromString(args[0]);

            // Notify the user about the new UUID
            invocation.source().sendMessage(MessageUtil.createMessage(newUUIDMessage));
        } catch (IllegalArgumentException e) {
            // Handle the case where the entered string is not a valid UUID
            invocation.source().sendMessage(MessageUtil.createMessage(invalidUUID));
        }
    }
}

