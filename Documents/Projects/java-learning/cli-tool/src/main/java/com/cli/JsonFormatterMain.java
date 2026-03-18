package com.cli;

// Usage: java -jar json-formatter.jar --input file.json --output pretty.json --indent 2

public class JsonFormatterMain {

    public static void main(String[] args) {
        try {
            // Parse command-line arguments
            CommandLineArgs cliArgs = new CommandLineArgs(args);
            cliArgs.validate();

            System.out.println("JSON Formatter");

            // Get JSON from stdin or file
            String json;
            if (cliArgs.getJsonString() != null && !cliArgs.getJsonString().isEmpty()) {
                System.out.print("Processing JSON from command-line");
                json = cliArgs.getJsonString();
                System.out.println(" OK");
            } else {
                System.out.print("Reading file: " + cliArgs.getInputFile());
                json = FileReader.readFile(cliArgs.getInputFile());
                System.out.println("File Read completed");
            }

            // Format JSON
            System.out.print("Formatting JSON");
            JsonFormatter formatter = new JsonFormatter(cliArgs.getIndentSize());

            // Validate
            if (!formatter.isValid(json)) {
                System.out.println(" ERROR");
                System.err.println("Invalid JSON: " + formatter.getValidationError(json));
                System.exit(1);
            }

            String formatted = formatter.format(json);
            System.out.println("Formatted");

            // Output result
            if (cliArgs.getOutputFile() != null && !cliArgs.getOutputFile().isEmpty()) {
                System.out.print("Writing to: " + cliArgs.getOutputFile());
                FileWriter.writeFile(cliArgs.getOutputFile(), formatted);
                System.out.println("Result");
            } else {
                System.out.println("\nFormatted JSON ");
                System.out.println(formatted);
            }

            // Show statistics if requested
            if (cliArgs.isShowStats()) {
                Statistics stats = new Statistics(formatted, true);
                stats.print();
            }

            System.out.println("\nDone!");

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }
}