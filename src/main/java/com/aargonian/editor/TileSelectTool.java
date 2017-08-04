package com.aargonian.editor;

import com.aargonian.resource.ImageResource;
import com.aargonian.tile.TileImpl;

import java.awt.event.MouseEvent;

/**
 * Created by aargonian on 7/16/17.
 */
public class TileSelectTool extends EditorTool {
    private int currentTileX = 0;
    private int currentTileY = 0;

    public TileSelectTool(TileMapDisplay display) {
        super(display);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int tileX = e.getX() / super.getCurrentDisplay().getOptions().tileWidth();
        int tileY = e.getY() / super.getCurrentDisplay().getOptions().tileHeight();
        //getCurrentDisplay().setSelectedTiles(Arrays.asList(new Pair<Integer, Integer>(tileX, tileY)));
        this.getCurrentDisplay().getCurrentTileMap()
                .setTileResourceAt(TileImpl.PROPERTY_IMG, new ImageResource("res/Water.png"), tileX, tileY);
        this.getCurrentDisplay().repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int tileX = e.getX() / super.getCurrentDisplay().getOptions().tileWidth();
        int tileY = e.getY() / super.getCurrentDisplay().getOptions().tileHeight();

        if (tileX != this.currentTileX || tileY != this.currentTileY) {
            this.currentTileX = tileX;
            this.currentTileY = tileY;
        }
    }
}
