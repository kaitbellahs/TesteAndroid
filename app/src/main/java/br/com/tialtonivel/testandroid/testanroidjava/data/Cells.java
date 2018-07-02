package br.com.tialtonivel.testandroid.testanroidjava.data;

import java.util.List;

public class Cells {
    public int id;
    public int type;
    public String message;
    public  String typefield;
    public boolean hidden;
    public int topSpacing;
    public String show;
    public boolean required;
    public List<Cells> cells;

    public String toString() {
        return String.format("id:%d,type:%d,message:%s,typefield:%s,hidden:%b,topSpacing:%d,show:%s,required:%b,cells:%s",
                id, type, message, typefield, hidden, topSpacing, show, required, cells);
    }

}


