package at.cgsit.kurs.startup;

enum TestNames {
    CHRIS("Chris"),
    FRANK("Frank"),
    INSERTED("insertedName"),
    UPDATED("updatedName"),
    TO_DELETE("toDelete");

    final String value;

    TestNames(String value) {
      this.value = value;
    }
  }