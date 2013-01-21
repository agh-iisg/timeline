package pl.edu.agh.iisg.timeline;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.eclipse.jface.resource.ImageDescriptor;
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

	public static TimelineDiagram createRealDataDiagram(int axesCnt,
			long totalElementsCnt, boolean useLongLabels) throws ParseException {
		TimelineDiagram.Builder diagram = TimelineDiagram.builder();

		diagram.name("Real data timeline diagram with " + axesCnt
				+ " axes and " + totalElementsCnt + " elements.");

		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		long interval = 24 * 60 * 60 * 1000l;
		long initialDateTime1 = formatter.parse("12/05/2012").getTime();
		long initialDateTime2 = formatter.parse("23/08/2012").getTime();
		long initialDateTime3 = formatter.parse("04/07/2012").getTime();

		diagram.minDateTime(initialDateTime1);
		diagram.maxDateTime(initialDateTime1 + 1000 * interval);
		diagram.initialDateTime(initialDateTime1);

		Axis.Builder axis1 = Axis.builder();

		if(useLongLabels) {
		axis1.name("ZIELONA KASKADA. \"NERO - DEVELOPMENT\" ZIELONA KASKADA. \"NERO - DEVELOPMENT\" SPӣKA Z OGRANICZON� ODPOWIEDZIALNO�CI�. ROMANOWSKI, KILJANCZYK I PARTNERZY SPӣKA Z OGRANICZON� ODPOWIEDZALNO�CI�. SPӣKA KOMANDYTOWA");
		} else {
			axis1.name("\"NERO - DEVELOPMENT\" SPӣKA Z OGRANICZON� ODPOWIEDZIALNO�CI�");
		}
		ImageDescriptor imgDesc1 = AbstractUIPlugin.imageDescriptorFromPlugin(
				Activator.PLUGIN_ID, "images/icon1.png");
		axis1.imageDesc(imgDesc1);
		diagram.addAxis(axis1.build());

		addAxisElement("Rejestracja dzia�alno�ci gospodarczej",
				"Firma rozpoczyna dzia�alno��", initialDateTime1, interval, 0,
				diagram);
		addAxisElement("Wynajem nowego lokalu",
				"Siedziba firmy zostaje ulokowana w Krakowie",
				initialDateTime3, interval, 0, diagram);

		Axis.Builder axis2 = Axis.builder();
		axis2.name("PRZEDSI�BIORSTWO PRODUKCYJNO US�UGOWO HANDLOWE JAN KOWALSKI");
		ImageDescriptor imgDesc2 = AbstractUIPlugin.imageDescriptorFromPlugin(
				Activator.PLUGIN_ID, "images/icon2.png");
		axis2.imageDesc(imgDesc2);
		diagram.addAxis(axis2.build());

		addAxisElement("Rejestracja dzia�alno�ci gospodarczej",
				"Firma rozpoczyna dzia�alno��", initialDateTime2, interval, 1,
				diagram);
		addAxisElement("Wynajem nowego lokalu",
				"Siedziba firmy zostaje ulokowana w Warszawie",
				initialDateTime2 + 5 * interval, interval, 1, diagram);

		Axis.Builder axis3 = Axis.builder();
		axis3.name("ROMANOWSKI, KILJA�CZYK I PARTNERZY SPӣKA KOMANDYTOWA");
		ImageDescriptor imgDesc3 = AbstractUIPlugin.imageDescriptorFromPlugin(
				Activator.PLUGIN_ID, "images/icon3.png");
		axis3.imageDesc(imgDesc3);
		diagram.addAxis(axis3.build());

		addAxisElement("Rejestracja dzia�alno�ci gospodarczej",
				"Firma rozpoczyna dzia�alno��", initialDateTime3, interval, 2,
				diagram);
		addAxisElement("Wynajem nowego lokalu",
				"Siedziba firmy zostaje ulokowana w Warszawie",
				initialDateTime3 + interval, interval, 2, diagram);

		final String[] events = { "Rekrutacja nowych pracownik�w",
				"Op�ata sk�adek na ubezpieczenie",
				"Wp�ata zaliczki na podatek dochodowy",
				"Rozliczenie podatku VAT", "Redukcje etat�w" };

		final String[] descriptions = {
				"Firma przyjmuje nowych pracownik�w na umow� o prac�",
				"Sk�adki na ubezpieczenie spo�eczne, zdrowotne, Fundusz Pracy i Fundusz Gwarantowanych �wiadcze� Pracowniczych zostaj� op�acone",
				"Zaliczka za podatek dochodowy od os�b fizycznych zostaje wp�acona",
				"Podatek VAT-7 i akcyzowy za bie��cy okres zostaje rozliczony",
				"Firma jest zmuszona zredukowa� sw�j personel" };

		final long[] dates = { initialDateTime1 + 60 * interval,
				initialDateTime2 + 20 * interval,
				initialDateTime3 + 60 * interval };

		for (int i = 0; i < totalElementsCnt - 6; i++) {
			long date = dates[i % 3] + interval * (i % 30);
			dates[i%3] = date;
			addAxisElement(events[i % events.length], descriptions[i
					% descriptions.length], date, interval, i % 3, diagram);
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

	private static void addAxisElement(String name, String description,
			long date, long duration, int axisNumber,
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
