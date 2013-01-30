package pl.edu.agh.iisg.timeline.util;

import pl.edu.agh.iisg.timeline.model.Separator;

public interface ISeparatorFactory {

    Separator newSeparator(long date);

}
