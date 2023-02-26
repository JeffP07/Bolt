package org.popcraft.bolt.matcher.block;

import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.EntityType;
import org.popcraft.bolt.matcher.Match;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

public class SaplingMatcher implements BlockMatcher {
    private boolean enabled;

    @Override
    public void initialize(Set<Material> protectableBlocks, Set<EntityType> protectableEntities) {
        enabled = protectableBlocks.stream().anyMatch(material -> Tag.SAPLINGS.isTagged(material) || Material.BAMBOO_SAPLING.equals(material));
    }

    @Override
    public boolean enabled() {
        return enabled;
    }

    @Override
    public boolean canMatch(Block block) {
        return enabled;
    }

    @Override
    public Optional<Match> findMatch(Block block) {
        final Block above = block.getRelative(BlockFace.UP);
        if (Tag.SAPLINGS.isTagged(above.getType()) || Material.BAMBOO_SAPLING.equals(above.getType())) {
            return Optional.of(Match.ofBlocks(Collections.singleton(above)));
        }
        return Optional.empty();
    }
}
