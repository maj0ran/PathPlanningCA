package mrn.ui.menu;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import mrn.data.Model;
import mrn.ui.base.View;

public class ViewMenu extends View<Model> {

    MenuBar menu;
    Menu menuFile;
    MenuItem reset;

    public ViewMenu(Model model) {
        this.model = model;
        this.ctrl = new ControllerMenu(model, this);
    }

    @Override
    protected void init() {
        menu = new MenuBar();
        menuFile = new Menu("File");
        reset = new MenuItem("reset");

        menuFile.getItems().add(reset);
        menu.getMenus().add(menuFile);
    }

    @Override
    public MenuBar getRoot() {
        return menu;
    }
}
