package com.example.aishchen.testgame;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;

public class MainActivity extends SimpleBaseGameActivity {
    private Camera camera;
    private static final int CAMERA_WIDTH = 800;
    private static final int CAMERA_HEIGHT = 480;
    private EngineOptions engineOptions;
    private BitmapTextureAtlas yourTexture;
    private ITiledTextureRegion yourTextureRegion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onCreateResources() {
        loadGraphics();
//        pOnCreateResourcesCallback.onCreateResourcesFinished();
    }

    @Override
    protected Scene onCreateScene() {
        Scene scene = new Scene();
        scene.setBackground(new Background(0.09804f, 0.6274f, 0.8784f));

//        Sprite yourSprite = new Sprite(10, 10, yourTextureRegion, getVertexBufferObjectManager());
//        scene.attachChild(yourSprite);
        AnimatedSprite as = new AnimatedSprite(0, 0, yourTextureRegion, getVertexBufferObjectManager());
        long[] frameDurration = {100, 100, 100};
        as.animate(frameDurration);
        scene.attachChild(as);
        return scene;
    }

    private void loadGraphics() {
        BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
        yourTexture = new BitmapTextureAtlas(getTextureManager(), 757, 522, TextureOptions.DEFAULT);

        TextureRegion pBuildableTextureAtlas = BitmapTextureAtlasTextureRegionFactory.createFromResource(yourTexture, this, R.drawable.submarine, 0, 0);
        yourTextureRegion =
                TiledTextureRegion.create(pBuildableTextureAtlas.getTexture(), 0, 0, pBuildableTextureAtlas.getTexture().getWidth(), pBuildableTextureAtlas.getTexture().getHeight(), 4, 1);

        yourTexture.load();
    }

    @Override
    public EngineOptions onCreateEngineOptions() {
        camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
        engineOptions = new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED,
                new FillResolutionPolicy(), camera);
        return engineOptions;
    }
}
