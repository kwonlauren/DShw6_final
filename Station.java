import java.util.Hashtable;

public class Station implements Comparable<Station>{
    static final long INFINITY = 9223372036854775807L;
    public String key;
    public String name;
    public String line;
    public long dist;
    boolean visited;
    Hashtable<String, Long> adjacent;

    public Station(String key, String name, String line){
        this.key = key;
        this.name = name;
        this.line = line;
        this.dist = INFINITY;
        this.visited = false;
        this.adjacent = new Hashtable<>();
    }

    public Station(String key, String name, String line, long dist){
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