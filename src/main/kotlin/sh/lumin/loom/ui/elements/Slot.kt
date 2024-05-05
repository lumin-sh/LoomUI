package sh.lumin.loom.ui.elements

import org.bukkit.inventory.ItemStack

/**
 * A class representing a slot in an inventory.
 * @see Element
 */
open class Slot : Element() {
    private var itemSupplier: (() -> ItemStack)? = null

    /**
     * Sets the supplier for the item in this slot.
     *
     * @param item The lambda function supplying the ItemStack for this slot.
     */
    fun item(item: () -> ItemStack) {
        itemSupplier = item
    }

    /**
     * Invokes the item supplier to get the ItemStack for this slot.
     *
     * @return The ItemStack supplied by the item supplier, or null if not available.
     */
    fun invokeItem(): ItemStack? {
        return itemSupplier?.invoke()
    }
}