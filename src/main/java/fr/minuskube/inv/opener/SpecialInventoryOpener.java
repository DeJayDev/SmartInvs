package fr.minuskube.inv.opener;

import com.google.common.collect.ImmutableList;
import fr.minuskube.inv.InventoryManager;
import fr.minuskube.inv.SmartInventory;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class SpecialInventoryOpener implements InventoryOpener {

    private static final List<InventoryType> SUPPORTED = ImmutableList.of(
        InventoryType.FURNACE,
        InventoryType.WORKBENCH,
        InventoryType.SMOKER, // Furnace
        InventoryType.BLAST_FURNACE, // Also a furnace.
        InventoryType.DISPENSER,
        InventoryType.DROPPER,
        InventoryType.ENCHANTING,
        InventoryType.BREWING,
        InventoryType.ANVIL,
        InventoryType.BEACON,
        InventoryType.HOPPER,
        InventoryType.STONECUTTER, // Job Block
        InventoryType.SMITHING, // Job Block
        InventoryType.LOOM, // Job Block
        InventoryType.LECTERN, // Job Block
        InventoryType.GRINDSTONE // Job Block
    );

    @Override
    public Inventory open(SmartInventory inv, Player player) {
        InventoryManager manager = inv.getManager();
        Inventory handle = Bukkit.createInventory(player, inv.getType(), inv.getTitle());

        fill(handle, manager.getContents(player).get());

        player.openInventory(handle);
        return handle;
    }

    @Override
    public boolean supports(InventoryType type) {
        return SUPPORTED.contains(type);
    }

}
