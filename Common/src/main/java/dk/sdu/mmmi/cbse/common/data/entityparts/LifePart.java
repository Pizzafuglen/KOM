package dk.sdu.mmmi.cbse.common.data.entityparts;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;

public class LifePart implements EntityPart {

    private int life;
    private boolean isHit, isDead;
    private float expiration;

    public LifePart(int life, float expiration) {
        this.life = life;
        this.expiration = expiration;
        this.isHit = false;
        this.isDead = false;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public boolean isIsHit() {
        return isHit;
    }

    public void setIsHit(boolean isHit) {
        this.isHit = isHit;
    }

    public float getExpiration() {
        return expiration;
    }

    public boolean isDead(){
        return this.isDead;
    }

    public void setExpiration(float expiration) {
        this.expiration = expiration;
    }  
    
    public void reduceExpiration(float delta){
        this.expiration -= delta;
    }
    
    @Override
    public void process(GameData gameData, Entity entity) {
        if (this.isHit) {
            this.life -= 1;
            this.isHit = false;
        }
        if (this.life <= 0) {
            this.isDead = true;
        }
    }
}
