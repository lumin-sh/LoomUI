package sh.lumin.loom.ui

import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import sh.lumin.loom.debug.DebugIcon
import sh.lumin.loom.debug.DebugLogger
import sh.lumin.loom.ui.elements.Button
import sh.lumin.loom.ui.elements.Element
import sh.lumin.loom.ui.elements.Slot

fun gui(title: String, inventoryType: InventorySize, init: GUI.() -> Unit): GUI {
    val gui = GUI(title, inventoryType)
    gui.init()
    return gui
}

/**
 * Represents a GUI containing all the elements
 * @param title The title of the GUI.
 * @param inventoryType The size of the inventory.
 */
class GUI(title: String, inventoryType: InventorySize) {
    val elements: MutableMap<Int, Element> = mutableMapOf()
    private val inventory: Inventory = Bukkit.createInventory(null, inventoryType.size, MiniMessage.miniMessage().deserialize(title))
    //
    var onCloseListener: (() -> Unit)? = null
    var onOpenListener: (() -> Unit)? = null

    /**
     * Defines a state within the GUI.
     * @param initialValue The initial value of the state.
     * @return Pair of functions: one to get the current state, and one to set the state.
     */
    fun <T> useState(initialValue: T): Pair<() -> T, (T) -> Unit> {
        var state = initialValue
        val getState: () -> T = { state }
        val setState: (T) -> Unit = {
            value -> state = value
            //
            DebugLogger.log(DebugIcon.LIGHTBULB, "State changed, calling render.")
            //
            render()
        }
        return getState to setState
    }

    /**
     * Sets a listener to be invoked when the GUI is opened.
     * @param listener The listener to be invoked.
     */
    fun onOpen(listener: () -> Unit) {
        onOpenListener = listener
    }

    /**
     * Sets a listener to be invoked when the GUI is closed.
     * @param listener The listener to be invoked.
     */
    fun onClose(listener: () -> Unit) {
        onCloseListener = listener
    }

    /**
     * Adds a button element to the GUI.
     * @param index The index where the button should be placed.
     * @param init Function to initialize the button.
     * @see Button
     */
    fun button(index: Int, init: Button.() -> Unit) {
        val button = Button()
        button.init()
        elements[index] = (button)
    }

    /**
     * Adds a slot element to the GUI.
     * @param index The index where the slot should be placed.
     * @param init Function to initialize the slot.
     * @see Slot
     */
    fun slot(index: Int, init: Slot.() -> Unit) {
        val slot = Slot()
        slot.init()
        elements[index] = (slot)
    }

    /**
     * Opens the GUI for the specified player.
     * @param player The player for whom the GUI should be opened.
     */
    fun openFor(player: Player) {
        render()
        DebugLogger.log(DebugIcon.TOOLS, "Opening GUI for player: ${player.name}")
        UIManager.registerGUIOpen(this, player)
        player.openInventory(inventory)
    }

    /**
     * Renders the GUI by updating its elements in the inventory.
     * Should not attempt to call this manually.
     * can be done with "val (_, render) = useState(0); render(0);"
     */
    private fun render() {
        DebugLogger.logTiming(DebugIcon.REFRESH, "Rendered GUI in") {
            // clear UI
            inventory.clear()
            // add all elements
            elements.forEach { (index, element) ->
                if(element is Slot) {
                    inventory.setItem(index, element.invokeItem())
                }
            }
        }
    }
}