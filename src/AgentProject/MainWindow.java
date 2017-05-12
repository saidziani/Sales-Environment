package AgentProject;

import java.util.*;
import javafx.scene.*;
import javafx.stage.*;

public class MainWindow 
{
    Stage myStage;
    LinkedList<MyScene> scenes = new LinkedList<MyScene>();
    MainWindow(Stage S)
    {
        myStage = S;
    }
    
    void nextScene(MyScene S)
    {
        scenes.add(S);
        showScene(scenes.getLast());
    }
    
    void switchScene(MyScene S)
    {
        if(scenes.size() > 0)
        scenes.removeLast();
        scenes.add(S);
        showScene(scenes.getLast());
    }
    
    void popScene()
    {
        scenes.removeLast();
        if(scenes.size() > 0)
        showScene(scenes.getLast());
        else
        myStage.close();
    }
    
    private void showScene(MyScene S)
    {
        myStage.setScene(S.getScene());
        myStage.show();
    }
}
