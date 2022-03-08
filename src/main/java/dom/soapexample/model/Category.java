package dom.soapexample.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Category {

    KIDS("KIDS"),
    FICTION("FICT"),
    HORROR("HORR"),
    ROMANCE("ROM"),
    SCIENCE_FICTION("SCIFI"),
    FANTASY("FANT");

    private String abbreviation;

}
