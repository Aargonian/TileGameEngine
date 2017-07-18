package com.aargonian.editor;

import aargonian.tile.TileImpl;

import java.awt.event.MouseEvent;

/**
 * Created by aargonian on 7/16/17.
 */
public class TileSelectTool extends EditorTool
{
    private int currentTileX = 0;
    private int currentTileY = 0;
    
    public TileSelectTool(TileMapDisplay display)
    {
        super(display);
    }
    
    @Override
    public void mouseClicked(MouseEvent e)
    {
        int tileX = e.getX()/super.getCurrentDisplay().getOptions().tileWidth();
        int tileY = e.getY()/super.getCurrentDisplay().getOptions().tileHeight();
        //getCurrentDisplay().setSelectedTiles(Arrays.asList(new Pair<Integer, Integer>(tileX, tileY)));
        getCurrentDisplay().getCurrentTileMap().setTileResourceAt(TileImpl.PROPERTY_IMG, ImageReader.readImage("res/Water.png"), tileX, tileY);
        getCurrentDisplay().repaint();
    }
    
    @Override
    public void mouseMoved(MouseEvent e)
    {
        int tileX = e.getX()/super.getCurrentDisplay().getOptions().tileWidth();
        int tileY = e.getY()/super.getCurrentDisplay().getOptions().tileHeight();
        
        if(tileX != currentTileX || tileY != currentTileY)
        {
            currentTileX = tileX;
            currentTileY = tileY;
        }
    }
}
