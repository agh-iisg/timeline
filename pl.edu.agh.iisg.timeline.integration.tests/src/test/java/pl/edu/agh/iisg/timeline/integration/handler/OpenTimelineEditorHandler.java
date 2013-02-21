package pl.edu.agh.iisg.timeline.integration.handler;

import java.text.ParseException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;

import pl.edu.agh.iisg.timeline.editor.TimelineEditor;

import pl.edu.agh.iisg.timeline.editor.TimelineEditorInput;
import pl.edu.agh.iisg.timeline.integration.DataGenerator;

public class OpenTimelineEditorHandler extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
        IWorkbenchPage page = window.getActivePage();
        try {
            page.openEditor(createTimelineEditorInput(), TimelineEditor.ID);
        } catch (PartInitException e) {
            e.printStackTrace();
        }
        return null;
    }

    private TimelineEditorInput createTimelineEditorInput() {
        TimelineEditorInput editorInput = new TimelineEditorInput();
        try {
            editorInput.setTimelineDiagram(DataGenerator.createRealDataDiagram(3, 100, false));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return editorInput;
    }

}
