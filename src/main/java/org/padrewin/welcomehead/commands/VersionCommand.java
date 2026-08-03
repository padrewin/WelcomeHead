package org.padrewin.welcomehead.commands;

import java.util.Collections;
import java.util.List;
import org.padrewin.welcomehead.WelcomeHead;
import org.padrewin.welcomehead.manager.CommandManager;
import org.padrewin.welcomehead.manager.LocaleManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class VersionCommand extends BaseCommand {

    public VersionCommand() {
        super("version", CommandManager.CommandAliases.VERSION);
    }

    @Override
    public void execute(WelcomeHead plugin, CommandSender sender, String[] args) {
        sendInfo(plugin, sender);
    }

    @Override
    public List<String> tabComplete(WelcomeHead plugin, CommandSender sender, String[] args) {
        return Collections.emptyList();
    }

    public static void sendInfo(WelcomeHead plugin, CommandSender sender) {
        LocaleManager localeManager = plugin.getManager(LocaleManager.class);

        String baseColor = localeManager.getLocaleMessage("base-command-color");
        localeManager.sendCustomMessage(sender, baseColor + "");
        localeManager.sendCustomMessage(sender, baseColor + "Running <g:#635AA7:#E6D4F8:#9E48F6>WelcomeHead" + baseColor + " v" + plugin.getDescription().getVersion());

        List<String> authors = plugin.getDescription().getAuthors();
        String authorsFormatted = String.join(", ", authors);

        localeManager.sendCustomMessage(sender, baseColor + "&7Developer: <g:#FF0000:#793434>" + authorsFormatted);

        if (sender instanceof Player) {
            Player player = (Player) sender;

            Component clickableText = Component.text("click here")
                    .color(NamedTextColor.RED)
                    .decorate(TextDecoration.UNDERLINED)
                    .clickEvent(ClickEvent.openUrl("https://github.com/Cold-Development/WelcomeHead"));
            Component baseMessage = LegacyComponentSerializer.legacySection().deserialize(baseColor + "GitHub: ")
                    .append(clickableText);

            player.sendMessage(baseMessage);

        } else if (sender instanceof ConsoleCommandSender) {
            String ansiRed = "\u001B[31m";
            String ansiReset = "\u001B[0m";
            String ansiAqua = "\u001B[36m";

            sender.sendMessage(ansiAqua + "GitHub: " + ansiRed + "https://github.com/Cold-Development/WelcomeHead" + ansiReset);
        }

        localeManager.sendSimpleMessage(sender, "base-command-help");
        localeManager.sendCustomMessage(sender, baseColor + "");
    }


}