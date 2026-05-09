package me.anti.vault;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.Vault;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class NoOminousVault extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("NoOminousVault enabled");
    }

    @EventHandler
    public void onChunkLoad(ChunkLoadEvent event) {
        Chunk chunk = event.getChunk();

        for (int x = 0; x < 16; x++) {
            for (int y = -64; y < 320; y++) {
                for (int z = 0; z < 16; z++) {

                    Block block = chunk.getBlock(x, y, z);

                    if (block.getType() == Material.VAULT) {
                        BlockData data = block.getBlockData();

                        if (data instanceof Vault vault) {
                            if (vault.isOminous()) {
                                block.setType(Material.AIR);
                            }
                        }
                    }
                }
            }
        }
    }
}
