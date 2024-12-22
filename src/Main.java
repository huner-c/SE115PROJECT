import java.io.IOException;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        //String input = args[0];
        String input = "src/map1.txt";

        City a1 = new City(input);//obje (SEHIR HAKKINDA BILGILER BULUNUR)
        a1.printCityNo();//1. satir yazdir
        a1.printCityLabels();//2. satir yazdir
        String[] cityLabels = a1.getCityLabels();//labellerin tutuldugu dizi
        a1.printRouteNumber();//3. satiri yazdirir
        a1.Location(); //son satirdan start ve end i alir
        a1.printStartAndEnd();//start ve end yazilir

        CountryMap a2 = new CountryMap(a1.getCityNumber() , cityLabels , a1.getRouteNumber(), input);//obje (SEHIRDEKI YOLLAR BULUNUR)
        a2.printMainArray(a1.getCityNumber()); //ROTALARI YAZDIRAN METHOD
        
        WayFinder a3 = new WayFinder(a2.getMainArray(), a1.getCityLabels());
        a3.findShortestPath(a1.getStart(),a1.getEnd());

    }
}