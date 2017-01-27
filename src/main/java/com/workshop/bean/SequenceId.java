package com.workshop.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mijo on 2017-01-05.
 */
@Document(collection="sequence")
public class SequenceId {

    @Id
    private String id;
    private int seq;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    @Override
    public String toString() {
        return "SequenceId{" +
                "id='" + id + '\'' +
                ", seq=" + seq +
                '}';
    }
}
