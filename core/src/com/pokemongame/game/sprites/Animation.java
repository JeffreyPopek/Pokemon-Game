package com.pokemongame.game.sprites;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Animation {
    private Array<TextureRegion> frames;
    private float maxFrameTime;
    private float currentFrameTime;
    private int frameCount;
    private int frame;

    public Animation(TextureRegion region, int frameCount, int colomCount, int rowCount, float cycleTime) {
        frames = new Array<TextureRegion>();
        int frameWidth = region.getRegionWidth() / colomCount;
        int frameHeight = region.getRegionHeight() / rowCount;
        int counter = 0;
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colomCount; j++) {
                if (counter >= frameCount){
                    break;
                }

                frames.add(new TextureRegion(
                        region,
                        j * frameWidth,
                        i * frameHeight,
                        frameWidth,
                        frameHeight
                ));

                counter += 1;

            }
            if (counter >= frameCount){
                break;
            }
        }

        this.frameCount = frameCount;
        maxFrameTime = cycleTime/frameCount;
        frame = 0;
   }

    public void update(float dt){
        currentFrameTime += dt;
        if (currentFrameTime > maxFrameTime){
            frame++;
            currentFrameTime = 0;
        }

        if (frame >= frameCount){
            frame = 0;
        }
    }

    public TextureRegion getFrame(){
        return frames.get(frame);
    }
}

