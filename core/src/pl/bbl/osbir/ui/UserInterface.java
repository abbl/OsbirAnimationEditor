package pl.bbl.osbir.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class UserInterface {
    private static final String UI_SKIN_PATH = "ui/skin.json";
    private static final String[] UI_COMPONENTS_PATH = {
            "ui/Buttons/Buttons.atlas",
            "ui/Fonts/Fonts.atlas",
    };
    private AssetManager assetManager;
    private Skin skin;

    public UserInterface(AssetManager assetManager){
        this.assetManager = assetManager;
        skin = new Skin();
        loadDependencies();
        assetManager.finishLoading();
        loadSkin();
    }

    private void loadDependencies(){
        loadUIComponents();
        loadFonts();
    }

    private void loadUIComponents(){
        for(String filePath : UI_COMPONENTS_PATH){
            assetManager.load(filePath, TextureAtlas.class);
        }
    }

    private void loadFonts(){
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("ui/Fonts/default.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 24;
        BitmapFont font = generator.generateFont(parameter);
        skin.add("defaultFont", font);
        generator.dispose();
    }

    private void loadSkin(){
        for(String filePath : UI_COMPONENTS_PATH){
            skin.addRegions(assetManager.get(filePath, TextureAtlas.class));
        }
        skin.load(Gdx.files.internal(UI_SKIN_PATH));
    }

    public Skin getSkin() {
        return skin;
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public void dispose(){

    }
}
