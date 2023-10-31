package com.wrrulos.mcptool.commands;

import com.velocitypowered.api.command.RawCommand;
import com.wrrulos.mcptool.utils.MessageUtil;

public class ChangeUsername implements RawCommand {
    // A static string to store the new username
    public static String username = "";

    public void execute(RawCommand.Invocation invocation) {
        // Define messages for user feedback
        String newNameMessage = "<dark_gray>[<dark_purple>SPOOFING<dark_gray>] <white>Now you will enter with the name <green>" + (String)invocation.arguments();
        String emptyNameMessage = "<dark_gray>[<red>!<dark_gray>] <white>You entered an empty name! Are you sure?";

        // Check if the command arguments are empty
        if (((String)invocation.arguments()).isEmpty()) {
            // Inform the user about the new name and the empty name warning
            invocation.source().sendMessage(MessageUtil.createMessage(newNameMessage));
            invocation.source().sendMessage(MessageUtil.createMessage(emptyNameMessage));
            return;
        }

        // Set the new username
        username = (String)invocation.arguments();
        // Notify the user about the new name
        invocation.source().sendMessage(MessageUtil.createMessage(newNameMessage));
    }
}
