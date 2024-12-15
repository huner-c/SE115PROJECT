import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class CountryMap
{
    private int[][] mainArray;
    private String[] currentArray1;

    public CountryMap(int cityNo , String[] cityLabels , int routeNo , String path)
    {
        this.mainArray = new int[cityNo][cityNo];
        this.currentArray1 = new String[10];

        Scanner info = null;
        try
        {
            info = new Scanner(Paths.get(path));
            info.nextLine();info.nextLine();info.nextLine();
            for(int i = 0;i<routeNo;i++)
            {
                String[] currentArray = info.nextLine().split(" "); //A,B,30 i her arttiginda bir alttaki satira gecer
                for(int j = 0;j<cityNo;j++) //0A 1B 2C 3D 4E
                {
                    if(currentArray[0].equals(cityLabels[j]))
                    {
                        for(int p = 0;p<cityNo;p++)
                        {
                            if(currentArray[1].equals(cityLabels[p]))
                            {
                                mainArray[j][p] = Integer.parseInt(currentArray[2]);
                            }
                        }
                    }
                }
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }


    public int[][] getMainArray()
    {
        return mainArray;
    }
}
