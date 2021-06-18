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

    @Override
    public int compareTo(Station o) {
        return Long.compare(this.dist, o.dist);
    }
}