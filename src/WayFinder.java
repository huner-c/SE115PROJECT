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
        for (int i = 0; i < cities.length; i++) //city labellerin icinden start/end city ye esit olanin indexini bulur
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
        if (startIndex == -1 || endIndex == -1) //dongu start/end bulamaz ise hata verecek
        {
            City.logError(City.count ,"Invalid city names: " + startCity + " or " + endCity + " not found!");
            throw new IllegalArgumentException("Invalid city names: " + startCity + " or " + endCity + " not found!");
        }




        int[] previous = new int[cities.length]; // Şehirlerin geldiği önceki şehir
        int[] totalDistances = new int[cities.length];//Start citymizden diger sehirlere uzakligi tutar
        boolean[] visited = new boolean[cities.length];//bir den fazla yol kullanacaksak aradaki sehirleri visited yapar
        for (int i = 0; i < cities.length; i++) //basta hepsi !visited ve uzakliklari max olsun
        {
            totalDistances[i] = Integer.MAX_VALUE;
            visited[i] = false;
            previous[i] = -1; // yeni ekledik
        }
        totalDistances[startIndex] = 0; //baslangic sehrinin kendine uzakligi 0

        for (int step = 0; step < cities.length-1; step++) //-1 cunku baslangic haric method donduren dongu
        {
            int currentCity = getClosestCity(totalDistances, visited);
            visited[currentCity] = true;

            // Komşu şehirlerin mesafelerini güncelle//
            for (int i = 0; i < cities.length; i++)
            {
                if (!visited[i] && distances[currentCity][i] > 0 && totalDistances[currentCity] + distances[currentCity][i] < totalDistances[i])
                {
                    totalDistances[i] = totalDistances[currentCity] + distances[currentCity][i];
                    previous[i] = currentCity; // yeni yazdik
                }
            }
        }
        String [] path = new String[cities.length];
        int pathIndex = 0;

        for (int at = endIndex; at != -1; at = previous[at]) {
            path[pathIndex++] = cities[at];
        }

        // Rota tersine çevrilerek düzenlenir
        StringBuilder resultPath = new StringBuilder();

        for (int i = pathIndex - 1; i >= 0; i--) {
            resultPath.append(path[i]);
            if (i > 0) {
                resultPath.append(" -> ");
            }
        }
        if(totalDistances[endIndex]==Integer.MAX_VALUE)
        {
            writeOutputToFile("output.txt","No Way Found");
            System.exit(0);
        }
        String result = "File read is successful!" + "\nFastest Way: " + resultPath + "\nTotal Time: " + totalDistances[endIndex] + " min";
        writeOutputToFile("output.txt", result);
    }
    private int getClosestCity(int[] totalDistances, boolean[] visited)
    {
        int minDistance = Integer.MAX_VALUE; // eger daha kisa yol varsa onu secmek icin
        int minIndex = -1; // pozitif disinda bisi olmali
        for (int i = 0; i < totalDistances.length; i++)
        {
            if (!visited[i] && totalDistances[i] < minDistance) //ziyaret edilmediyse ve onceki ziyaretten daha kisa yol var ise degeri degistirir
            {
                minDistance = totalDistances[i];
                minIndex = i;
            }
        }
        return minIndex;
    }
    public void writeOutputToFile(String filename, String content)
    {
        Formatter formatter = null;
        FileWriter fileWriter = null;
        try
        {
            fileWriter = new FileWriter(filename, false); //append false olcak ki her baslatista icindeki silinsin
            formatter = new Formatter(fileWriter);
            formatter.format("%s%n", content); //
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
