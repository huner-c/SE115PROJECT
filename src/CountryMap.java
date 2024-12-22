
import java.nio.file.Paths;
import java.util.Scanner;

public class CountryMap
{
    private int[][] mainArray;
    int currentLineNumber = 3;
    String path;

    public CountryMap(int cityNo , String[] cityLabels , int routeNo, String path) //DIGER SATIRLAR
    {
        this.path = path;
        this.mainArray = new int[cityNo][cityNo];
        Scanner info = null;
        try
        {
            info = new Scanner(Paths.get(path));
            info.nextLine();info.nextLine();info.nextLine(); //3 satir atladik
            for(int i = 0;i<routeNo;i++) //0 1 2 3 4 5 dongu 6 kere calisir  BU DONGUNUN AMACI 6 SATIR ASAGI INMEKTIR
            {
                String[] currentLine = info.nextLine().split(" ");//A,B,30 i her arttiginda bir alttaki satira gecer o satir []currentLine olr
                if(currentLine.length != 3)
                {
                    City.logError(i+4, "Expecting ONE space between labels and times");
                    System.err.println("Error Line: "+(i+4)+ " Expecting ONE space between labels and times");
                    return;
                }
                currentLineNumber++;
                for(int j = 0;j<cityNo;j++) //0A 1B 2C 3D 4E   5 KERE CALISACAK //
                {
                    if(currentLine[0].equals(cityLabels[j]))  //o anki satirdaki 0. elemani CLJ DEMEK abcde
                    {
                        for(int p = 0;p<cityNo;p++)
                        {
                            if(currentLine[1].equals(cityLabels[p]))
                            {
                                mainArray[j][p] = Integer.parseInt(currentLine[2]);//HER SATIR TEK TEK TARANIR CITY LABELLER TESPIT EDILIR
                                mainArray[p][j] = Integer.parseInt(currentLine[2]);//TESPIT SONRASI SURELER O SATIR VE SUTUNA ESITLENIR
                            }
                        }
                    }
                }
            }
        }
        catch (Exception otherLinesError)
        {
            City.logError(currentLineNumber ,"Integer Time Expected");
            System.err.println("Error Line: " + currentLineNumber + " Integer Time Expected");
            System.exit(0);
        }
    }
    public void printMainArray(int cityNo)
    {
        for(int i = 0; i< cityNo;i++)
        {
            for(int j = 0;j< cityNo;j++)
            {
                System.out.print(mainArray[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public int[][] getMainArray()
    {
        return mainArray;
    }
}
