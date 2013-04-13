package pl.edu.agh.iisg.timeline.e4.handlers;
 

import java.text.ParseException;

import pl.edu.agh.iisg.timeline.common.example.generator.DataGenerator;
import pl.edu.agh.iisg.timeline.common.model.TimelineDiagram;

public class CreateLongTextsAndIconsTimelineDiagramHandler extends CreateSampleTimelineDiagramHandler {

	@Override
	protected TimelineDiagram getTimelineDiagram(int axesCnt, int elementsCount) {
		TimelineDiagram diagram = null;
		try {
			diagram = DataGenerator.createRealDataDiagram(axesCnt, elementsCount, true);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return diagram;
	}
		
}