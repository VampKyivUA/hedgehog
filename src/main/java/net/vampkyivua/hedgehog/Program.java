package net.vampkyivua.hedgehog;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.*;

/**
 * Author: Mykhailo Drozdov
 * Created: 2019-08-19 17:40
 */
public class Program {

    private static final Logger log = Logger.getLogger("main");

    public static void main(String[] args) {
        setUpLogger();
        if (args.length == 0) {
            log.severe("Input path is required");
            Runtime.getRuntime().exit(127);
        }

        String inputPath = args[0];
        String outputPath = args.length > 1 ? args[1] : inputPath + ".out";

        List<String> strings = readInput(inputPath);

        try {
            Grid grid = Grid.fromStrings(strings);
            log.fine("Grid:");
            log.fine(grid.toString());

            Path p = new PathFinder().findPath(grid);
            log.fine(p.toString());
            writeOutput(outputPath, p);
        } catch (Exception e) {
            log.severe("Error occurred during processing: " + e.getMessage());
            Runtime.getRuntime().exit(1);
        }
    }

    private static List<String> readInput(String inputPath) {
        List<String> strings = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(inputPath))) {
            while (reader.ready()) {
                String s = reader.readLine().trim();
                if (s.length() > 0) strings.add(s);
            }
        } catch (IOException e) {
            log.severe("Unable to read input file, error: " + e.getMessage());
            Runtime.getRuntime().exit(127);
        }

        return strings;
    }

    private static void writeOutput(String outputPath, Path result) {
        try (BufferedWriter w = Files.newBufferedWriter(Paths.get(outputPath))) {
            w.write(String.valueOf(result.calculateCost()));
            w.flush();
        } catch (IOException e) {
            log.severe("Unable to write to output file, error: " + e.getMessage());
        }
    }

    private static void setUpLogger() {
        try (InputStream props =
                     ClassLoader.getSystemResourceAsStream("logger.properties")) {

            LogManager.getLogManager().readConfiguration(props);
            Handler appender = new StreamHandler(System.out, new SimpleFormatter());

            if (System.getProperty("hedgehog.verbose") != null)
                appender.setLevel(Level.FINE);
            if (System.getProperty("hedgehog.debug") != null)
                appender.setLevel(Level.FINER);

            log.addHandler(appender);
            log.setUseParentHandlers(false);
        } catch (Exception e) {
            System.err.println("WARN: Unable to configure logger. Skipping.");
        }
    }
}
