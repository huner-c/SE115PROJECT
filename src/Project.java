import java.io.IOException;


public class Project
{
    public static void main(String[] args) throws IOException
    {


        City a1 = new City();//obje (SEHIR HAKKINDA BILGILER BULUNUR)
        a1.printCityNo();//1. satir yazdir
        a1.printCityLabels();//2. satir yazdir
        String[] cityLabels = a1.getCityLabels();//labellerin tutuldugu dizi
        a1.printRouteNumber();//3. satiri yazdirir
        a1.Location(); //son satirdan start ve end i alir
        a1.printStartAndEnd();//start ve end yazilir

        CountryMap a2 = new CountryMap(a1.getCityNumber() , cityLabels , a1.getRouteNumber());//obje (SEHIRDEKI YOLLAR BULUNUR)
        int[][] arrayOfRoutes = a2.getMainArray();// ROTALAR TUTAN ARRAY
        a2.printMainArray(a1.getCityNumber()); //ROTALARI YAZDIRAN METHOD
        
        WayFinder a3 = new WayFinder(a2.getMainArray(), a1.getCityLabels());
        a3.findShortestPath(a1.getStart(),a1.getEnd());

    }
}