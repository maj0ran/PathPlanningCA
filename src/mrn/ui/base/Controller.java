package mrn.ui.base;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * The baseclass that represents a controller.
 * @param <M> Concrete Model
 * @param <V> Concrete View
 */
public abstract class Controller<M, V extends View> implements PropertyChangeListener {


    protected M model;
    protected V view;
    protected static PropertyChangeSupport change;

    //  public void register(EventBus bus);
    private Controller() {
       if(change == null) {
           change = new PropertyChangeSupport(this);
       }
       change.addPropertyChangeListener(this);
    }

    public Controller(M model, V view) {
        this();
        this.model = model;
        this.view = view;
        this.init(model, view);
    }

    protected abstract void init(M model, V view);

}
