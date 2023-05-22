package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;


public class EnemyPlugin implements IGamePluginService {
    public EnemyPlugin() {

    }

    @Override
    public void start(GameData gameData, World world) {
        float deceleration = 10;
        float acceleration = 200;
        float maxSpeed = 300;
        float rotationSpeed = 5;
        float x = this.getRandomNumber(0, gameData.getDisplayWidth());
        float y = this.getRandomNumber(0, gameData.getDisplayHeight());
        float radians = this.getRandomNumber(0f, (float) (2 * Math.PI));

        Entity enemyShip = new Enemy();

        enemyShip.setShapeX(new float[14]);
        enemyShip.setShapeY(new float[14]);
        enemyShip.add(new MovingPart(deceleration, acceleration, maxSpeed, rotationSpeed, 50));
        enemyShip.add(new PositionPart(x, y, radians, 4));
        enemyShip.add(new LifePart(1, 0));

        world.addEntity(enemyShip);
    }

    @Override
    public void stop(GameData gameData, World world) {
        for (Entity enemy : world.getEntities(Enemy.class)) {
            world.removeEntity(enemy);
        }
    }
    private float getRandomNumber(float min, float max) {
        return (float) ((Math.random() * (max - min)) + min);
    }
}
