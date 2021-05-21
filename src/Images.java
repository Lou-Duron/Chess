public enum Images {
    WSQUARE("./Images/cb.png"),
    BSQUARE("./Images/cn.png"),
    WLASTMOVE("./Images/cby.png"),
    BLASTMOVE("./Images/cny.png"),
    WCHECK("./Images/cbr.png"),
    BCHECK("./Images/cnr.png"),
    EMPTY("./Images/empty.png"),
    FILLED("./Images/filled.png"),
    WPAWN("./Images/wp.png"),
    BPAWN("./Images/bp.png"),
    WROOK("./Images/wr.png"),
    BROOK("./Images/br.png"),
    WKNIGHT("./Images/wn.png"),
    BKNIGHT("./Images/bn.png"),
    WBISHOP("./Images/wb.png"),
    BBISHOP("./Images/bb.png"),
    WQUEEN("./Images/wq.png"),
    BQUEEN("./Images/bq.png"),
    WKING("./Images/wk.png"),
    BKING("./Images/bk.png"),
    FLAG("./Images/flag.png"),
    REVERSEBOARD("./Images/inverse.png"),
    LARROW("./Images/left.png"),
    RARROW("./Images/right.png");


    private String file;

    private Images(String file){
        this.file = file;
    }

    public String getImage(){
        return file;
    }
}
