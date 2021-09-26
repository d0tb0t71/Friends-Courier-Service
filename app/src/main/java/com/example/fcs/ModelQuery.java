package com.example.fcs;

public class ModelQuery {
    String QsenderID,mssg;

    public ModelQuery() {
    }

    public ModelQuery(String qsenderID, String mssg) {
        QsenderID = qsenderID;
        this.mssg = mssg;
    }

    public String getQsenderID() {
        return QsenderID;
    }

    public String getMssg() {
        return mssg;
    }
}
