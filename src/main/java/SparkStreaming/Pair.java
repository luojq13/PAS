/**
 * Created by root on 4/12/17.
 */
package SparkStreaming;

public class Pair<E extends Object, F extends Object> {
    private E first;
    private F second;

    public Pair(){
    }

    public Pair(E a, F b){
        first = a;
        second = b;
    }

    public E getFirst() {
        return first;
    }

    public void setFirst(E first) {
        this.first = first;
    }

    public F getSecond() {
        return second;
    }

    public void setSecond(F second) {
        this.second = second;
    }

}
