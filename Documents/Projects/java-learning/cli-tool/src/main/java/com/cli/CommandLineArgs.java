package com.cli;

public class CommandLineArgs {

    private String inputFile;
    private String outputFile;
    private int indentSize = 2;
    private String jsonString;
    private boolean showStats = false;

    public CommandLineArgs(String[] args) {
        parseArgs(args);
    }

    private void parseArgs(String[] args){
        for(int i = 0; i < args.length; i++){
            switch(args[i]){
                case "--input":
                case "-i":
                    if(i+1 < args.length){
                        this.inputFile = args[++i];
                    }
                    break;
                case "--json":
                if (i + 1 < args.length) {
                    this.jsonString = args[++i];
                }
                break;

                case "--output":
                case "-o":
                    if(i+1 < args.length){
                        this.outputFile = args[++i];
                    }
                    break;

                case "--indent":
                    if(i+1 < args.length){
                        try{
                            this.indentSize = Integer.parseInt(args[++i]);
                        }
                        catch(NumberFormatException e){
                            System.err.println("Invalid indent size: " + args[i]);
                        }
                    }
                    break;

                case "--stats":
                    this.showStats = true;
                    break;

                case "--help":
                case "-h":
                    printHelp();
                    System.exit(0);
                    break;
            }
        }
    }

    private void printHelp() {
        System.out.println("JSON Formatter - Format and validate JSON files");
        System.out.println();
        System.out.println("Usage: json-formatter [options]");
        System.out.println();
        System.out.println("Options:");
        System.out.println("  --input, -i FILE      Input JSON file (required)");
        System.out.println("  --output, -o FILE     Output file (default: stdout)");
        System.out.println("  --indent SIZE         Indent size (default: 2)");
        System.out.println("  --stats               Show file statistics");
        System.out.println("  --help, -h            Show this help message");
    }

    //Getters

    public String getInputFile() { return inputFile; }
    public String getOutputFile() { return outputFile; }
    public int getIndentSize() { return indentSize; }
    public boolean isShowStats() { return showStats; }
    public String getJsonString() { return jsonString; }

    public void validate() {
        //input file or JSON string
        if ((inputFile == null || inputFile.isEmpty()) &&
                (jsonString == null || jsonString.isEmpty())) {
            System.err.println("Error: --input file OR --json string is required");
            printHelp();
            System.exit(1);
        }
    }
}