package pl.edu.agh.iisg.timeline.integration.handler;

import pl.edu.agh.iisg.timeline.integration.DataGenerator;
import pl.edu.agh.iisg.timeline.model.TimelineDiagram;

public class CreateRealDataTimelineDiagramHandler extends
		CreateSampleTimelineDiagramHandler {

	@Override
	protected TimelineDiagram getTimelineDiagram(int axesCnt, long elementsCont)
			throws Exception {
		return DataGenerator.createRealDataDiagram(axesCnt, elementsCont,false);
	}
}
