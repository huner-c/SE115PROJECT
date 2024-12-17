import javax.swing.plaf.synth.SynthOptionPaneUI;
import javax.xml.transform.Source;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class CountryMap
{
    private int[][] mainArray;

    public CountryMap(int cityNo , String[] cityLabels , int routeNo) //DIGER SATIRLAR
    {
        this.mainArray = new int[cityNo][cityNo];
        Scanner info = null;
        try
        {
            info = new Scanner(Paths.get("src/map1.txt"));
            info.nextLine();info.nextLine();info.nextLine(); //3 satir atladik
            for(int i = 0;i<routeNo;i++) //0 1 2 3 4 5 dongu 6 kere calisir  BU DONGUNUN AMACI 6 SATIR ASAGI INMEKTIR
            {
                String[] currentLine = info.nextLine().split(" "); //A,B,30 i her arttiginda bir alttaki satira gecer o satir []currentLine olr
                for(int j = 0;j<cityNo;j++) //0A 1B 2C 3D 4E   5 KERE CALISACAK //
                {
                    if(currentLine[0].equals(cityLabels[j]))  //o anki satirdaki 0. elemani CLJ DEMEK abcde
                    {
                        for(int p = 0;p<cityNo;p++)
                        {
                            if(currentLine[1].equals(cityLabels[p]))
                            {
                                mainArray[j][p] = Integer.parseInt(currentLine[2]);  //HER SATIR TEK TEK TARANIR CITY LABELLER TESPIT EDILIR
                            }                                                        //TESPIT SONRASI SURELER O SATIR VE SUTUNA ESITLENIR
                        }
                    }
                }
            }
        }
        catch (IOException what)
        {
            System.err.println("?");
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
