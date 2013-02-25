package pl.edu.agh.iisg.timeline.common.util;

import java.util.Calendar;

import pl.edu.agh.iisg.timeline.common.model.Separator;


public interface ISeparatorFactory {

    Separator newSeparator(Calendar date);

}
