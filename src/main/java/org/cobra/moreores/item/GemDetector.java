package org.cobra.moreores.item;

import org.cobra.moreores.registry.ModBlockTags;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GemDetector extends Item {
    public GemDetector(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if(!world.isClient()) {
            BlockPos playerPos = user.getBlockPos();

            int rad = 50;
            for(int x = -rad; x <= rad; x++) {
                for(int y = -rad; y <= rad; y++) {
                    for(int z = -rad; z <= rad; z++) {

                        BlockPos checkPos = playerPos.add(x, y, z);
                        BlockState state = world.getBlockState(checkPos);

                        if(state.isIn(ModBlockTags.MOD_ORES)) {
                            user.sendMessage(Text.literal("Ore detected at " + checkPos.toShortString() + ", ore: " + state), false);
                            return ActionResult.SUCCESS;
                        }
                    }
                }
            }

            user.sendMessage(Text.literal("No ore found"), false);
        }
        return ActionResult.SUCCESS;
    }
}
