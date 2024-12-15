public class WayFinder
{
    private int [][] ways;
    private String start;
    private String end;
    private int startSatir;
    private int endSutun;

    public WayFinder(int[][] ways, String start, String end)
    {
        this.ways = ways;
        this.start = start;
        this.end = end;
        setStartSatir(startSatir);
        setEndSutun(endSutun);
    }

    public void shortestRoute()
    {
        for(int i = 0;i<5;i++)
        {
            System.out.println(ways[0][i]);
        }
        System.out.println(endSutun);
        System.out.println(startSatir);
    }

    public void setStartSatir(int startSatir)
    {
        this.startSatir = startSatir;
    }

    public void setEndSutun(int endSutun)
    {
        this.endSutun = endSutun;
    }
}
