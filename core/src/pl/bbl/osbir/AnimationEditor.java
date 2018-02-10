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
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.widget.file.FileChooser;
import pl.bbl.osbir.properties.EditorProperties;
import pl.bbl.osbir.ui.EditorLayout;
import pl.bbl.osbir.ui.UserInterface;

public class AnimationEditor extends ApplicationAdapter {
	private AssetManager assetManager;
    private Stage stage;
	private Stack actors;
	private OrthographicCamera camera;
	private SpriteBatch batch;
    private UserInterface userInterface;
	private EditorLayout editorLayout;

	@Override
	public void create () {
		this.assetManager = new AssetManager();
	    this.stage = new Stage();
		this.batch = new SpriteBatch();
		this.userInterface = new UserInterface(assetManager);
		this.editorLayout = new EditorLayout(userInterface, stage);
		setupCamera();
		setupStage();
		testFileChooser();
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
        actors.addActor(editorLayout.getLayout());
    }

    private void testFileChooser(){
		FileChooser fileChooser = new FileChooser(FileChooser.Mode.OPEN);
		fileChooser.setSelectionMode(FileChooser.SelectionMode.DIRECTORIES);
		stage.addActor(fileChooser);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		update();
		batch.begin();
		editorLayout.render(batch);
		batch.end();
		stage.draw();
	}

	private void update(){
    }

	@Override
	public void dispose () {
		batch.dispose();
	}
}
