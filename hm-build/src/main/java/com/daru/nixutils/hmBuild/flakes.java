package com.daru.nixutils.hmBuild;

import org.apache.commons.cli.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class flakes extends App {
  public static void flakeOptions() {
    // Flake usage
    Options flakeOpts = new Options();

    // Enable flakes
    Option enableFlake = new Option("F", "flake", false, "Enable flake usage");

    // Print help
    Option help = new Option("h", "help", false, "Show flake usage");

    // Updating inputs
    Option updateInputs = new Option("U", "update-inputs", false, "Update all the flake inputs");
    Option flakeInputs = Option.builder("u").longOpt("flake-inputs")
        .argName("config")
        .hasArg()
        .required(true)
        .desc("Update a specific set of onputs").build();

    // Specifying the flake directory
    Option flakeDir = Option.builder("f").longOpt("flake-dir")
        .argName("flake-dir")
        .hasArg()
        .required(true)
        .desc("Directory where the `flake.nix` is stored").build();
    Option currentDir = new Option("c", "current-dir", false,
        "Search the current working directory for a flake.nix file and use it");

    // Getting user@hostname
    Option autoMode = new Option("a", "auto", false,
        "Get the configuration url automatically and use the current working directory");
    Option configUrl = Option.builder("s").longOpt("flake-url")
        .argName("Flake URL")
        .hasArg()
        .required(true)
        .desc("The user@hostname (url) of the configuration in the flake.nix file").build();

    // Add the options
    flakeOpts.addOption(help);
    flakeOpts.addOption(enableFlake);
    flakeOpts.addOption(updateInputs);
    flakeOpts.addOption(flakeInputs);
    flakeOpts.addOption(flakeDir);
    flakeOpts.addOption(currentDir);
    flakeOpts.addOption(autoMode);
    flakeOpts.addOption(configUrl);

    getDir(flakeDir);
    getFlake(flakeDir, currentDir);
    getInputs(flakeInputs);
  }

  // TODO: Get these working properly
  private static void getFlake(Option flakeDir, Option currentDir) {
    CommandLine cmd;
    CommandLineParser cmdParser = new DefaultParser();
    Options flakeDirs = new Options();
    flakeDirs.addOption(flakeDir);
    flakeDirs.addOption(currentDir);

    String flakeDirString[] = { flakeDir.getValue(), currentDir.getValue() };
    // cmd = cmdParser.parse(flakeDirs,flakeDir);

  }

  private final static String flakeUrl(Option mode, Option flakeUrl, Options options) {
    // Get the user and hostname
    String hostname;
    String username;
    String url;
    if (options.hasOption(mode.getLongOpt())) {
      return (flakeUrl.getValue());
    } else {
      try {
        Runtime rt = Runtime.getRuntime();
        String[] command = { "hostname" };
        Process proc = rt.exec(command);

        BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
        // Read the output from the command

        String tmpStr = null;
        while ((tmpStr = stdInput.readLine()) != null) {
          hostname = "a" + tmpStr;
        }
        while ((tmpStr = stdError.readLine()) != null) {
          System.err.println("Error getting hostname: " + tmpStr);
        }
        url = hostname;
      } catch (IOException e) {
        System.err.println("Error getting flake url: " + e.getMessage());
      }
      try {
        Runtime rt = Runtime.getRuntime();
        String[] command = { "echo", "$USER" };
        Process proc = rt.exec(command);

        BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
        // Read the output from the command

        String tmpStr = null;
        while ((tmpStr = stdInput.readLine()) != null) {
          username = tmpStr;
        }
        while ((tmpStr = stdError.readLine()) != null) {
          System.err.println("Error getting username: " + tmpStr);
        }
        url = username + url;
      } catch (IOException e) {
        System.err.println("Error getting flake url: " + e.getMessage());
      }
      return url;
    }
  }

  private static void getDir(Option flakeDir) {

  }

  private static void getInputs(Option flakeInputs) {

  }
}
