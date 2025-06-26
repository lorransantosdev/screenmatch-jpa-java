package br.com.alura.screenmatch.model;

public enum Category {
    ACAO("Action"),
    ROMANCE("Romance"),
    COMEDIA("Comedy"),
    DRAMA("Drama"),
    CRIME("Crime");

    private String categoryOmdb;

    Category(String categoryOmdb) {
        this.categoryOmdb = categoryOmdb;
    }

    public static Category fromString(String text) {
        for (Category category : Category.values()) {
            if (category.categoryOmdb.equalsIgnoreCase(text)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Category not found to the String provided: " + text);
    }
}
