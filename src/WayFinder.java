import java.io.FileWriter;
import java.util.Formatter;

public class WayFinder
{
    private int[][] distances; // Şehirler arası mesafe matrisi
    private String[] cities;   // Şehirlerin isimleri

    public WayFinder(int[][] distances, String[] cities)
    {
        this.distances = distances;
        this.cities = cities;
    }
    public void findShortestPath(String startCity, String endCity)
    {
        int startIndex = -1; //start city index
        int endIndex = -1;   //end city index
        for (int i=0;i<cities.length;i++) //city labellerin icinden start/end city ye esit olanin indexini bulur
        {
            if (cities[i].equals(startCity))
            {
                startIndex = i;
            }
            if (cities[i].equals(endCity))
            {
                endIndex = i;
            }
        }
        if (startIndex==-1 || endIndex==-1) //dongu start/end bulamaz ise hata verecek
        {
            City.logError(City.count, "Invalid Start or End City");
            throw new IllegalArgumentException("Error Line: " +City.count + " Invalid Start or End City");
        }
        int[] previous = new int[cities.length]; // sehrin geldigi onceki sehir
        int[] totalDistances = new int[cities.length];//Start citymizden diger sehirlere uzakligi tutar
        boolean[] visited = new boolean[cities.length];//bir den fazla yol kullanacaksak aradaki sehirleri visited yapar
        for (int i = 0; i < cities.length; i++) //basta hepsi !visited ve uzakliklari max olsun
        {
            totalDistances[i] = Integer.MAX_VALUE;
            visited[i] = false;
            previous[i] = -1;  //
        }
        totalDistances[startIndex] = 0; //baslangic sehrinin kendine uzakligi 0

        for (int step =0; step<cities.length-1; step++) //-1 cunku baslangic haric method donduren dongu
        {
            int currentCity = getClosestCity(totalDistances, visited);
            visited[currentCity] = true;

            for (int i =0;i<cities.length; i++)
            {
                if (!visited[i] && distances[currentCity][i]>0 && totalDistances[currentCity] + distances[currentCity][i]<totalDistances[i])
                {                                                 //yukarida bulundugmuz sehrin komsu olan sehirlerle baglantisini buluyor
                    totalDistances[i] = totalDistances[currentCity] + distances[currentCity][i];
                    previous[i] = currentCity; //
                }
            }
        }
        String [] path = new String[cities.length];
        int pathIndex = 0;
        for (int t = endIndex; t != -1; t = previous[t])
        {
            path[pathIndex++] = cities[t];
        }

        String resultPath = path[--pathIndex];
        for (int i = pathIndex-1;i>=0;i--)
        {
            resultPath += " -> " + path[i]; //string toplamasi
        }

        if(totalDistances[endIndex]==Integer.MAX_VALUE)
        {
            writeOutputToFile("output.txt","No Way Found");
            System.err.println("No Way Found");
            return;
        }
        System.out.println("File read is successful");
        String result = "File read is successful"+"\nFastest Way: "+resultPath+"\nTotal Time:"+ totalDistances[endIndex]+" min";
        writeOutputToFile("output.txt",result);
    }
    public int getClosestCity(int[] totalDistances, boolean[] visited)
    {
        int minDistance = Integer.MAX_VALUE; // eger daha kisa yol varsa onu secmek icin
        int minIndex = -1; // pozitif ve 0 disinda bisi olmali
        for (int i = 0; i < totalDistances.length; i++)
        {
            if (!visited[i] && totalDistances[i]<minDistance) //ziyaret edilmediyse ve onceki ziyaretten daha kisa yol var ise degeri degistirir
            {
                minDistance = totalDistances[i];
                minIndex = i;
            }
        }
        return minIndex;
    }
    public void writeOutputToFile(String filename,String content)
    {
        Formatter formatter = null;
        FileWriter fileWriter = null;
        try
        {
            fileWriter = new FileWriter(filename,false); //append false olcak ki her baslatista icindeki silinsin
            formatter = new Formatter(fileWriter);
            formatter.format("%s%n",content); //
            fileWriter.close();
        } catch (Exception ex)
        {
            System.err.println("Something went wrong.");
        }
        finally
        {
            if (formatter != null)
            {
                formatter.close();
            }
        }
    }
}
