package pl.bbl.osbir;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import pl.bbl.osbir.properties.EditorProperties;
import pl.bbl.osbir.ui.UserInterface;

public class AnimationEditor extends ApplicationAdapter {
	private AssetManager assetManager;
    private Stage stage;
	private Stack actors;
	private OrthographicCamera camera;
	private SpriteBatch batch;
    private UserInterface userInterface;

	@Override
	public void create () {
	    this.assetManager = new AssetManager();
	    this.stage = new Stage();
		this.batch = new SpriteBatch();
		this.userInterface = new UserInterface(assetManager);
		setupCamera();
		setupStage();
	}

	private void setupCamera(){
	    camera = new OrthographicCamera();
	    camera.setToOrtho(false, EditorProperties.EDITOR_WIDTH, EditorProperties.EDITOR_HEIGHT);
    }

	private void setupStage(){
        stage = new Stage(new StretchViewport(EditorProperties.EDITOR_WIDTH, EditorProperties.EDITOR_HEIGHT));
        actors = new Stack();
        actors.setFillParent(true);
        stage.addActor(actors);
        Gdx.input.setInputProcessor(stage);
    }

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		update();
		batch.begin();
		batch.end();
	}

	private void update(){

    }

	@Override
	public void dispose () {
		batch.dispose();
	}
}
