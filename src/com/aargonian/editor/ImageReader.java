package com.aargonian.editor;

import java.awt.image.BufferedImage;

/**
 * Created by aargonian on 7/8/17.
 */
public class ImageReader
{
    public static BufferedImage readImage(String imagePath)
    {
        return ResourceLoader.loadImage(imagePath);
    }
}
