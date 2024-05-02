{
  description = "Scripts for managing nixos systems written in java";

  inputs = {
    nixpkgs.url = "github:nixos/nixpkgs/nixos-unstable";
    mavenix.url = "github:nix-community/mavenix";
    flake-parts.url = "github:hercules-ci/flake-parts";
  };

  outputs = {
    flake-parts,
    mavenix,
    self,
    ...
  } @ inputs:
    flake-parts.lib.mkFlake {inherit inputs;} {
      systems = [
        "x86_64-linux"
        "aarch64-linux"
        "x86_64-darwin"
        "aarch64-darwin"
      ];
      perSystem = {
        pkgs,
        system,
        ...
      }: let
        mavenix = mavenix.defaultPackage.${system};
      in {
        packages = {
          hm-build = mavenix.buildMaven ./hm-build;
          nix-rebuild = mavenix.buildMaven ./nix-rebuild;
        };
        devShells.default = pkgs.mkShell {
          buildInputs = with pkgs; [
            maven
          ];
        };
      };
    };
}
