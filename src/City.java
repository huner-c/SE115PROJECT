import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

public class City
{
    public String[] array;
    private String start;
    private String end;
    private int cityNumber;
    private int routeNumber;
    private String[] cityLabels;


    public City() throws IOException
    {
        setCityNumber(cityNumber);
        setRouteNumber(routeNumber);
        setCityLabels(cityLabels);
        setEnd(end);
        setStart(start);
    }
    public void printCityNo()
    {
        System.out.println("Number of Cities: " + cityNumber);
        System.out.println();
    }
    public void printCityLabels()
    {
        System.out.println("City Labels: ");
        for(int i = 0;i< cityNumber;i++)
        {
            System.out.print(cityLabels[i]+ " ");
        }
        System.out.println();
    }

    public void printRouteNumber()
    {
        System.out.println();
        System.out.println("Number of Routes: " + routeNumber);
        System.out.println();
    }
    public void printStartAndEnd()
    {
        System.out.println("Start City: " + start+ "\n" + "End City: " + end);
        System.out.println();
    }
    public void setCityNumber(int cityNumber) throws IOException  //ILK SATIR
    {
        System.out.println();
        Scanner info = null;
        try
        {
            info = new Scanner(Paths.get("src/map1.txt"));
            this.cityNumber = Integer.parseInt(info.nextLine().trim());
        }
        catch (NumberFormatException wrongNoFormat)
        {
            System.err.println("Error Line 1: Invalid number of cities");
        }
    }
    public void setCityLabels(String[] cityLabels) throws IOException    //IKINCI SATIR
    {
        Scanner info = null;
        try
        {
            info = new Scanner(Paths.get("src/map1.txt"));
            info.nextLine();
            String test = info.nextLine();//"A B C D E"
            String[] test2 = test.split(" ");
            int labelno1 = test.length();
            int labelno2 = test2.length;
            if(labelno1 == labelno2*2-1)
            {
                this.cityLabels = test2; //[]"ABCDE"
            }
            else
            {
                System.err.println("Error Line 2: Invalid city labels format, expecting one space between labels");
            }
        }
        catch (Exception what)
        {
            System.err.println("test1");
        }

    }
    public void setRouteNumber(int routeNumber) throws IOException  //3. SATIR
    {
        System.out.println();
        Scanner info = null;
        try
        {
            info = new Scanner(Paths.get("src/map1.txt"));
            info.nextLine();info.nextLine();
            this.routeNumber = Integer.parseInt(info.nextLine().trim());
        }
        catch (Exception what2)
        {
            System.err.println("test2");
        }
    }

    //yollllar

    public void Location() throws IOException //SON SATIR
    {
        Scanner info = null;
        Scanner infofirst = null;
        try
        {
            info = new Scanner(Paths.get("src/map1.txt"));
            infofirst = new Scanner(Paths.get("src/map1.txt"));
            int count = 0;
            while (infofirst.hasNextLine()) //satir sayisini bulur
            {
                count++;// 1 2 3 4 5 6 7 8 9 10
                infofirst.nextLine();
            }
            for(int i = 0;i<count-1;i++) //en sondan bir onceki satiri bulur
            {
                info.nextLine();
            }
            array = info.nextLine().split(" ");
            start = array[0];
            end = array[1];

        }
        catch (Exception what3)
        {
            System.err.println("test3");
        }
    }
    public void setEnd(String end) //SON SATIR
    {
        this.end = end;
    }
    public void setStart(String start)//SON SATIR
    {
        this.start = start;
    }


    public int getCityNumber()
    {
        return cityNumber;
    }
    public String[] getCityLabels()
    {
        return cityLabels;
    }
    public int getRouteNumber()
    {
        return routeNumber;
    }
    public String getStart()
    {
        return start;
    }
    public String getEnd()
    {
        return end;
    }
}
