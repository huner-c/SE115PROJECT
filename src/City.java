import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class City
{
    public String[] array;
    private String start;
    private String end;
    private int cityNumber;
    private int routeNumber;
    private String[] cityLabels;
    static int count = 0;
    String path;


    public City(String path) throws IOException
    {
        this.path = path;
        setCityNumber(cityNumber);
        setRouteNumber(routeNumber);
        setCityLabels(cityLabels);
        setEnd(end);
        setStart(start);
    }
    public void printCityNo() throws IOException {
        setCityNumber(0);
        if(cityNumber == 0)
        {
            logError(1,"Wrong Format");
            System.err.println("Error Line: 1 City Number cannot be 0 or empty");
            return;
        }
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
    }
    public void printRouteNumber()
    {
        setRouteNumber(0);
        if(routeNumber == 0)
        {
            System.err.println("Error Line: 3 Route Number cannot be 0 or empty");
            return;
        }
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
            info = new Scanner(Paths.get(path));
            int line = Integer.parseInt(info.nextLine().trim());
            this.cityNumber = line;
        }
        catch (NumberFormatException wrongNoFormat)
        {
            logError(1, "City Number must be integer");
            System.err.println("Error Line: 1 City Number must be integer");
        }
    }
    public void setCityLabels(String[] cityLabels)     //IKINCI SATIR
    {
        Scanner info = null;
        try
        {
            info = new Scanner(Paths.get(path));
            info.nextLine();
            String test = info.nextLine();//"A B C D E" length 9
            String[] test2 = test.split(" "); //[A],[B] ... length 5
            if(test2.length*2-1 != test.length())
            {
                System.err.println("Error Line: 2 Expecting ONE space between city labels");
            }
            if(test.isEmpty())
            {
                System.err.println("Error Line: 2 No City Label Found");
                return;
            }
            if(test2.length != cityNumber)
            {
                System.err.println("Error Line: 2 Number of city labels does not match city count");
                logError(2, "Number of city labels does not match city count");
                return;
            }
            int labelno1 = test.length();
            int labelno2 = test2.length;
            if(labelno1 == labelno2*2-1)
            {
                this.cityLabels = test2; //[]"ABCDE"
            }
            else
            {
                logError(2, "Expecting ONE space between labels");
                System.err.println("Error Line: 2 Expecting ONE space between labels");
            }
        }
        catch (Exception WrongLabelsFormat)
        {

            logError(2, "Error reading city labels");
        }

    }
    public void setRouteNumber(int routeNumber)   //3. SATIR
    {
        System.out.println();
        Scanner info = null;
        try
        {
            info = new Scanner(Paths.get(path));
            info.nextLine();info.nextLine();
            this.routeNumber = Integer.parseInt(info.nextLine().trim());
        }
        catch (Exception wrongFormatRoute)
        {
            System.err.println("Error Line: 3 Integer number of route expected");
            logError(3, "Integer number of route expected");
        }
    }
    public void Location() throws IOException //SON SATIR
    {
        Scanner info = null;
        Scanner infofirst = null;
        try
        {
            info = new Scanner(Paths.get(path));
            infofirst = new Scanner(Paths.get(path));

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
            if(array.length != 2)
            {
                logError(count, " Expecting ONE space between start and end labels");
                System.err.println("Error Line: " +count+ " Expecting ONE space between start and end labels");
            }
            if(array.length > 2)
            {
                logError(count, " Only ONE Start and End city Allowed");
                System.err.println("Error Line: " +count+ " Only ONE Start and End city Allowed");
            }

            setStart(array[0]);
            setEnd(array[1]);
        }
        catch (Exception wrongStartandEndCities)
        {
            System.err.println("Error Line: " +count+  " Error reading start and end cities");
            logError(count, "Error reading start and end cities");
        }
        if((count-4) != routeNumber)
        {
            logError(3, "Route number does not match");
            throw new IOException("Error Line: 3 Route number does not match ");
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


    public static void logError(int lineNumber, String description)
    {
        try (FileWriter writer = new FileWriter("output.txt", false);
             Formatter formatter = new Formatter(writer))
        {
            formatter.format("Error Line: %d %s%n", lineNumber, description);
        } catch (IOException e)
        {
            System.err.println("Failed to write error to file: " + e.getMessage());
        }
    }
}
