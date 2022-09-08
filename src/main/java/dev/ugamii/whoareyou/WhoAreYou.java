package dev.ugamii.whoareyou;

import net.fabricmc.api.ClientModInitializer;
import dev.ugamii.whoareyou.config.WhoAreYouConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WhoAreYou implements ClientModInitializer {
	public static final Logger LOGGER = LogManager.getLogger("whoareyou");

	@Override
	public void onInitializeClient() {
		LOGGER.info("[Who are you?] who r u");

		WhoAreYouConfig.load();
	}
}
