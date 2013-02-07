package pl.edu.agh.iisg.timeline.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.ParseException;

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
        TimelineDiagram diagram = DataGenerator.createRealDataDiagram(10, 10, false);

        storeToTempFileAndPrintStatistics(diagram);
    }

    private static void storeToTempFileAndPrintStatistics(TimelineDiagram diagram) throws IOException {
        File temp = File.createTempFile("tempfile", ".tmp");
        long start = System.currentTimeMillis();
        store(temp, diagram);
        long end = System.currentTimeMillis();
        System.out.println("time: " + (end - start));
        System.out.println("size: " + temp.length());

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
