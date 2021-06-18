import java.util.Hashtable;

public class Station implements Comparable<Station>{
    static final int INFINITY = 2147483647;
    public String key;
    public String name;
    public String line;
    public int dist;
    boolean visited;
    Hashtable<String, Integer> adjacent;

    public Station(String key, String name, String line){
        this.key = key;
        this.name = name;
        this.line = line;
        this.dist = INFINITY;
        this.visited = false;
        this.adjacent = new Hashtable<>();
    }

    public Station(String key, String name, String line, int dist){
        this.key = key;
        this.name = name;
        this.line = line;
        this.dist = dist;
        this.visited = false;
        this.adjacent = new Hashtable<>();
    }

    public void clear(){
        this.dist = INFINITY;
    }

    @Override
    public int compareTo(Station o) {
        int ans;
        if(this.dist > o.dist) return 1;
        else if(this.dist < o.dist) return -1;
        else return 0;
    }
}