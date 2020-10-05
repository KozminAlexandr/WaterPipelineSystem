package kom;

import kom.CSV.CSVManager;
import kom.h2.H2Manager;
import kom.model.*;
import kom.water_pipeline.WaterPipelineManager;
import kom.water_pipeline.WaterPipelineUtil;

import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        List<PipeLine> pipeLines;
        List<Route> routes;
        List<Point> points;
        Set<Point> pointsSet = new HashSet<>();
        List<String> outputLines = new ArrayList<>();

        H2Manager.createPipelineTable();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter path to the file with pipelines");
        String pathToPipelineFile = scanner.nextLine();

        pipeLines = CSVManager.readPipelineCSV(pathToPipelineFile);

        pipeLines.forEach(pipeLine ->
                H2Manager.insertPipeLine(pipeLine.getSource().getName(), pipeLine.getDestination().getName(),
                        pipeLine.getLength()));

        System.out.println("Enter path to the file with routes ");
        String pathToRouteFile = scanner.nextLine();

        routes = CSVManager.readPathCSV(pathToRouteFile);

        for(PipeLine p : pipeLines) {
            pointsSet.add(p.getSource());
            pointsSet.add(p.getDestination());
        }

        points = new ArrayList<>(pointsSet);

        WaterPipelineSystem waterPipelineSystem = new WaterPipelineSystem(points, pipeLines);
        WaterPipelineManager waterPipelineManager = new WaterPipelineManager(waterPipelineSystem);

        for(Route route : routes) {
            Point startPoint = WaterPipelineUtil.findPointByName(points, route.getIdA());
            Point endPoint = WaterPipelineUtil.findPointByName(points, route.getIdB());
            String output = "";

            waterPipelineManager.execute(startPoint);
            boolean isExist = waterPipelineManager.pathExist(endPoint);
            String exist = String.valueOf(isExist).toUpperCase();
            output = output + exist + ";";
            if(isExist) {
                String minLength = String.valueOf(waterPipelineManager.getMinLength(endPoint));
                output = output + minLength;
            }

            outputLines.add(output);
        }

        System.out.println("Enter path to the directory with file name where application will create output CSV-file");
        String pathToOutputFile = scanner.nextLine();

        CSVManager.writeToCSV(pathToOutputFile, outputLines);
    }
}
