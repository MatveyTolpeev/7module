package ru.service.db.servlets;

public class FileDir {
  public boolean directory;

  public FileDir(boolean isDirectory, String name, String shortName) {
    this.directory = isDirectory;
    this.name = name;
    this.shortName = shortName;
  }

  public void setDirectory(boolean directory) {
    this.directory = directory;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setShortName(String shortName) {
    this.shortName = shortName;
  }

  public boolean isDirectory() {
    return directory;
  }

  public String getName() {
    return name;
  }

  public String getShortName() {
    return shortName;
  }

  public String name;

  public String shortName;
}
