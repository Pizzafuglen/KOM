package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class AsteroidPlugin implements IGamePluginService {
    private Entity asteroid;

    public AsteroidPlugin asteroidPlugin;
    @Override
    public void start(GameData gameData, World world) {
        for (int i = 0; i < 5; i++) {
            float x = (float) (Math.random() * gameData.getDisplayWidth());
            float y = (float) (Math.random() * gameData.getDisplayHeight());
            float radians = (float) (Math.random() * (2 * Math.PI));
            float startingSpeed = (float) (Math.random() * 50f) + 25f;
            float deceleration = 0;
            float acceleration = 0;
            float maxSpeed = 400;
            float rotationSpeed = 0;
            float radius = 25;

            //Might be a problem, as the loop might not be able to gen more than one asteroid

            Entity asteroid = new Asteroid();
            asteroid.setShapeX(new float[8]);
            asteroid.setShapeY(new float[8]);
            asteroid.add(new MovingPart(deceleration, acceleration, maxSpeed, rotationSpeed, startingSpeed));
            asteroid.add(new PositionPart(x, y, radians, radius));
            LifePart lifePart = new LifePart(3, 0);
            asteroid.add(lifePart);

            world.addEntity(asteroid);
        }
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeEntity(asteroid);
    }
}
