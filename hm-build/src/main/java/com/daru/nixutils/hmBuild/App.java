package com.daru.nixutils.hmBuild;

import org.apache.commons.cli.*;

public class App {
  public static void main(String[] args) {
    CommandLineParser parser = new DefaultParser();
    HelpFormatter helper = new HelpFormatter();

    // Help option to show help options
    Option help = new Option("h", "help", false, "Show help menu");

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
        System.err.println("`build` and `switch` are mutually exclusive");
        helper.printHelp("Build Options", buildOpts);
      }

      if (buildCmd.hasOption("build") && buildCmd.hasOption("help")) {
        System.err.println("`build` and `help` are mutually exclusive");
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
