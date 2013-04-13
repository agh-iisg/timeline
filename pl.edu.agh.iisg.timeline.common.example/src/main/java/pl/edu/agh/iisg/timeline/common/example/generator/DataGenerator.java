package pl.edu.agh.iisg.timeline.common.example.generator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import pl.edu.agh.iisg.timeline.common.example.Activator;
import pl.edu.agh.iisg.timeline.common.example.util.Messages;
import pl.edu.agh.iisg.timeline.common.model.Axis;
import pl.edu.agh.iisg.timeline.common.model.Element;
import pl.edu.agh.iisg.timeline.common.model.TimelineDiagram;


public class DataGenerator {

    public static TimelineDiagram createSampleDiagram(final int axesCnt, final long totalElementsCnt, final long minDateTime,
            final long maxDateTime, final long initialDateTime) {

        TimelineDiagram diagram = new TimelineDiagram();

        long interval = (maxDateTime - minDateTime) / totalElementsCnt;

        Axis axes[] = new Axis[axesCnt];

        for (int axisIdx = 0; axisIdx < axesCnt; axisIdx++) {
            Axis.Builder axis = Axis.builder();
            axis.name("Axis no.: " + axisIdx); //$NON-NLS-1$
            axes[axisIdx] = axis.build();
            diagram.addAxis(axes[axisIdx]);
        }

        for (long elementIdx = 0; elementIdx < totalElementsCnt; elementIdx++) {
            Element.Builder element = Element.builder();
            element.title("Element no.: " + elementIdx); //$NON-NLS-1$
            element.description("This is element with index: " + elementIdx); //$NON-NLS-1$
            element.date(minDateTime + interval * elementIdx);
            element.axis(axes[((int)elementIdx % axesCnt)]);

            diagram.addElement(element.build());

        }

        return diagram;

    }

    public static TimelineDiagram createRealDataDiagram(int axesCnt, long totalElementsCnt, boolean useLongLabels) throws ParseException {
        TimelineDiagram diagram = new TimelineDiagram();

        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy:H"); //$NON-NLS-1$

        long hour = 60 * 60 * 1000l;
        long day = 24 * hour;
        long initialDateTime1 = formatter.parse("12/05/2012:2").getTime(); //$NON-NLS-1$
        long initialDateTime2 = formatter.parse("23/08/2012:3").getTime(); //$NON-NLS-1$
        long initialDateTime3 = formatter.parse("04/07/2012:4").getTime(); //$NON-NLS-1$

        Axis.Builder axis1Builder = Axis.builder();

        if (useLongLabels) {
            axis1Builder.name(Messages.DataGenerator__SampleAxisName20);
        } else {
            axis1Builder.name(Messages.DataGenerator__SampleAxisName21);
        }
        ImageDescriptor imgDesc1 = AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "images/icon1.png"); //$NON-NLS-1$
        axis1Builder.imageDesc(imgDesc1);
        Axis axis1 = axis1Builder.build();
        diagram.addAxis(axis1);

        addElement(Messages.DataGenerator_SampleElement1, Messages.DataGenerator_SampleElement2, initialDateTime1, day, axis1, diagram);
        addElement(Messages.DataGenerator_SampleElement3, Messages.DataGenerator_SampleElement4, initialDateTime3, day, axis1, diagram);

        Axis.Builder axis2Builder = Axis.builder();
        axis2Builder.name(Messages.DataGenerator_SampleAxisName1);
        ImageDescriptor imgDesc2 = AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "images/icon2.png"); //$NON-NLS-1$
        axis2Builder.imageDesc(imgDesc2);
        Axis axis2 = axis2Builder.build();
        diagram.addAxis(axis2);

        addElement(Messages.DataGenerator_SampleElement5, Messages.DataGenerator_SampleElement6, initialDateTime1, day, axis2, diagram);
        addElement(Messages.DataGenerator_SampleElement7, Messages.DataGenerator_SampleElement8, initialDateTime2 + 5 * hour, day, axis2,
                diagram);

        Axis.Builder axis3Builder = Axis.builder();
        axis3Builder.name(Messages.DataGenerator_SampleAxisName2);
        ImageDescriptor imgDesc3 = AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "images/icon3.png"); //$NON-NLS-1$
        axis3Builder.imageDesc(imgDesc3);
        Axis axis3 = axis3Builder.build();
        diagram.addAxis(axis3);

        addElement(Messages.DataGenerator_SampleElement9, Messages.DataGenerator_SampleElement10, initialDateTime3, day, axis3, diagram);
        addElement(Messages.DataGenerator_SampleElement11, Messages.DataGenerator_SampleElement12, initialDateTime3 + hour, day, axis3,
                diagram);

        final String[] events = { Messages.DataGenerator_SampleElement13, Messages.DataGenerator_SampleElement14,
            Messages.DataGenerator_SampleElement15, Messages.DataGenerator_SampleElement16, Messages.DataGenerator_SampleElement17 };

        final String[] descriptions = { "", Messages.DataGenerator_SampleElement19, Messages.DataGenerator_SampleElement20, //$NON-NLS-1$
            Messages.DataGenerator_SampleElement21, Messages.DataGenerator_SampleElement22 };

        final long[] dates = { initialDateTime1 + 60 * day, initialDateTime2 + 20 * day, initialDateTime3 + 60 * day };

        Axis axes[] = { axis1, axis2, axis3 };

        for (int i = 0; i < totalElementsCnt - 6; i++) {
            long date = dates[i % 3] + hour * (i % 30);
            dates[i % 3] = date;
            addElement(events[i % events.length], descriptions[i % descriptions.length], date, day, axes[i % 3], diagram);
        }

        return diagram;
    }

    public static TimelineDiagram createSampleDiagram(int axesCnt, long totalElementsCnt) throws Exception {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); //$NON-NLS-1$
        long minDateTime = formatter.parse("01/02/2012").getTime(); //$NON-NLS-1$
        long maxDateTime = formatter.parse("31/12/2012").getTime(); //$NON-NLS-1$
        long initialDateTime = formatter.parse("01/08/2012").getTime(); //$NON-NLS-1$

        return createSampleDiagram(axesCnt, totalElementsCnt, minDateTime, maxDateTime, initialDateTime);
    }

    private static void addElement(String name, String description, long date, long duration, Axis axis, TimelineDiagram diagram) {
        Element.Builder element = Element.builder();
        element.title(name);
        element.description(description);
        element.date(date);
        element.axis(axis);

        diagram.addElement(element.build());
    }
}
