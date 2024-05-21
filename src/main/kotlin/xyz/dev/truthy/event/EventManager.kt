package xyz.dev.truthy.event.impl

import xyz.dev.truthy.impl.command.ExampleCommand

class EventManager private constructor() {
    private val eventListeners: MutableList<EventListener> = ArrayList<EventListener>()

    init {
        registerAll()
    }

    private fun registerAll() {
        register(ExampleCommand())

        for (eventListener in eventListeners) {
            System.out.println("Registered command: " + eventListener.name)
        }
    }

    private fun register(eventListener: EventListener) {
        eventListeners.add(eventListener)
    }

    fun getEventListeners(): List<EventListener> {
        return eventListeners
    }

    companion object {
        val instance: EventManager = EventManager()
    }
}