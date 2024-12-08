import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class City
{
    public char city1;
    public char city2;
    public int numberofcities;
    public char [] labels = new char[numberofcities];
    public int numberofroutes;


    public void cityMap()
    {
        Scanner info = new Scanner(System.in);
        System.out.println("Okunacak dosya adi girin");
        String dosya = info.nextLine();
        try (BufferedReader okuyucu = new BufferedReader(new FileReader(dosya)))
        {
            String sonSatir = null;
            String satir;
            int lineNumber=0;
            String importantInfo1=null;
            String importantInfo2=null;

            while ((satir = okuyucu.readLine()) != null)
            {
                sonSatir = satir;
                lineNumber++;
                if(lineNumber == 1)
                {
                    importantInfo1 = satir;
                }
                if(lineNumber == 2)
                {
                    String newSatir = satir.replace(" ", "");
                    labels = newSatir.toCharArray();
                    for(int i= 0;i<newSatir.length();i++)
                    {
                        System.out.print(labels[i]+" ");
                    }
                }
                if(lineNumber == 3)
                {
                    importantInfo2 = satir;
                }
            }
            if(sonSatir != null)
            {
                String whereToWhere = sonSatir.replace(" ", "");
                city1 = whereToWhere.charAt(0);
                city2 = whereToWhere.charAt(1);
            }
            numberofcities = Integer.parseInt(importantInfo1.trim());
            numberofroutes = Integer.parseInt(importantInfo2.trim());
            System.out.println();
            System.out.println("City no : " + numberofcities);
            System.out.println("Route no : " + numberofroutes);
            WayFinder o3 = new WayFinder(city1,city2);

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
