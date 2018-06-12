package javaserversocket;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;

/**
 * @author Richard Nader <richard.nader@fynydd.com>
 */
public class DataPacket implements Serializable {

    private static final long serialVersionUID = -5399605122490343339L;

    private Double[] pos;

    private String name;

    public DataPacket(String name, Double[] data) {
        this.name = name;

        this.pos = data;

    }

    public Double[] getPos() {
        return pos;
    }

    public void setPos(Double[] pos) {
        this.pos = pos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DataPacket{" + "pos length=" + pos.length + ", name=" + name + '}';
    }

}
