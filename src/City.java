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
    private String path;

    public City(String path) throws IOException
    {
        this.path = path;
        setCityNumber(cityNumber);
        setRouteNumber(routeNumber);
        setCityLabels(cityLabels);
        setEnd(end);
        setStart(start);
    }

    public void setCityNumber(int cityNumber) throws IOException
    {
        System.out.println();
        Scanner info = null;
        try
        {
            info = new Scanner(Paths.get(path));
            this.cityNumber = Integer.parseInt(info.nextLine().trim());
        }
        catch (Exception wrongPath)
        {
            System.err.println("file not found");
        }
    }
    public void setCityLabels(String[] cityLabels)
    {
        Scanner info = null;
        try
        {
            info = new Scanner(Paths.get(path));
            info.nextLine();
            this.cityLabels = info.nextLine().split(" ");
        }
        catch (NullPointerException wrongpoint)
        {
            System.err.println("unnamed error occured");

        } catch (IOException wtf) {
            System.err.println("unnamed error2 occured");
            throw new RuntimeException(wtf);
        }
    }

    public void setRouteNumber(int routeNumber)
    {
        System.out.println();
        Scanner info = null;
        try
        {
            info = new Scanner(Paths.get(path));
            info.nextLine();info.nextLine();
            this.routeNumber = Integer.parseInt(info.nextLine().trim());

        }
        catch (Exception wrongPath)
        {
            System.err.println("file not found");
        }
    }

    //yollllar

    public void Location()
    {
        Scanner info = null;
        Scanner infofirst = null;
        try
        {
            info = new Scanner(Paths.get(path));
            infofirst = new Scanner(Paths.get(path));
            int count = 0;
            while (infofirst.hasNextLine())
            {
                count++;
                infofirst.nextLine();
            }
            for(int i = 0;i<count-1;i++)
            {
                info.nextLine();
            }
            array = info.nextLine().split(" ");
            end = array[1];
            start = array[0];
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
    public void setEnd(String end)
    {
        this.end = end;
    }
    public void setStart(String start)
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
