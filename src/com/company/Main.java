package com.company;

public class Main {

    public static void main(String[] args) {
        NoteManager noteManager = new NoteManager();

        // Create a new note
        noteManager.createNote("Shopping List", "1. Milk\n2. Bread\n3. Eggs");

        // Edit the note with id 1
        noteManager.editNote(1, "Grocery List", "1. Milk\n2. Bread\n3. Eggs\n4. Butter");

        // Delete the note with id 1
        noteManager.deleteNote(1);

        noteManager.closeConnection();
    }

    }

