package dk.sdu.mmmi.cbse.components;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.util.SPILocator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("processInjection")
public class PInjection  implements IProcessor{

    public PInjection() {

    }
    @Override
    public void process(GameData gd, World w) {
        List<IEntityProcessingService> processors = SPILocator.locateAll(IEntityProcessingService.class);
        processors.forEach((processor) -> processor.process(gd, w));
    }
}
