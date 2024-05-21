package xyz.dev.truthy.impl.command

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import xyz.dev.truthy.event.impl.EventListener

class ExampleCommand : EventListener("example", "Example command") {
    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent?) {
        event!!.reply("Example command").queue()
    }
}