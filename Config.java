import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import java.nio.file.*;;

public class Config extends HashMap<String, String>
{
    private String seperator;
    private String filePath;

    public Config(String filePath)
    {
        this.setFilePath(filePath);
        this.setSeperator("=");
    }

    public Config(String filePath, String seperator)
    {
        this.setFilePath(filePath);
        this.setSeperator(seperator);
    }

    private String mergeStrings(String[] strings)
    {
        String mergedString = new String();

        for (int i = 1; i < strings.length; i++)
            mergedString += strings[i];

        return mergedString;
    }

    public void deserialize(String data)
    {
        this.clear();

        for (String line : data.split("\n"))
        {
            String[] splitLine = line.split(this.getSeperator());

            if (splitLine.length > 1) 
            {
                if (!this.containsKey(splitLine[0])) 
                    this.put(splitLine[0], this.mergeStrings(splitLine));
            }
        }
    }

    public String serialize()
    {
        StringBuffer serialized = new StringBuffer();

        for (Entry<String, String> entry : this.entrySet())
        {
            serialized.append(String.format("%s=%s\n", entry.getKey(), entry.getValue()));
        }

        return serialized.toString();
    }

    public boolean read()
    {
        try
        {
            String data = new String(Files.readAllBytes(Paths.get(this.getFilePath())));
            this.deserialize(data);
            return true;
        }
        catch (IOException e)
        {
            return false;
        }
    }

    public boolean write()
    {
        try
        {
            Files.write(Paths.get(this.getFilePath()), this.serialize().getBytes());
            return true;
        } 
        catch (IOException e)
        {
            return false;
        }
    }

    public String getSeperator()
    {
        return this.seperator;
    }

    public void setSeperator(String seperator)
    {
        this.seperator = seperator;
    }

    public String getFilePath()
    {
        return this.filePath;
    }

    public void setFilePath(String filePath) 
    {
        this.filePath = filePath;
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == this)
            return true;
            
        if (!(o instanceof Config))
        {
            return false;
        }

        Config config = (Config) o;
        return Objects.equals(seperator, config.seperator) && Objects.equals(filePath, config.filePath);
    }
}
