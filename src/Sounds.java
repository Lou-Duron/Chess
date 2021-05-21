public enum Sounds {
    CHECK("./Sounds/check.wav"),
    EAT("./Sounds/eat.wav"),
    MOVE("./Sounds/move.wav"),
    PROMOTION("./Sounds/promotion.wav");

    private String file;

    private Sounds(String file){
        this.file = file;
    }
    
    public String getFile(){
        return file;
    }
}
