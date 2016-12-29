// Abstract Product
public class MacOSWidgetFactory implements AbstractWidgetFactory {
    // create an MSWindow
    public Window createWindow() {
        MacOSWindow window = new MacOSWindow();
        return window;
    }
}
