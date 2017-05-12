package AgentProject;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public abstract class MyScene 
{
    Pane parent;
    abstract Scene getScene();
    void setParent(Pane p)
    {
        parent = p;
    }
}
