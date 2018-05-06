package mrn.ui.base;

/**
 * The base class to construct a view. A View may contain of multiple GUI Elements and has a Model
 * and a Controller. The Type of the Model is defined by the Generic Parameter. The concrete Controller
 * should be initialized in your concrete View.
 * Override the init() Method to declare all GUI-Elements. The init method gets called first,
 * so it should not depend on data from the model because it will not be initialized at this moment
 * @param <M> Set the class of your model as the generic parameter so your view can store the exact
 *           type of your model
 */
public abstract class View<M> {
    protected M model;
    protected Controller ctrl;

    protected View() {
        this.init();
    }

    /**
     * This is the initialization method of the View. Override it with the declaration
     * of all GUI-Elements. The method gets called automatically when your View will be instanciated.
     * Do not use the model in this method, because it will not be linked at this time.
     */
    protected abstract void init();

    protected void setModel(M data) {
        this.model = data;
    }

    protected void setController(Controller ctrl) {
        this.ctrl = ctrl;
    }

    public abstract <T> T getRoot();

}
