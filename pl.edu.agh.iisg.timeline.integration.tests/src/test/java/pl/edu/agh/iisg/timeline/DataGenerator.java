package pl.edu.agh.iisg.timeline;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import pl.edu.agh.iisg.timeline.model.Axis;
import pl.edu.agh.iisg.timeline.model.AxisElement;
import pl.edu.agh.iisg.timeline.model.TimelineDiagram;

public class DataGenerator {

	public static TimelineDiagram createSampleDiagram(final int axesCnt,
			final long totalElementsCnt, final long minDateTime,
			final long maxDateTime, final long initialDateTime) {


		TimelineDiagram.Builder diagram = TimelineDiagram.builder();

		diagram.name("Sample timeline diagram with " + axesCnt + " axes and "
				+ totalElementsCnt + " elements.");

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
			element.owner(diagram.getAxis((int) elementIdx % axesCnt));

			diagram.addElement(element.build());

		}

		return diagram.build();

	}

	public static TimelineDiagram createRealDataDiagram(int axesCnt, long totalElementsCnt) throws ParseException {
		TimelineDiagram.Builder diagram = TimelineDiagram.builder();

		diagram.name("Real data timeline diagram with " + axesCnt + " axes and "
				+ totalElementsCnt + " elements.");

		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		long minDateTime = formatter.parse("01/02/2012").getTime();
		long maxDateTime = formatter.parse("31/12/2012").getTime();
		long initialDateTime = formatter.parse("01/08/2012").getTime();

		diagram.minDateTime(minDateTime);
		diagram.maxDateTime(maxDateTime);
		diagram.initialDateTime(initialDateTime);

		long interval = (maxDateTime - minDateTime) / totalElementsCnt;

		for (int axisIdx = 0; axisIdx < axesCnt; axisIdx++) {
			Axis.Builder axis = Axis.builder();
			axis.name("ZIELONA KASKADA. \"NERO - DEVELOPMENT\" SPOLKA Z OGRANICZONA ODPOWIEDZIALNOSCIA. ROMANOWSKI, KILJANCZYK I PARTNERZY SPOLKA Z OGRANICZONA ODPOWIEDZALNOSCIA. SPOLKA KOMANDYTOWA");

			ImageDescriptor imgDesc =  AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "images/dt_majesty.png");
			axis.imageDesc(imgDesc);
			diagram.addAxis(axis.build());
		}


		for (long elementIdx = 0; elementIdx < totalElementsCnt; elementIdx++) {
			AxisElement.Builder element = AxisElement.builder();
			element.name("Element no.: " + elementIdx);
			element.description("This is element with index: " + elementIdx);
			element.date(minDateTime + interval * elementIdx);
			element.setDuration(interval);
			element.owner(diagram.getAxis((int) elementIdx % axesCnt));

			diagram.addElement(element.build());

		}

		return diagram.build();
	}

	public static TimelineDiagram createSampleDiagram(int axesCnt,
			long totalElementsCnt) throws Exception {
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		long minDateTime = formatter.parse("01/02/2012").getTime();
		long maxDateTime = formatter.parse("31/12/2012").getTime();
		long initialDateTime = formatter.parse("01/08/2012").getTime();

		return createSampleDiagram(axesCnt, totalElementsCnt, minDateTime,
				maxDateTime, initialDateTime);
	}
}
