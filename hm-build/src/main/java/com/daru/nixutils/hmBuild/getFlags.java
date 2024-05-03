package com.daru.nixutils.hmBuild;

import org.apache.commons.cli.*;

public class getFlags {
  static Options options = new Options();

  public static void main(String[] args) {

  }

  // static CommandLine = new CommandLine();
  static CommandLineParser parser = new DefaultParser();
  HelpFormatter helper = new HelpFormatter();

  static void flakes() {
    Option updateInputs = new Option("U", "update-inputs", false, "Update all the flake inputs");
    Option enableFlake = new Option("F", "flake", false, "Enable flake usage");
    Option flakeDir = Option.builder("f").longOpt("flake-dir")
        .argName("flake-dir")
        .hasArg()
        .required(true)
        .desc("Directory where the `flake.nix` is stored").build();
    Option currentDir = new Option("c", "current-dir", false,
        "Search the current working directory for a flake.nix file and use it");
    Option autoMode = new Option("a", "auto", false,
        "Get the userString and use the current working directory");
    Option userString = Option.builder("s").longOpt("user-string")
        .argName("user-string")
        .hasArg()
        .required(true)
        .desc("The user@hostname combination used to build your configuration").build();

  }

  static void extraOptions() {
    Option backup = new Option("b", "backup", false, "Backup conflicting files");
    Option showTrace = new Option("t", "show-trace", false, "Show the logs");
    Option verbose = new Option("v", "verbose", false, "Show verbose output");
    Option impure = new Option("i", "impure", false, "Enable the impure option in nix");
    Option dryRun = new Option("d", "dry-run", false, "Do not build anything, only show what is to be changed");
    options.addOption(backup);
    options.addOption(showTrace);
    options.addOption(verbose);
    options.addOption(impure);
    options.addOption(dryRun);
  }

  static void buildOperation() {
    Option hmBuild = new Option("B", "build", false, "Build the configuration");
    Option hmSwitch = new Option("S", "switch", false, "Build and switch to the configuration");
    if (hmSwitch == hmBuild) {
      // throw ;
    }
    options.addOption(hmBuild);
    options.addOption(hmSwitch);
  }
}
