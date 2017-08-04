package com.aargonian.editor;

import com.aargonian.resource.ImageResource;
import com.aargonian.tile.TileImpl;

import java.awt.event.MouseEvent;

/**
 * Created by aargonian on 7/23/17.
 */
public class PencilTool extends EditorTool implements TilesetDisplay.TilesetListener {
    private ImageResource currentImage;

    public PencilTool(TileMapDisplay display, TilesetDisplay tilesetDisplay) {
        super(display);
        tilesetDisplay.addSetListener(this);
    }

    @Override
    public void imageSelected(ImageResource resource) {
        this.currentImage = resource;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (this.currentImage != null) {
            int tileX = e.getX() / super.getCurrentDisplay().getOptions().tileWidth();
            int tileY = e.getY() / super.getCurrentDisplay().getOptions().tileHeight();
            this.getCurrentDisplay().getCurrentTileMap()
                    .setTileResourceAt(TileImpl.PROPERTY_IMG, this.currentImage, tileX, tileY);
            this.getCurrentDisplay().repaint();
        }
    }
}
