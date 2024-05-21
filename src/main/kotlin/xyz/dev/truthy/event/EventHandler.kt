package xyz.dev.truthy.event

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.Permission
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent
import net.dv8tion.jda.api.events.session.ReadyEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import net.dv8tion.jda.api.interactions.commands.build.CommandData
import net.dv8tion.jda.api.interactions.commands.build.Commands
import xyz.dev.truthy.event.impl.EventManager
import java.awt.Color
import java.util.*

class EventHandler : ListenerAdapter() {
    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent) {
        val command = event.name


        for (eventListener in EventManager.instance.getEventListeners()) {
            if (eventListener.name.equals(command, ignoreCase = true)) {
                if (eventListener.isAdminCommand) {
                    if (!event.member!!.permissions.contains(Permission.ADMINISTRATOR)) {
                        val embedBuilder = EmbedBuilder()
                            .setColor(Color(0xEC5353))
                            .setTitle("❌ - Permission Denied")
                            .setDescription("This command is only available to Discord Administrators.")
                        event.replyEmbeds(embedBuilder.build()).setEphemeral(true).queue()
                        return
                    }
                }
                eventListener.onSlashCommandInteraction(event)
            }
        }
    }

    override fun onButtonInteraction(event: ButtonInteractionEvent) {
        for (eventListener in EventManager.instance.getEventListeners()) {
            for (id in eventListener.getButtonIds()) {
                if (Objects.requireNonNull(event.button.id).equals(id, ignoreCase = true)) {
                    eventListener.onButtonInteraction(event)
                    return
                }
            }
        }
    }

    override fun onModalInteraction(event: ModalInteractionEvent) {
        for (eventListener in EventManager.instance.getEventListeners()) {
            for (id in eventListener.getModalIds()) {
                if (event.modalId.equals(id, ignoreCase = true)) {
                    eventListener.onModalInteraction(event)
                    return
                }
            }
        }
    }

    override fun onReady(event: ReadyEvent) {
        val commandData: MutableList<CommandData> = ArrayList()
        for (eventListener in EventManager.instance.getEventListeners()) {
            if (eventListener.getOptionData().isEmpty()) {
                commandData.add(Commands.slash(eventListener.name.lowercase(Locale.getDefault()), eventListener.description))
            } else {
                commandData.add(
                    Commands.slash(eventListener.name, eventListener.description)
                        .addOptions(eventListener.getOptionData())
                )
            }
        }

        event.jda.updateCommands().addCommands(commandData).queue()
    }
}