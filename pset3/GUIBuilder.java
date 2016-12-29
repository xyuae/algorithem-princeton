import java.lang.Object;

// client
public class GUIBuilder {
    public void buildWindow (AbstractWidgetFactory widgetFactory) {
        Window window = widgetFactory.createWindow();
        window.setTitle();
    }
    
    public static void main(String[] args) {
        GUIBuilder builder = new GUIBuilder();
        AbstractWidgetFactory widgetFactory = null;
        // check waht platform we're on
        if (Platform.currentPlatform()=="MACOSX") {
            widgetFactory = new MsWindowsWidgetFactory();
        }
        else {
            widgetFactory = new MacOSWidgetFactory();
        }
        builder.buildWindow(widgetFactory);

    }
}
