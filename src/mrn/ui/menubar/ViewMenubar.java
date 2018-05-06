package mrn.ui.menubar;

import javafx.scene.control.*;
import mrn.data.Model;
import mrn.ui.base.View;
import mrn.ui.base.Window;


public class ViewMenubar extends View<Model> {

    Window parent;
    private MenuBar menu; // root
    Menu menuFile;
    MenuItem save;
    MenuItem load;
    MenuItem quit;

    public ViewMenubar(Model model) {
        this.setModel(model);
        this.setController(new ControllerMenubar(model, this));
    }

    @Override
    protected void init() {


        menu = new MenuBar();
        menuFile = new Menu("File");
        save = new MenuItem("Save");
        load = new MenuItem("Load");
        quit = new MenuItem("Quit");

        menuFile.getItems().addAll(save, load, quit);
        menu.getMenus().addAll(menuFile);



    }
    public void update() {

    }

    public MenuBar getRoot() {
        return this.menu;
    }
}
