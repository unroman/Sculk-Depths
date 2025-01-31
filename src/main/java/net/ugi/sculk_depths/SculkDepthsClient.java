package net.ugi.sculk_depths;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.particle.WaterSuspendParticle;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.nbt.NbtElement;
import net.minecraft.util.Identifier;
import net.ugi.sculk_depths.block.ModBlocks;
import net.ugi.sculk_depths.entity.ModEntities;
import net.ugi.sculk_depths.entity.client.GlomperRenderer;
import net.ugi.sculk_depths.fluid.ModFluids;
import net.ugi.sculk_depths.item.ModItems;
import net.ugi.sculk_depths.item.crystal.CrystalUpgrade;
import net.ugi.sculk_depths.particle.ModParticleTypes;
import net.ugi.sculk_depths.particle.SurfaceWindParticle;

import java.util.Arrays;
import java.util.List;

public class SculkDepthsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        SculkDepths.LOGGER.info("Registering tooltips for " + SculkDepths.MOD_ID);
        CrystalUpgrade.tooltipAdd();


        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.KRYSLUM_STILL,
                ModFluids.KRYSLUM_FLOWING,
                new SimpleFluidRenderHandler(
                        new Identifier("sculk_depths:block/kryslum_still"),
                        new Identifier("sculk_depths:block/kryslum_flow")
                )
        );




        ModelPredicateProviderRegistry.register(ModItems.QUAZARITH_AXE, new Identifier("crystal"), (itemStack, clientWorld, livingEntity, seed) -> {

            NbtElement nbtData = itemStack.getNbt().get("sculk_depths.crystal");

            if (nbtData == null) {return 0.0F;}

            List<String> crystalItemNbtList = Arrays.asList("\"white\"", "\"blue\"", "\"orange\"", "\"lime\"");

            return (crystalItemNbtList.indexOf(nbtData.toString()) + 1) * 0.1F;
        });




        EntityRendererRegistry.register(ModEntities.GLOMPER, GlomperRenderer::new);
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.VALTROX_SAPLING, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CEPHLERA, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CEPHLERA_LIGHT, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SOUL_FIRE, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WHITE_CRYSTAL_BLOCK, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.BLUE_CRYSTAL_BLOCK, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.ORANGE_CRYSTAL_BLOCK, RenderLayer.getTranslucent());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.LIME_CRYSTAL_BLOCK, RenderLayer.getTranslucent());


        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.VALTROX_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.VALTROX_TRAPDOOR, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.DRIED_VALTROX_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.DRIED_VALTROX_TRAPDOOR, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PETRIFIED_VALTROX_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PETRIFIED_VALTROX_TRAPDOOR, RenderLayer.getCutout());

        ParticleFactoryRegistry.getInstance().register(ModParticleTypes.PENEBRIUM_SPORES, WaterSuspendParticle.SporeBlossomAirFactory::new);
        ParticleFactoryRegistry.getInstance().register(ModParticleTypes.SURFACE_WIND, SurfaceWindParticle.Factory::new);
    }
}