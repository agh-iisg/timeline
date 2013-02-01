package pl.edu.agh.iisg.timeline.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.swt.widgets.Display;

import pl.edu.agh.iisg.timeline.Activator;

public class FontsLoader {

    /**
     * Loads fonts files from specified directory (given as bundle relative path)
     *
     * @param relativeDir
     *            a font directory path
     * @throws IOException
     */
    public static void loadFromDirectory(String relativeDir) throws IOException {

        URL fullPathUrl = FileLocator.find(Platform.getBundle(Activator.PLUGIN_ID), new Path(relativeDir), null);
        File fontsDir = new File(FileLocator.toFileURL(fullPathUrl).getPath());
        if (!fontsDir.isDirectory()) {
            throw new IllegalArgumentException("Fonts should be loaded from directory given as plugin relative path");
        }

        for (File fontsFile : fontsDir.listFiles()) {
            Display.getDefault().loadFont(fontsFile.getAbsolutePath());
        }
    }
}
