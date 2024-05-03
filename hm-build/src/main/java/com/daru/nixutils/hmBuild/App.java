package com.daru.nixutils.hmBuild;

import org.apache.commons.cli.*;

public class App {
  public static void main(String[] args) {
    CommandLine cmd;
    CommandLineParser parser = new DefaultParser();
    HelpFormatter helper = new HelpFormatter();

    // Help option to show help options
    Option help = new Option("h", "help", false, "Show help menu");

    // Flake usage
    Options flakeOpts = new Options();
    flakeOpts.addOption(help);
    flakeOpts.addOption(new Option("U", "update-inputs", false, "Update all the flake inputs"));
    flakeOpts.addOption(Option.builder("u").longOpt("flake-inputs")
        .argName("config")
        .hasArg()
        .required(true)
        .desc("Update a specific set of onputs").build());
    flakeOpts.addOption(Option.builder("f").longOpt("flake-dir")
        .argName("flake-dir")
        .hasArg()
        .required(true)
        .desc("Directory where the `flake.nix` is stored").build());
    flakeOpts.addOption(new Option("c", "current-dir", false,
        "Search the current working directory for a flake.nix file and use it"));
    flakeOpts.addOption(new Option("a", "auto", false,
        "Get the userString and use the current working directory"));
    flakeOpts.addOption(Option.builder("s").longOpt("user-string")
        .argName("user-string")
        .hasArg()
        .required(true)
        .desc("The user@hostname combination used to build your configuration").build());

    // General options
    // TODO: Add other options like builders and cores
    Options hmOpts = new Options();
    hmOpts.addOption(help);
    hmOpts.addOption(new Option("b", "backup", false, "Backup conflicting files"));
    hmOpts.addOption(new Option("t", "show-trace", false, "Show the logs"));
    hmOpts.addOption(new Option("v", "verbose", false, "Show verbose output"));
    hmOpts.addOption(new Option("i", "impure", false, "Enable the impure option in nix"));
    hmOpts.addOption(new Option("d", "dry-run", false, "Do not build anything, only show what is to be changed"));

    // Build options

    Options buildOpts = new Options();
    buildOpts.addOption(help);
    buildOpts.addOption(new Option("B", "build", false, "Build the configuration, incompatible with `switch`"));
    buildOpts.addOption(
        new Option("S", "switch", false, "Build and switch to the configuration, incompatible with `build`"));
    buildOpts.addOption(
        new Option("N", "news", false, "Show home-manager news (Nothing will be built), mutually exclusive"));

    // Make sure that options are all compatible
    try {
      CommandLine buildCmd = parser.parse(buildOpts, args);

      if (buildCmd.hasOption("build") && buildCmd.hasOption("switch")) {
        System.err.println("`suild` and `switch` are mutually exclusive");
        helper.printHelp("Build Options", buildOpts);
      }

      if (buildCmd.hasOption("build") && buildCmd.hasOption("help")) {
        System.err.println("`suild` and `help` are mutually exclusive");
        helper.printHelp("Build Options", buildOpts);
      }

      if (buildCmd.hasOption("switch") && buildCmd.hasOption("help")) {
        System.err.println("`switch` and `help` are mutually exclusive");
        helper.printHelp("Build Options", buildOpts);
      }
    } catch (ParseException e) {
      System.out.println("Unexpected exception:" + e.getMessage());
    }
  }
}
