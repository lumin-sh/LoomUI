package sh.lumin.loom

import org.bukkit.plugin.java.JavaPlugin


/**
 * Represents the Loom user interface.
 *
 * @param javaPlugin The JavaPlugin instance associated with the LoomUI.
 */
class LoomUI(javaPlugin: JavaPlugin) {

    init {
        plugin = javaPlugin
        initialized = true
    }

    companion object {
        lateinit var plugin: JavaPlugin
        var initialized = false
        var debugMode = false
    }

    fun debugMode() { debugMode = true }
}

/**
 * Creates and initializes a LoomUI instance.
 *
 * @param plugin The JavaPlugin instance associated with the LoomUI.
 * @param init An optional lambda function for initializing the LoomUI.
 * @return The initialized LoomUI instance.
 */
fun loomUI(plugin: JavaPlugin, init: LoomUI.() -> Unit = {}): LoomUI = LoomUI(plugin).apply(init)
