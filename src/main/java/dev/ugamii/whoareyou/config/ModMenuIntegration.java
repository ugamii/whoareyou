package dev.ugamii.whoareyou.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import dev.isxander.yacl.api.ConfigCategory;
import dev.isxander.yacl.api.Option;
import dev.isxander.yacl.api.YetAnotherConfigLib;
import dev.isxander.yacl.gui.controllers.TickBoxController;
import net.minecraft.text.Text;

public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return getConfigScreen();
    }

    private ConfigScreenFactory<?> getConfigScreen() {
        return (parent) -> YetAnotherConfigLib.createBuilder()
                .title(Text.of("Who are you?"))
                .category(ConfigCategory.createBuilder()
                        .name(Text.translatable("whoareyou.config.nameDisplaying"))
                        .option(Option.createBuilder(boolean.class)
                                .name(Text.translatable("whoareyou.config.nameDisplaying.own"))
                                .binding(
                                        WhoAreYouConfig.enabledOwnNameDefault,
                                        () -> WhoAreYouConfig.enabledOwnName,
                                        (value) -> WhoAreYouConfig.enabledOwnName = value
                                )
                                .controller(TickBoxController::new)
                                .build())
                        .option(Option.createBuilder(boolean.class)
                                .name(Text.translatable("whoareyou.config.nameDisplaying.otherPlayers"))
                                .binding(
                                        WhoAreYouConfig.enabledOtherPlayersNameDefault,
                                        () -> WhoAreYouConfig.enabledOtherPlayersName,
                                        (value) -> WhoAreYouConfig.enabledOtherPlayersName = value
                                )
                                .controller(TickBoxController::new)
                                .build())
                        .build())
                .save(WhoAreYouConfig::save)
                .build()
                .generateScreen(parent);
    }
}
