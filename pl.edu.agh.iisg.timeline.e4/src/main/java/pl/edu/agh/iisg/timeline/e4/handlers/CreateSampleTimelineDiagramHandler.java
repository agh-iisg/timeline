 
package pl.edu.agh.iisg.timeline.e4.handlers;

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;

import pl.edu.agh.iisg.timeline.common.model.TimelineDiagram;
import pl.edu.agh.iisg.timeline.common.model.generator.DataGenerator;
import pl.edu.agh.iisg.timeline.e4.parts.TimelineEditorPart;

public class CreateSampleTimelineDiagramHandler {
	
	private static final String AXES_CNT =  "pl.edu.agh.iisg.timeline.e4.commandparameter.axes.cnt";
	
	private static final String ELEMENTS_CNT =  "pl.edu.agh.iisg.timeline.e4.commandparameter.elements.cnt";

	@Execute
	public void execute(@Named(AXES_CNT) String axesCntParam, @Named(ELEMENTS_CNT) String elementsCntParam, MPart part) {
		TimelineDiagram diagram = getTimelineDiagram(Integer.parseInt(axesCntParam), Integer.parseInt(elementsCntParam));
		((TimelineEditorPart)part.getObject()).setDiagram(diagram);
	}
	
	private TimelineDiagram getTimelineDiagram(int axesCnt, int elementsCount) {
		TimelineDiagram diagram = null;
		try {
			return DataGenerator.createSampleDiagram(axesCnt, elementsCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return diagram;
	}
		
}