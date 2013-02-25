package pl.edu.agh.iisg.timeline.common.editpart.dynamic;

/**
 * Updates the model that should be currently visible depending on the scroll position.
 *
 * @author AGH CAST Team
 */
public interface IModelRefresher {
    ModelRefresh refresh(int scrollPos);
}
