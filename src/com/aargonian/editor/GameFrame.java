package com.aargonian.editor;

import com.aargonian.tile.TileImpl;
import com.aargonian.tile.TileMap;

import javax.swing.*;
import java.awt.*;

/**
 * Created by aargonian on 7/8/17.
 */
public class GameFrame
{
    public static final String PROG_TITLE = "TileEditor 0.1.0";
    
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
                               {
                                   GameFrame.setupUI();
                               });
    }
    
    //TODO: Add an Icon Image
    private static final JFrame setupFrame()
    {
        JFrame frame = new JFrame(PROG_TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        if(screenSize.width <= 1024 || screenSize.height <= 800)
        {
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH); //maximize by default if the screen is really small
        }
        else
        {
            frame.setSize((int) Math.round(screenSize.width * 0.8), (int) Math.round(screenSize.height * 0.8));
            frame.setLocationRelativeTo(null); //Centers the frame
        }
        return frame;
    }
    
    /**
     * Creates the pane that holds the different tile implementations the user may choose to put into the editor.
     *
     * @return The Tile Pane Component, Fully Constructed
     */
    private static final JComponent setupTilesPanel()
    {
        throw new UnsupportedOperationException("Not Supported Yet.");
    }
    
    public static final void setupUI()
    {
        JFrame frame = setupFrame();

        TileMap map = new TileMap(10, 10);
        map.setTileResourceAt(TileImpl.PROPERTY_IMG, ImageReader.readImage("res/Water.png"), 2, 2);

        TileMapDisplay.OptionsBuilder displayOptions = new TileMapDisplay.OptionsBuilder();
        displayOptions = displayOptions
                .tileSize(32, 32)
                .displaySize(640, 480)
                .borderColor(Color.black);
        TileMapDisplay currentTileMapDisplay = new TileMapDisplay(displayOptions);
        
        currentTileMapDisplay.setCurrentTileMap(map);
        currentTileMapDisplay.setCurrentActiveTool(new TileSelectTool(currentTileMapDisplay));
        
        System.out.println("TILE MAP DISPLAY WIDTH: " + currentTileMapDisplay.getPreferredSize().getWidth());
        System.out.println("TILE MAP DISPLAY HEIGHT: " + currentTileMapDisplay.getPreferredSize().getHeight());

        frame.add(currentTileMapDisplay);
        
        frame.add(BorderLayout.CENTER, currentTileMapDisplay);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
}
