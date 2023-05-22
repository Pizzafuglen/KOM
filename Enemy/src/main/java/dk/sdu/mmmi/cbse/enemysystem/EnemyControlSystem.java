package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

public class EnemyControlSystem implements IEntityProcessingService {

    private float entityTime = 0f;

    @Override
    public void process(GameData gameData, World world) {
        for (Entity enemy : world.getEntities(Enemy.class)) {
            PositionPart positionPart = enemy.getPart(PositionPart.class);
            MovingPart movingPart = enemy.getPart(MovingPart.class);
            LifePart lifePart = enemy.getPart(LifePart.class);

            this.entityTime = (this.entityTime + gameData.getDelta()) % 100;

            float controlRotateAmplifier = (float) (Math.random() * 2f) + 0.1f;
            float controlGeneralAmplifier = (float) (Math.random() * 2f) + 0.1f;

            movingPart.setLeft(
                    (Math.sin(entityTime * controlRotateAmplifier + (Math.random() * 2f)) * controlGeneralAmplifier) < this.getRandomNumber(-0.3f, -controlGeneralAmplifier)
            );
            movingPart.setRight(
                    (Math.sin(entityTime * controlRotateAmplifier + (Math.random() * 2f)) * controlGeneralAmplifier) > this.getRandomNumber(0.8f, controlGeneralAmplifier)
            );
            movingPart.setUp(
                    this.getRandomNumber(0.01f, 1f) > this.getRandomNumber(0.5f, 1f)
            );

            movingPart.process(gameData, enemy);
            positionPart.process(gameData, enemy);
            lifePart.process(gameData, enemy);


            if (lifePart.isDead()) {
                world.removeEntity(enemy);
            }

            System.out.println("Enemy component reached");

            updateShape(enemy);
        }
    }

    private void updateShape(Entity entity) {
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = (float) (Math.PI / 2f);

        float[] points = new float[7];
        points[0] = 1;
        points[1] = 2;
        points[2] = 2.2f;
        points[3] = 2.5f;
        points[4] = 2.8f;
        points[5] = 3;
        points[6] = 4;

        float[] distance = new float[7];
        distance[0] = distance[1] = distance[2] = 10;
        distance[3] = 15;
        distance[4] = distance[5] = distance[6] = 10;

        for (int i = 0; i < 7; i++) {
            shapex[i] = (float) (x + Math.cos(radians + Math.PI * (points[i] / 5)) * distance[i]);
            shapey[i] = (float) (y + Math.sin(radians + Math.PI * (points[i] / 5)) * distance[i]);
        }

        for (int i = 0; i < 7; i++) {
            shapex[i + 7] = (float) (x - Math.cos(radians + Math.PI * points[i] / 5) * distance[i]);
            shapey[i + 7] = (float) (y - Math.sin(radians + Math.PI * points[i] / 5) * distance[i]);
        }

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }

    private float getRandomNumber(float min, float max) {
        return (float) ((Math.random() * (max - min)) + min);
    }
}
