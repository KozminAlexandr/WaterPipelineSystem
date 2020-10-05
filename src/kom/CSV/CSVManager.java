package kom.CSV;

import kom.model.Route;
import kom.model.PipeLine;
import kom.model.Point;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVManager {
    private static final String OUTPUT_HEADER = "ROUTE EXISTS; MIN LENGTH";

    public static List<PipeLine> readPipelineCSV(String path) throws IOException {
        List<PipeLine> pipeLines = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(path));

        String line = reader.readLine(); //reading and ignoring header

        while((line = reader.readLine()) != null && !line.isEmpty()) {
            String[] fields = line.split(";");
            pipeLines.add(new PipeLine(new Point(Integer.parseInt(fields[0])),
                                        new Point(Integer.parseInt(fields[1])),
                                        Integer.parseInt(fields[2])));
        }
        reader.close();
        return pipeLines;
    }

    public static List<Route> readPathCSV(String path) throws IOException {
        List<Route> routes = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(path));

        String line = reader.readLine(); //reading and ignoring header

        while((line = reader.readLine()) != null && !line.isEmpty()) {
            String[] fields = line.split(";");
            routes.add(new Route(Integer.parseInt(fields[0]),
                    Integer.parseInt(fields[1])));
        }
        reader.close();
        return routes;
    }

    public static void writeToCSV(String path, List<String> lines) {
        try {
            FileWriter fileWriter = new FileWriter(path);
            fileWriter.write(OUTPUT_HEADER + "\n");
            for(String line : lines) {
                fileWriter.write(line  + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
