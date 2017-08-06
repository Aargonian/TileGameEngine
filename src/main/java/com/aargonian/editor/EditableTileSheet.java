package com.aargonian.editor;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aargonian on 7/13/17.
 * <p>
 * The EditableTileSheet Class is an implementation for a collection of Tiles arranged within a Sheet, as used by the
 * editing
 * program. Although this can be thought of in the traditional TileSheet sense (and in fact, a TileSheet can be
 * constructed from a TileSheetReader from an actual TileSheet image), the EditableTileSheet is mutable, and doesn't
 * keep each tile instance as a subimage within a larger sheet, unless written out by a TileSheetWriter. Instead,
 * individual tile images can be added or removed from the set at will.
 */
public class EditableTileSheet
{
    /*
     * An EditableTileSheet is the backing model for the TileSheets dock of the editor, and as such implements the
     * Observer
     * pattern for the sake of a cleaner MVC implementation.
     */
    private final List<Image> tileImages;

    /**
     * Creates an empty EditableTileSheet.
     */
    public EditableTileSheet()
    {
        this.tileImages = new ArrayList<>();
    }

    public EditableTileSheet(List<Image> tileImages)
    {
        this.tileImages = tileImages;
    }
}
