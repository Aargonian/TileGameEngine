package com.aargonian.editor;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by aargonian on 7/8/17.
 */
public class ImageReader
{
    public static final BufferedImage readImage(String imagePath)
    {
        try {
            BufferedImage img = ImageIO.read(new File(imagePath));
            return img;
        } catch(IOException ex) {
            System.err.println("Exception Occurred: " + ex);
        }
        return null;
    }
}
