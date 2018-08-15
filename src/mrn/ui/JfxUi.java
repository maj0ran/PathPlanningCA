package mrn.ui;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import mrn.data.Model;
import mrn.ui.animationbar.ViewAnimationBar;
import mrn.ui.ctrlpanel.ViewBtnPanel;
import mrn.ui.menu.ViewMenu;
import mrn.ui.base.Window;
import mrn.ui.ca.ViewCA;

public class JfxUi {
    public JfxUi(Stage mainstage) {
        /* Init your model(s) */
        Model data = new Model();

        /* Init a window */
        Window<GridPane> mainwindow = new Window<>(mainstage);
        mainwindow.setLayout(new GridPane());
        mainstage.setTitle("Path Planning with Cellular Automat");
        GridPane wndw_layout = mainwindow.getLayout();
        wndw_layout.setGridLinesVisible(true);


        /* Init Views and add them to the window layout */
        ViewMenu menu = new ViewMenu(data);
        wndw_layout.add(menu.getRoot(), 0, 0, 3, 1);
        menu.getRoot().prefWidthProperty().bind(mainstage.widthProperty());

        ViewBtnPanel btnPanel = new ViewBtnPanel(data);
        wndw_layout.add(btnPanel.getRoot(), 0, 2);


        ViewCA viewCA = new ViewCA(data);
        wndw_layout.add(viewCA.getRoot(), 1, 2);

        ViewAnimationBar animationBar = new ViewAnimationBar(data);
        wndw_layout.add(animationBar.getRoot(), 1, 1, 2, 1);
        animationBar.getRoot().prefWidthProperty().bind(mainstage.widthProperty());




        /* add the window to the stage */
        mainwindow.getStage().setScene(new Scene(mainwindow.getLayout(), 1400, 900));
        mainwindow.getStage().show();

    }
}
