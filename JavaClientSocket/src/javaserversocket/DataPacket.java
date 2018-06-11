
package javaserversocket;

import java.io.Serializable;
import java.util.Random;

/**
 * @author Richard Nader <richard.nader@fynydd.com>
 */
public class DataPacket implements Serializable {

    private static final long serialVersionUID = -5399605122490343339L;

    private double[] pos;

    private String name;

    public DataPacket(String name, int dataLength) {
        this.name = name;

        pos = new double[dataLength];

        for (int i = 0; i < pos.length; i++) {
            pos[i] = new Random().nextDouble();
        }
    }

    public double[] getPos() {
        return pos;
    }

    public void setPos(double[] pos) {
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
        return "DataPacket{" + "pos=" + pos + ", name=" + name + '}';
    }

}
