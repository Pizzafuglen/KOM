package dk.sdu.mmmi.cbse.components;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.util.SPILocator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("pluginInjector")
public class PluginInjection {

    public PluginInjection() {

    }
    public void loadPlugins(GameData gm, World w) {
        List<IGamePluginService> plugins = SPILocator.locateAll(IGamePluginService.class);
        plugins.forEach((plugin) -> plugin.start(gm, w));
    }
}
