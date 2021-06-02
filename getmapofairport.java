import java.util.Map;
import java.util.HashMap;
import java.io.*;
class getmapofairport
{
    public static Map<String,String> main() throws IOException
    {
        Map<String,String> map=new HashMap<String,String>();
        BufferedReader br = new BufferedReader(new FileReader("airportscode.txt"));
        BufferedReader bw = new BufferedReader(new FileReader("airportsname.txt"));
        String text;
        while((text=br.readLine())!=null)
        {
            map.put(text,bw.readLine());
        }
        return map;
    }
}