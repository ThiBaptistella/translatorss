package au.com.translatorss.utils;

public enum CategoryEnum {

    bi_dea_mar_cer("Birth, Death or Marriage Certificate"),
    pass("Passport"),
    per_pri("Personal / Private"),
    drivLic("Drivers License"),
    police("Police"),
    aca_trans("Academic Records / Transcripts"),
    insurance("Insurance"),
    business_doc("Business Documents"),
    
    trainingManual("Training Manual"),
    api("API"),
    website("Web Site"),
    legalDocu("Legal Documents"),
    salesMark("Sales &amp; Marketing"),
    
    technical("Technical"),
    medical("Medical"),
    vidtran_trans("Video Transcription / Translation"),

    gaming("Gaming"),
    other("Other");
    
    private final String value;

    public String value() {
        return value;
    }
    
    CategoryEnum(String v) {
        value = v;
    }
    
    public static CategoryEnum fromValue(String v) {
        for (CategoryEnum c: CategoryEnum.values()) {
            if (c.name().equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
   
}
