package xyz.dev.truthy

import io.github.cdimascio.dotenv.Dotenv
import net.dv8tion.jda.api.OnlineStatus
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.requests.GatewayIntent
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder
import net.dv8tion.jda.api.sharding.ShardManager
import xyz.dev.truthy.event.EventHandler

object Main {
    private var shardManager: ShardManager? = null
    private var dotenv: Dotenv? = null

    private fun start() {
        dotenv = Dotenv.configure().ignoreIfMissing().load()
        val builder = DefaultShardManagerBuilder.createDefault(dotenv!!["BOT_TOKEN"])
        builder.setStatus(OnlineStatus.ONLINE)
        builder.enableIntents(GatewayIntent.GUILD_MEMBERS)
        builder.enableIntents(GatewayIntent.MESSAGE_CONTENT)
        builder.setActivity(Activity.watching("Activity")) //FIXME: Set your activity here
        shardManager = builder.build()
        println("Bot started")

        shardManager!!.addEventListener(EventHandler())
    }

    @JvmStatic
    fun main(args: Array<String>) {
        start()
    }
}