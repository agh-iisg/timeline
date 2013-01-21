package pl.edu.agh.iisg.timeline.handler;

import pl.edu.agh.iisg.timeline.DataGenerator;
import pl.edu.agh.iisg.timeline.model.TimelineDiagram;

public class CreateLongTextsAndIconsTimelineDiagramHandler extends CreateSampleTimelineDiagramHandler {

	@Override
	protected TimelineDiagram getTimelineDiagram(int axesCnt, long elementsCont)
			throws Exception {
		return DataGenerator.createRealDataDiagram(axesCnt, elementsCont,true);
	}
}
