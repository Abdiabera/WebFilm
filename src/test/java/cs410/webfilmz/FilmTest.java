package cs410.webfilmz;

/*

        Author : Professor Ryan Culpepper

        Student name : Abdi Abera
        Subject: cs410
        Date : 10/12/2023

        */
/*
 *
 * ADD YOUR User TESTS TO THIS FILE
 *
 */

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FilmTest {

    @Test
    void testIsAppropriateFor() {

        Catalog catalog = BaseCatalogTest.getCatalog();
        //film name
        Film terminator = catalog.findByTitle("The Terminator"); // R
        Film toyStory = catalog.findByTitle("Toy Story"); //R

        //this will test if film is higher rating in R
        assertTrue(terminator.isAppropriateFor(Rating.R));
        //test if the film is the same rating with PG13
        assertFalse(terminator.isAppropriateFor(Rating.PG13));
        //test if the film is the same rating with R
        assertTrue(toyStory.isAppropriateFor(Rating.R));
        //test if the film is the same rating with G
        assertTrue(toyStory.isAppropriateFor(Rating.G));
    }
}
