package com.daru.nixutils.hmBuild;

import org.apache.commons.cli.*;

public class flakes extends App {
  public static void flakeOptions() {
    // Flake usage
    Options flakeOpts = new Options();

    Option enableFlake = new Option("F", "flake", false, "Enable flake usage");
    Option help = new Option("h", "help", false, "Show flake usage");
    Option updateInputs = new Option("U", "update-inputs", false, "Update all the flake inputs");
    Option flakeInputs = Option.builder("u").longOpt("flake-inputs")
        .argName("config")
        .hasArg()
        .required(true)
        .desc("Update a specific set of onputs").build();
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

    // Add the options
    flakeOpts.addOption(help);
    flakeOpts.addOption(enableFlake);
    flakeOpts.addOption(updateInputs);
    flakeOpts.addOption(flakeInputs);
    flakeOpts.addOption(flakeDir);
    flakeOpts.addOption(currentDir);
    flakeOpts.addOption(autoMode);
    flakeOpts.addOption(userString);

    getDir(flakeDir);
    getFlake(flakeDir);
    getInputs(flakeInputs);
  }

  // TODO: Get these working properly
  private static void getFlake(Option flakeDir) {

  }

  private static void getDir(Option flakeDir) {

  }

  private static void getInputs(Option flakeInputs) {

  }
}
