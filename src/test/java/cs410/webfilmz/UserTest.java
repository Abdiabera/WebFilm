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

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserTest {

    @Test
    void yourFirstTestCase() {
        return;
    }


    @Test
    void getRecommendationsNewReleases() {
        Catalog catalog = BaseCatalogTest.getCatalog();

        User alice = new User();
        alice.addWatched(catalog.findByTitle("The Martian"));

        Set<Film> expectedRecommendation =
                Set.of(
                        catalog.findByTitle("Oppenheimer"),
                        catalog.findByTitle("Inception"));

        Set<Film> actualRecommendation = alice.getAllRecommendations(catalog, 3).get("New Releases");

        assertTrue(actualRecommendation.containsAll(expectedRecommendation));
        assertTrue(expectedRecommendation.containsAll(actualRecommendation));

    }
    @Test
    void getRecommendationsNewReleasesRating() {
        Catalog catalog = BaseCatalogTest.getCatalog();

        User alice = new User();
        alice.addWatched(catalog.findByTitle("The Martian"));

        Set<Film> expectedRecommendation =
                Set.of(
                        catalog.findByTitle("Oppenheimer"),
                        catalog.findByTitle("Inception"));
        Set<Film> actualRecommendation = alice.getAllRecommendations(catalog, 3).get("New Releases");
        assertTrue(actualRecommendation.containsAll(expectedRecommendation));
        assertTrue(expectedRecommendation.containsAll(actualRecommendation));

    }
    @Test
    void getRecommendationsByDirector() {
        Catalog catalog = BaseCatalogTest.getCatalog();

        User alice = new User();

        Film amelie = catalog.findByTitle("Amelie");
        alice.addWatched(amelie);
        alice.addLiked(amelie);

        // this used to create expected recommendation
        Set<Film> expectedRecommendation = new HashSet<>();
        expectedRecommendation.add(catalog.findByTitle("The City of Lost Children"));

        assertEquals(expectedRecommendation, alice.getAllRecommendations(catalog,3).get("Favorite Directors"));
    }
    @Test
    void getRecommendationsByDirectorRating() {
        Catalog catalog = BaseCatalogTest.getCatalog();
        User alice = new User();
        Film amelie = catalog.findByTitle("Amelie");
        alice.addWatched(amelie);
        alice.addLiked(amelie);
        // this used to create expected recommendation
        Set<Film> expectedRecommendation = new HashSet<>();
        expectedRecommendation.add(catalog.findByTitle("The City of Lost Children"));
        assertEquals(expectedRecommendation, alice.getAllRecommendations(catalog,3).get("Favorite Directors"));
    }
    @Test
    void getRecommendationsByGenre() {
        Catalog catalog = BaseCatalogTest.getCatalog();
        User alice = new User(Rating.PG13);
        Film terminator = catalog.findByTitle("The Terminator");
        alice.addWatched(terminator);
        alice.addLiked(terminator);
        // this used to create expected recommendation
        Set<Film> expectedRecommendation = new HashSet<>();
        expectedRecommendation.add(catalog.findByTitle("The Martian"));
        expectedRecommendation.add(catalog.findByTitle("Inception"));
        assertEquals(expectedRecommendation, alice.getAllRecommendations(catalog, 3).get("Favorite Genres"));


    }
//method getRecommendationsByGenresRating
    @Test
    void getRecommendationsByGenresRating() {
//an object of catalog
        Catalog catalog = BaseCatalogTest.getCatalog();
        //user
        User alice = new User(Rating.PG13);
        Film terminator = catalog.findByTitle("The Terminator");
        alice.addWatched(terminator);
        alice.addLiked(terminator);
        // this used to create expected recommendation
        Set<Film> expectedRecommendation = new HashSet<>();
        //expectedRecommendation.add(catalog.findByTitle("The City of Lost Children"));
        expectedRecommendation.add(catalog.findByTitle("The Martian"));
        expectedRecommendation.add(catalog.findByTitle("Inception"));
        assertEquals(expectedRecommendation, alice.getAllRecommendations(catalog,3).get("Favorite Genres"));
    }
        @Test
    void testRatings() {
        Catalog catalog = BaseCatalogTest.getCatalog();
        User bobby = new User(Rating.G);

        assertEquals(Set.of(catalog.findByTitle("Toy Story")),
                bobby.getAllRecommendations(catalog, 10).get("New Releases"));
    }
}


