import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.*;

public class Subway {
    public static void main(String[] args) throws Exception{
        Graph map = new Graph();
        BufferedReader br = new BufferedReader(new FileReader(args[0]));
        String line;
        while (!(line = br.readLine()).isEmpty()) {
            //graph에 vertex 추가
            String[] strs = line.split(" ");
            map.insert_vertex(strs[0], strs[1], strs[2]);
        }
        while ((line = br.readLine()) != null) {
            //graph에 edge 추가
            String[] strs = line.split(" ");
            map.insert_edge(strs[0], strs[1], Long.parseLong(strs[2]));
        }

        BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
        while (true)
        {
            String input = br2.readLine();
            if(input.compareTo("QUIT") == 0) break;
            String[] strs = input.split(" ");
            String output = dijkstra(strs[0], strs[1], map);
            System.out.println(output);
        }

    }

    public static String dijkstra(String origin, String dest, Graph map){
        //origin_num(이름)에서 dest_num(이름)으로의 최단경로 역 이름들 List, 소요시간 return

        PriorityQueue<Station> undetermined = new PriorityQueue<>();
        Hashtable<Station, Station> prev = new Hashtable<>();
        ArrayList<String> path = new ArrayList<>();
        long time;

        map.initialize(undetermined, origin, dest);

        Station top;
        while(true){
            top = undetermined.remove();
            top.visited = true;
            if(top.name.equals(dest)) break;
            Hashtable<String, Long> adjacent = map.search_adjacent(top.key);
            for(Map.Entry<String, Long> entry : adjacent.entrySet()){
                Station adjSt = map.getStation(entry.getKey());
                if(!adjSt.visited){//unvisited
                    long alt = top.dist + entry.getValue();
                    if(alt < adjSt.dist){ //relaxation
                        undetermined.remove(adjSt);//없애볼수도?
                        adjSt.dist = alt;
                        undetermined.add(adjSt);
                        prev.put(adjSt, top);
                    }
                }
            }
        }
        //여기서 top은 destination
        time = top.dist;
        while(!top.name.equals(origin)){
            path.add(top.name);
            top = prev.get(top);
        }
        path.add(top.name); //origin 한번 추가
        Collections.reverse(path);
        return dij_to_str(path, time);
    }

    private static String dij_to_str(ArrayList<String> path, long time){
        StringBuilder str = new StringBuilder();
        long len = path.size();
        for(int i=0; i<len; i++){
            if((i<len-1) && path.get(i+1).equals(path.get(i))){
                str.append("[");
                str.append(path.get(i));
                str.append("] ");
                i++;
            } else{
                str.append(path.get(i));
                str.append(" ");
            }
        }
        str = new StringBuilder(str.toString().trim());
        str.append("\n");
        str.append(time);
        return str.toString();
    }
}
