import java.io.IOException;
import java.util.Scanner;

public class Project
{
    public static void main(String[] args) throws IOException
    {
        Scanner sc = new Scanner(System.in);  //path
        System.out.println("Path Girin");     //path
        String path = sc.nextLine();
        City a1 = new City(path);//objeler


        System.out.println("city no: " + a1.getCityNumber());//satir1

        String[] array1 = a1.getCityLabels();   //satir2
        for(int i = 0;i< a1.getCityNumber();i++)//satir2
        {                                       //satir2
            System.out.print(array1[i]+ " ");   //satir2
        }                                       //satir2

        CountryMap a2 = new CountryMap(a1.getCityNumber() , array1 , a1.getRouteNumber() , path);//objeler

        System.out.println("\nroute no: " + a1.getRouteNumber());//satir3


        a1.Location();                               //son satir
        System.out.println("start: " +a1.getStart());//son satir
        System.out.println("end: " +a1.getEnd());    //son satir


        //BOSS

        int[][] array2 = a2.getMainArray();
        for(int i = 0; i< a1.getCityNumber();i++)
        {
            for(int j = 0;j< a1.getCityNumber();j++)
            {
                System.out.print(array2[i][j]+" ");
            }
            System.out.println();
        }


        //shortest way finding


        WayFinder a3 = new WayFinder(array2, a1.getStart(), a1.getEnd(), a1.getCityNumber(), a1.getCityLabels(),a1.getRouteNumber());
        a3.shortestRoute(a1.getStart(),a1.getEnd());

    }
}