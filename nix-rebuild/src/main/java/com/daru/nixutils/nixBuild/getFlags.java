package teaimg;

import org.apache.commons.cli.*;

public class getFlags {
  static Options options = new Options();

  public static void main(String[] args) {

  }

  static void flakes() {
    Option updateInputs = new Option("u", "Update Inputs", false, "Update the flake inputs");
  }

  static void extraOptions() {
    Option backup = new Option("b", "Backup", false, "Backup conflicting files");
    Option showTrace = new Option("t", "Show trace", false, "Show the logs");
    Option verbose = new Option("v", "Verbose", false, "Show verbose output");
    Option impure = new Option("i", "Impure", false, "Enable the impure option in nix");
    Option dryRun = new Option("d", "Dry run", false, "Do not build anything, only show what is to be changed");
  }

  static void buildOperation() {
    Option hmBuild = new Option("B", "Build", false, "Build the configuration");
    Option hmSwitch = new Option("S", "Switch", false, "Build and switch to the configuration");
    if (hmSwitch == hmBuild) {
      // throw ;
    }
    options.addOption(hmBuild);
    options.addOption(hmSwitch);
  }
}
