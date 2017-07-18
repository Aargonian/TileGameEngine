package com.aargonian.editor;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by aargonian on 7/17/17.
 *
 * A utility class for loading and caching common resources used by the editor. Primarily used to load images and sound
 * files.
 */
public final class ResourceLoader
{
    private static final Map<String, Object> resourceCache = new HashMap<>(100);
    
    public static final BufferedImage loadImage(String imagePath)
    {
        if(imagePath == null || imagePath.isEmpty())
            return null;
        if(resourceCache.containsKey(imagePath) && resourceCache.get(imagePath) != null)
            return (BufferedImage)resourceCache.get(imagePath);
        else
        {
            try
            {
                BufferedImage image = ImageIO.read(new File(imagePath));
                resourceCache.put(imagePath, image);
                return image;
            }
            catch(IOException ex)
            {
                System.err.println("Unable to load resource: " + imagePath);
                System.err.println(ex);
                for(StackTraceElement element : ex.getStackTrace())
                {
                    System.err.println("\tat " + element);
                }
                return null;
            }
        }
    }
}
