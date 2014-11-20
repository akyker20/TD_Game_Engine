package gamePlayer.guiContainers.secondaryContainers;

import java.io.File;
import java.util.List;
import gamePlayer.guiContainers.GuiContainer;
import gamePlayer.mainClasses.GuiElement;
import javafx.geometry.Dimension2D;
import javafx.scene.Node;
import javafx.scene.layout.TilePane;
import utilities.XMLParsing.XMLParser;
import utilities.reflection.Reflection;

public class ControlDock extends TilePane implements GuiContainer {
    private XMLParser myParser;

    @Override
    public void initialize (Dimension2D containerSize) {
        myParser = new XMLParser(new File(myPropertiesPath+this.getClass().getSimpleName()+".XML"));
        
        //set component size
        Dimension2D sizeRatio = myParser.getDimension("SizeRatio");
        Dimension2D mySize = new Dimension2D(containerSize.getWidth()*sizeRatio.getWidth(),
                                             containerSize.getHeight()*sizeRatio.getHeight());
        
        this.setMinSize(mySize.getWidth(),mySize.getHeight());
        this.setPrefSize(mySize.getWidth(),mySize.getHeight());
        
        //add contained GUI elements
        List<String> myItems = myParser.getValuesFromTag("Items");
        for (String item:myItems) {
                GuiElement element = (GuiElement) Reflection.createInstance(item);
                element.initialize(mySize);
                this.getChildren().add(element.getNode());
        }
    }

    @Override
    public Node getNode () {
        return this;
    }
}
