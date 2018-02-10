package pl.bbl.osbir.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.kotcrab.vis.ui.util.dialog.Dialogs;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.file.FileChooser;
import pl.bbl.osbir.properties.EditorProperties;

public class EditorLayout {
    private UserInterface userInterface;

    private Stack layout;
    private Stage stage;

    private Table leftMenu;
    private Table upperBar;
    private Button newAnimation;
    private Button openAnimation;
    private Button saveAnimation;
    private Button searchButton;

    private static final String BACKGROUND_ATLAS_DIR = "ui/Backgrounds/Backgrounds.atlas";
    private TextureAtlas backgroundAtlas;
    private TextureRegion background;

    private VisTable fileChooserTable;
    private FileChooser fileChooser;

    public EditorLayout(UserInterface userInterface, Stage stage){
        this.userInterface = userInterface;
        this.stage = stage;
        initializeBackground();
        initializeTables();
        initializeFileChooser();
        initializeButtons();
        assemblyLayout();
        layout.debug();
    }

    private void initializeBackground(){
        userInterface.getAssetManager().load(BACKGROUND_ATLAS_DIR, TextureAtlas.class);
        userInterface.getAssetManager().finishLoading();
        backgroundAtlas = userInterface.getAssetManager().get(BACKGROUND_ATLAS_DIR);
        background = backgroundAtlas.findRegion("background");
    }

    private void initializeFileChooser(){
        fileChooser = new FileChooser(FileChooser.Mode.OPEN);
        fileChooser.setSelectionMode(FileChooser.SelectionMode.DIRECTORIES);
        fileChooserTable.add(fileChooser);
        fileChooserTable.debug();
    }

    private void initializeTables() {
        this.fileChooserTable = new VisTable();
        this.layout = new Stack();
        this.layout.setFillParent(true);
        this.leftMenu = new Table();
        this.upperBar = new Table();
    }

    private void initializeButtons(){
        this.newAnimation = new Button(userInterface.getSkin(), "new");
        this.openAnimation = new Button(userInterface.getSkin(), "open");
        this.saveAnimation = new Button(userInterface.getSkin(), "save");
        this.searchButton = new Button(userInterface.getSkin(), "search");
        addListenerToSearchButton();
    }

    private void addListenerToSearchButton(){
        searchButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                System.out.println("Kutas kutas");
                stage.addActor(fileChooserTable);
            }
        });
    }

    private void assemblyLayout() {
        assemblyUpperBar();
        assemblyLeftMenu();
        layout.add(leftMenu);
    }

    private void assemblyUpperBar() {
        upperBar.add(newAnimation);
        upperBar.add(openAnimation);
        upperBar.add(saveAnimation);
        upperBar.add(searchButton);
        upperBar.top().left();
    }

    private void assemblyLeftMenu() {
        leftMenu.add(upperBar);
        leftMenu.left();
    }

    public void render(SpriteBatch spriteBatch){
         //spriteBatch.draw(background, 0, 0, EditorProperties.EDITOR_WIDTH, EditorProperties.EDITOR_HEIGHT);
    }

    public Stack getLayout() {
        return layout;
    }
}
