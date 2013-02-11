package pl.edu.agh.iisg.timeline.util;

import java.util.Calendar;

import pl.edu.agh.iisg.timeline.model.Separator;

public interface ISeparatorFactory {

    Separator newSeparator(Calendar date);

}
