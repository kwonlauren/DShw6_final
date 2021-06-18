import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.PriorityQueue;

public class Graph {
    static final int INFINITY = 2147483647;
    Hashtable<String, Station> graph;//key: 고유번호

    public Graph(){
        graph = new Hashtable<>();
    }

    public void insert_vertex(String key, String name, String line){
        Station st = new Station(key, name, line);
        graph.put(key, st);
    }

    public void insert_edge(String origin, String dest, int time){
        Hashtable<String, Integer> ht = graph.get(origin).adjacent;
        ht.put(dest, time);
    }

    public void initialize(PriorityQueue<Station> undetermined, String origin, String destination){
        // origin, destination은 역 이름(고유번호 x)
        for(Map.Entry<String, Station> entry : graph.entrySet()){ // for every station
            Station st = entry.getValue();
            if(st.name.equals(origin)) {
                st.dist = 0;
            } else{
                st.dist = INFINITY;
            }
            undetermined.add(st);//모든 노드를 undetermined에 추가
        }
    }


    public Hashtable<String, Integer> search_adjacent(String station_num){
        return graph.get(station_num).adjacent;
    }

    public Station getStation(String key){
        return graph.get(key);
    }
}
