import javax.swing.*;

public class WayFinder
{
    private int [][] ways;
    private String startCity;
    private String endCity;
    private int cityNo;
    private String[] cityLabels;
    private int routeNo;

    public WayFinder(int[][] ways, String startCity, String endCity, int cityNo, String[] cityLabels, int routeNo)
    {
        this.ways = ways;
        this.startCity = startCity;
        this.endCity = endCity;
        this.cityNo=cityNo;
        this.cityLabels = cityLabels;
        this.routeNo=routeNo;
    }

    public void shortestRoute(String startCity, String endCity)
    {
        int[] distances = new int[cityNo];
        boolean[] visited = new boolean[cityNo];
        int[] previous = new int[cityNo];

        for(int i = 0; i<cityNo; i++) //pre ve distances lere ilk degerler ataniyor
        {
            distances[i] = Integer.MAX_VALUE;
            previous[i] =-1;
        }
        distances[getCityIndex(startCity)] = 0;  //A index no 0 distances[0] = 0 a esitlenir

        while(true)
        {
            int u = -1;
            for(int i = 0;i<cityNo; i++)
            {
                if(!visited[i] && (u==-1 || distances[i]<distances[u]))// hata yok mu ?
                {
                    u = i;
                }
            } // ilk while  dongusunde for dan u=0 olarak cikar
            if(u == -1 || distances[u] == Integer.MAX_VALUE)
            {
                break;
            }
            visited[u] = true;
            for(int v = 0;v<cityNo;v++) // asil dongu
            {
                if(ways[u][v] > 0 && !visited[v])
                {
                    int alt= distances[u] + ways[u][v];
                    if(alt < distances[v])
                    {
                        distances[v] = alt;
                        previous[v] = u;
                    }
                }
            }
        }
        printShortestPath(distances, previous,getCityIndex(endCity));
    }

    public int getCityIndex(String city)// girilen city labelin index no bulur mesela start A icin 0 D icin 3
    {
        for(int i = 0; i<cityLabels.length;i++)//5 kere calisan dongu
        {
            if(cityLabels[i].equals(city)) return i; //eger CL[] den bi eleman girilen paramereye esitse index bulundu
        }
        throw new IllegalArgumentException("Invalid city " + city);
    }

    private void printShortestPath(int[] distances, int[] previous, int endIndex)
    {
        if(distances[endIndex] == Integer.MAX_VALUE)
        {
            System.out.println("No Path Found");
        }
        else
        {
            System.out.println("Shortest Path: ");
            printPath(previous, endIndex);
            System.out.println("\nDistance: "+distances[endIndex]);
        }
    }
    private void printPath(int[] previous, int index)
    {
        if(previous[index] != -1)
        {
            printPath(previous, previous[index]);
        }
        System.out.println(cityLabels[index] + " ");
    }
}