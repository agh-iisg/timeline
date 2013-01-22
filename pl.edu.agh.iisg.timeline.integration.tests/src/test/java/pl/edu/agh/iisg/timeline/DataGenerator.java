package pl.edu.agh.iisg.timeline;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import pl.edu.agh.iisg.timeline.model.Axis;
import pl.edu.agh.iisg.timeline.model.AxisElement;
import pl.edu.agh.iisg.timeline.model.TimelineDiagram;
import pl.edu.agh.iisg.timeline.util.Messages;

public class DataGenerator {

    public static TimelineDiagram createSampleDiagram(final int axesCnt, final long totalElementsCnt, final long minDateTime,
            final long maxDateTime, final long initialDateTime) {

        TimelineDiagram.Builder diagram = TimelineDiagram.builder();

        diagram.name("Sample timeline diagram with " + axesCnt + " axes and " + totalElementsCnt + " elements.");

        diagram.minDateTime(minDateTime);
        diagram.maxDateTime(maxDateTime);
        diagram.initialDateTime(initialDateTime);

        long interval = (maxDateTime - minDateTime) / totalElementsCnt;

        for (int axisIdx = 0; axisIdx < axesCnt; axisIdx++) {
            Axis.Builder axis = Axis.builder();
            axis.name("Axis no.: " + axisIdx);
            diagram.addAxis(axis.build());
        }

        for (long elementIdx = 0; elementIdx < totalElementsCnt; elementIdx++) {
            AxisElement.Builder element = AxisElement.builder();
            element.name("Element no.: " + elementIdx);
            element.description("This is element with index: " + elementIdx);
            element.date(minDateTime + interval * elementIdx);
            element.setDuration(interval);
            element.owner(diagram.getAxis((int)elementIdx % axesCnt));

            diagram.addElement(element.build());

        }

        return diagram.build();

    }

    public static TimelineDiagram createRealDataDiagram(int axesCnt, long totalElementsCnt, boolean useLongLabels) throws ParseException {
        TimelineDiagram.Builder diagram = TimelineDiagram.builder();

        diagram.name("Real data timeline diagram with " + axesCnt + " axes and " + totalElementsCnt + " elements.");

        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy:H");

        long hour = 60 * 60 * 1000l;
        long day = 24 * hour;
        long initialDateTime1 = formatter.parse("12/05/2012:2").getTime();
        long initialDateTime2 = formatter.parse("23/08/2012:3").getTime();
        long initialDateTime3 = formatter.parse("04/07/2012:4").getTime();

        diagram.minDateTime(initialDateTime1);
        diagram.maxDateTime(initialDateTime1 + 1000 * day);
        diagram.initialDateTime(initialDateTime1);

        Axis.Builder axis1 = Axis.builder();

        if (useLongLabels) {
            axis1.name(Messages.DataGenerator__SampleAxisName20);
        } else {
            axis1.name(Messages.DataGenerator__SampleAxisName21);
        }
        ImageDescriptor imgDesc1 = AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "images/icon1.png");
        axis1.imageDesc(imgDesc1);
        diagram.addAxis(axis1.build());

        addAxisElement(Messages.DataGenerator_SampleElement1, Messages.DataGenerator_SampleElement2, initialDateTime1, day, 0, diagram);
        addAxisElement(Messages.DataGenerator_SampleElement3, Messages.DataGenerator_SampleElement4, initialDateTime3, day, 0, diagram);

        Axis.Builder axis2 = Axis.builder();
        axis2.name(Messages.DataGenerator_SampleAxisName1);
        ImageDescriptor imgDesc2 = AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "images/icon2.png");
        axis2.imageDesc(imgDesc2);
        diagram.addAxis(axis2.build());

        addAxisElement(Messages.DataGenerator_SampleElement5, Messages.DataGenerator_SampleElement6, initialDateTime1, day, 1, diagram);
        addAxisElement(Messages.DataGenerator_SampleElement7, Messages.DataGenerator_SampleElement8, initialDateTime2 + 5 * hour, day, 1,
                diagram);

        Axis.Builder axis3 = Axis.builder();
        axis3.name(Messages.DataGenerator_SampleAxisName2);
        ImageDescriptor imgDesc3 = AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "images/icon3.png");
        axis3.imageDesc(imgDesc3);
        diagram.addAxis(axis3.build());

        addAxisElement(Messages.DataGenerator_SampleElement9, Messages.DataGenerator_SampleElement10, initialDateTime3, day, 2, diagram);
        addAxisElement(Messages.DataGenerator_SampleElement11, Messages.DataGenerator_SampleElement12, initialDateTime3 + hour, day, 2,
                diagram);

        final String[] events = { Messages.DataGenerator_SampleElement13, Messages.DataGenerator_SampleElement14,
            Messages.DataGenerator_SampleElement15, Messages.DataGenerator_SampleElement16, Messages.DataGenerator_SampleElement17 };

        final String[] descriptions = { Messages.DataGenerator_SampleElement18, Messages.DataGenerator_SampleElement19,
            Messages.DataGenerator_SampleElement20, Messages.DataGenerator_SampleElement21, Messages.DataGenerator_SampleElement22 };

        final long[] dates = { initialDateTime1 + 60 * day, initialDateTime2 + 20 * day, initialDateTime3 + 60 * day };

        for (int i = 0; i < totalElementsCnt - 6; i++) {
            long date = dates[i % 3] + hour * (i % 30);
            dates[i % 3] = date;
            addAxisElement(events[i % events.length], descriptions[i % descriptions.length], date, day, i % 3, diagram);
        }

        return diagram.build();
    }

    public static TimelineDiagram createSampleDiagram(int axesCnt, long totalElementsCnt) throws Exception {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        long minDateTime = formatter.parse("01/02/2012").getTime();
        long maxDateTime = formatter.parse("31/12/2012").getTime();
        long initialDateTime = formatter.parse("01/08/2012").getTime();

        return createSampleDiagram(axesCnt, totalElementsCnt, minDateTime, maxDateTime, initialDateTime);
    }

    private static void addAxisElement(String name, String description, long date, long duration, int axisNumber,
            TimelineDiagram.Builder diagram) {
        AxisElement.Builder element = AxisElement.builder();
        element.name(name);
        element.description(description);
        element.date(date);
        element.setDuration(duration);
        element.owner(diagram.getAxis(axisNumber));

        diagram.addElement(element.build());
    }
}
