// more for fun than for actual purposes

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

public class ReadNadeFile
{
    public static void main(String [] args) throws FileNotFoundException
    {
        File file = new File("custom file.nade");
        String filename = "custom file.nade";
        Scanner scanner = new Scanner(file);
        for(int i = 0; i < 2; i++){scanner.nextLine();}
        for(int j = 3; j < file.length(); j++)
        {
            if(scanner.hasNext())
            {

                if(isInt(getLine(j, filename)))
                {
                    System.out.println(getIntVarFromLine(getLine(j, filename)));
                }
                else if(isString(getLine(j, filename)))
                {
                    System.out.println(getStringVarFromLine(getLine(j, filename)));
                }
                else if(isBool(getLine(j, filename)))
                {
                    System.out.println(getBoolVarFromLine(getLine(j, filename)));
                }
                else
                {
                    System.out.println("Error, no associated type! | Line " + j +" : " + getLine(j, filename));
                }
                scanner.nextLine();
            }
        }
    }

    public static String getLine(int lineIndex, String filename)
    {
        String lineData = "";
        try(Stream<String> lines = Files.lines(Paths.get(filename)))
        {
            lineData = lines.skip(lineIndex - 1).findFirst().get();
        }
        catch(Exception e){}
        return lineData;
    }

    public static int getIntVarFromLine(String line)
    {
        return Integer.parseInt(getStringVarFromLine(line).strip());
    }

    public static boolean isInt(String line)
    {
        if(line.startsWith("int =") || line.startsWith("int="))
        {
            return true;
        }
        else{return false;}
    }

    public static String getStringVarFromLine(String line)
    {
        return line.substring(line.indexOf("=") + 1, line.indexOf(";"));
    }

    public static boolean isString(String line)
    {
        if(line.startsWith("String=") || line.startsWith("String ="))
        {
            return true;
        }
        else{return false;}
    }

    public static boolean getBoolVarFromLine(String line)
    {
        return Boolean.parseBoolean(getStringVarFromLine(line).strip());
    }

    public static boolean isBool(String line)
    {
        if(line.startsWith("boolean=") || line.startsWith("boolean ="))
        {
            return true;
        }
        else{return false;}
    }
}