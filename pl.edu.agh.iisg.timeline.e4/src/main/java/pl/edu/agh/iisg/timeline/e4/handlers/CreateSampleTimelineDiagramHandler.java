 
package pl.edu.agh.iisg.timeline.e4.handlers;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;

import pl.edu.agh.iisg.timeline.common.model.TimelineDiagram;
import pl.edu.agh.iisg.timeline.common.model.generator.DataGenerator;
import pl.edu.agh.iisg.timeline.e4.parts.TimelineEditorPart;

public class CreateSampleTimelineDiagramHandler {
	
	private static final String AXES_CNT =  "pl.edu.agh.iisg.timeline.e4.commandparameter.axes.cnt";
	
	private static final String ELEMENTS_CNT =  "pl.edu.agh.iisg.timeline.e4.commandparameter.elements.cnt";
	
	private static final String EDITOR_PART_ID = "pl.edu.agh.iisg.timeline.e4.partdescriptor.editor"; 
	
	private static final String PART_STACK_ID = "pl.edu.agh.iisg.timeline.e4.partstack";
	
	@Inject
	private EPartService partService;
	
	@Inject
	private EModelService modelService;
	
	@Inject
	private MApplication application;

	@SuppressWarnings("restriction")
	@Execute
	public void execute(@Named(AXES_CNT) String axesCntParam, @Named(ELEMENTS_CNT) String elementsCntParam) {
		MPart part = createTimelineEditorPart();
		displayTimelineEditorPart(part);
		setTimelineEditorPartInput(part, Integer.parseInt(axesCntParam), Integer.parseInt(elementsCntParam));
		setFocusOnTimelineEditor(part);
	}
	
	protected TimelineDiagram getTimelineDiagram(int axesCnt, int elementsCount) {
		TimelineDiagram diagram = null;
		try {
			return DataGenerator.createSampleDiagram(axesCnt, elementsCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return diagram;
	}
	
	@SuppressWarnings("restriction")
	private MPart createTimelineEditorPart() {
		return partService.createPart(EDITOR_PART_ID);
	}
	
	@SuppressWarnings("restriction")
	private void displayTimelineEditorPart(MPart part) {
		MPartStack stack = (MPartStack)modelService.find(PART_STACK_ID, application);
		stack.getChildren().add(part);
		partService.showPart(part, PartState.ACTIVATE);
	}
	
	@SuppressWarnings("restriction")
	private void setTimelineEditorPartInput(MPart part, int axesCnt, int elementsCnt) {
		TimelineDiagram diagram = getTimelineDiagram(axesCnt, elementsCnt);
		((TimelineEditorPart)part.getObject()).setDiagram(diagram);
	}
	
	@SuppressWarnings("restriction")
	private void setFocusOnTimelineEditor(MPart part) {
		((TimelineEditorPart)part.getObject()).setFocus();
	}
}