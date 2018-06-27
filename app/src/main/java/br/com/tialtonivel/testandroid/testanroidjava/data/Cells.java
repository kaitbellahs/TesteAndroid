package br.com.tialtonivel.testandroid.testanroidjava.data;

import java.util.List;

public class Cells {


    private int id;
    private int type;
    private String message;
    private  String typefield;
    private boolean hidden;
    private int topSpacing;
    private String show;
    private boolean required;
    private List<Cells> cells;

    public int getID() { return id; }
    public int getType() { return type; }
    public String getMessage() { return message; }
    public String getTypefield() { return typefield; }
    public boolean getHidden() { return hidden; }
    public int getTopSpacing() { return topSpacing; }
    public String getShow() { return show; }
    public boolean getRequired() { return required; }
    public List<Cells> getCells() { return cells; }

    public void setId(int id) { this.id = id; }
    public void setType(int type) { this.type = type; }
    public void setMessage(String message) { this.message = message; }
    public void setTypefield(String typefield) { this.typefield = typefield; }
    public void setHidden(boolean hidden) { this.hidden = hidden; }
    public void setTopSpacing(int topSpacing) { this.topSpacing = topSpacing; }
    public void setShow(String show) { this.show = show; }
    public void setRequired(boolean required) { this.required = required; }
    public void setCells(List<Cells> cells) { this.cells = cells; }

    public String toString() {
        return String.format("id:%d,type:%d,message:%s,typefield:%s,hidden:%b,topSpacing:%d,show:%s,required:%b,cells:%s",
                id, type, message, typefield, hidden, topSpacing, show, required, cells);
    }

}

