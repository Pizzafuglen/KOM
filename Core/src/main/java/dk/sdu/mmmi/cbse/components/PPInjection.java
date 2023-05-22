package dk.sdu.mmmi.cbse.components;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.util.SPILocator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("postProcessInjection")
public class PPInjection implements IProcessor{

    public PPInjection() {

    }
    @Override
    public void process(GameData gd, World w) {
        List<IGamePluginService> plugins = SPILocator.locateAll(IGamePluginService.class);
        plugins.forEach((plugin) -> plugin.start(gd, w));
    }
}
