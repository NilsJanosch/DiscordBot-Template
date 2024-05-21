package xyz.dev.truthy.event.impl

import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent
import net.dv8tion.jda.api.interactions.commands.OptionType
import net.dv8tion.jda.api.interactions.commands.build.OptionData

open class EventListener(val name: String, val description: String) {
    var isAdminCommand: Boolean = false
    private val optionData: MutableList<OptionData> = ArrayList()
    private val buttonIds: MutableList<String> = ArrayList()
    private val modalIds: MutableList<String> = ArrayList()

    init {
        setup()
    }

    open fun onSlashCommandInteraction(event: SlashCommandInteractionEvent?) {
        /*
         * Used for the children that extend this class.
         */
    }

    fun onButtonInteraction(event: ButtonInteractionEvent?) {
        /*
         * Used for the children that extend this class.
         */
    }

    fun onModalInteraction(event: ModalInteractionEvent?) {
        /*
         * Used for the children that extend this class.
         */
    }

    fun setup() {
        /*
         * Used for the children that extend this class.
         */
    }

    fun addOption(optionType: OptionType?, name: String?, description: String?, required: Boolean) {
        val oD = OptionData(optionType!!, name!!, description!!, required)
        optionData.add(oD)
    }

    fun addOption(optionData: OptionData) {
        this.optionData.add(optionData)
    }

    fun addButtonId(id: String) {
        buttonIds.add(id)
    }

    fun addModalId(id: String) {
        modalIds.add(id)
    }

    fun getOptionData(): List<OptionData> {
        return optionData
    }

    fun getModalIds(): List<String> {
        return modalIds
    }

    fun getButtonIds(): List<String> {
        return buttonIds
    }
}