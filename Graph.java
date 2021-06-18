import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;
import java.util.PriorityQueue;

public class Graph {
    static final long INFINITY = 9223372036854775807L;
    Hashtable<String, Station> graph;//key: 고유번호
    Hashtable<String, ArrayList<String>> name_key; //이름을 key로 가지고 고유번호들(환승역) list를 value로 가짐.

    public Graph(){
        graph = new Hashtable<>();
        name_key = new Hashtable<>();
    }

    public void insert_vertex(String key, String name, String line){
        Station st = new Station(key, name, line);
        graph.put(key, st);
        if(name_key.containsKey(name)){ //이미 name이 있는 경우
            for(String key2 : name_key.get(name)){
                insert_edge(key, key2, 5);
                insert_edge(key2, key, 5);
            }
            name_key.get(name).add(key);
        } else{ //name이 없는 경우
            ArrayList<String> lst = new ArrayList<>();
            lst.add(key);
            name_key.put(name,lst);
        }
    }

    public void insert_edge(String origin, String dest, long time){
        Hashtable<String, Long> ht = graph.get(origin).adjacent;
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
            st.visited = false;
            undetermined.add(st);//모든 노드를 undetermined에 추가
        }
    }


    public Hashtable<String, Long> search_adjacent(String station_num){
        return graph.get(station_num).adjacent;
    }

    public Station getStation(String key){
        return graph.get(key);
    }
}
