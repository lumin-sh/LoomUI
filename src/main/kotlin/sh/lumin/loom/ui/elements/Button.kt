package sh.lumin.loom.ui.elements

import org.bukkit.event.inventory.InventoryClickEvent

/**
 * A class representing a button in an inventory slot.
 * @see Slot
 */
class Button : Slot() {
    private var onClickListener: ((InventoryClickEvent) -> Unit)? = null

    /**
     * Sets the click listener for this button.
     *
     * @param listener The lambda function to be invoked when the button is clicked.
     */
    fun onClick(listener: (InventoryClickEvent) -> Unit) {
        onClickListener = listener
    }

    /**
     * Handles the click event for this button.
     *
     * @param event The InventoryClickEvent representing the click event.
     */
    fun click(event: InventoryClickEvent) {
        onClickListener?.invoke(event)
    }
}