package at.cgsit.kurs.data;

public enum TestNames {
    HERR_MANN("Herr", "Mann"),
    FRANK_ELSTNER("Frank" , "Elstner"),
    INSERTED("inserted Vornamen", "inserted Nachnamen"),
    UPDATED("updated Vornamen", "updated Nachnamen"),
    TO_DELETE("toDelete Vornamen", "toDelete Nachnamen");

    final String name;
    final String vorname;

    TestNames(String vorname, String name) {
        this.vorname = vorname;
        this.name = name;
    }

  public String getName() {
    return name;
  }

  public String getVorname() {
    return vorname;
  }
}