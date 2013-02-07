package pl.edu.agh.iisg.timeline.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.util.Collection;
import java.util.List;

import pl.edu.agh.iisg.timeline.integration.DataGenerator;

public class SaveToFilePerformanceTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            test();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("done.");

    }

    private static void test() throws ParseException, IOException {
        TimelineDiagram diagram = DataGenerator.createRealDataDiagram(10, 100000, false);

        storeToTempFileAndPrintStatistics(diagram);
        storeTextToTempFileAndPrintStatistics(diagram);
    }

    private static void storeTextToTempFileAndPrintStatistics(TimelineDiagram diagram) throws IOException {
        System.out.println("2. Manual text serialization:");
        File temp = File.createTempFile("tempfile2", ".tmp");
        long start = System.currentTimeMillis();

        storeTextDiagram(temp, diagram);

        long end = System.currentTimeMillis();
        System.out.println("time: " + (end - start));
        System.out.println("size: " + temp.length());
        System.out.println();
    }

    private static void storeTextDiagram(File file, TimelineDiagram diagram) {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            writeElements(oos, diagram.getElements());
            writeAxes(oos, diagram.getAxes());

            oos.close();
            fos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    private static void writeAxes(ObjectOutputStream oos, List<Axis> axes) throws IOException {
        for (Axis axis : axes) {
            oos.writeChars(axis.getName());
            oos.writeObject(axis.getImageDesc());
        }

    }

    private static void writeElements(ObjectOutputStream oos, Collection<Element> elements) throws IOException {
        for (Element element : elements) {
            oos.writeChars(element.getAxis().getName());
            oos.writeChars(element.getTitle());
            oos.writeChars(element.getDescription());
            oos.writeLong(element.getDate());
        }

    }

    private static void storeToTempFileAndPrintStatistics(TimelineDiagram diagram) throws IOException {
        System.out.println("1. Java Serialization:");
        File temp = File.createTempFile("tempfile", ".tmp");
        long start = System.currentTimeMillis();

        store(temp, diagram);

        long end = System.currentTimeMillis();
        System.out.println("time: " + (end - start));
        System.out.println("size: " + temp.length());
        System.out.println();

    }

    public static boolean store(File f, TimelineDiagram diagram) {
        try {
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(diagram);
            oos.close();
            fos.close();
        } catch (IOException ex) {
            return false;
        }
        return true;
    }

}
