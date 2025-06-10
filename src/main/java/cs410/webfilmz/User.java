package cs410.webfilmz;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* Represents a user (subscriber to the service), including
 * their watch/like history.
 * Responsible for maintaining watch/like history, expressing
 * film preferences, and getting personalized recommendations.
 * Refers to and updates Film.
 * Relies on Catalog to implement recommendations.
 */
public class User implements ILikeFilm {
    /* Sets of films watched and liked, respectively.
     * Uses Set to avoid recording watch/like multiple times.
     */
    private final Set<Film> watched;
    private final Set<Film> liked;
    private final Set<String> isLikedDirector;
    private final Set<String> isLikedGenre;

    // Maximum acceptable rating for recommendations.
    private final Rating limitRating;

    public User(Rating limitRating) {
        this.watched = new HashSet<>();
        this.liked = new HashSet<>();
        this.limitRating = limitRating;
        this.isLikedGenre = new HashSet<>();
        this.isLikedDirector= new HashSet<>();
    }

    public User() {
        this(Rating.R);
    }

    // Record that the user watched/liked the given film,
    // updating film if not already in Set.
    public void addWatched(Film film) {
        if (watched.add(film)) {
            film.incrementWatched(1);
        }
    }

    public void addLiked(Film film) {
        if (liked.add(film)) {
            film.incrementLiked(1);
            isLikedDirector.add(film.director());
            isLikedGenre.add(film.genre());
        }
    }


    public Set<Film> getRecommendationsByGenre(Catalog catalog) {

        Set<Film> recommendationBygenre = catalog.getRecommendationsByGenre(this);
        recommendationBygenre.removeIf(watched::contains);
        //return directorRecommendations;
        //return catalog.getRecommendationsByDirector(this);
        recommendationBygenre.removeIf(film -> !film.rating().isAppropriateFor(limitRating));
        return recommendationBygenre;
    }
    public Set<Film> RecommendationsNewReleases(Catalog catalog, int initialGenericRecsCount) {

        Set<Film> newReleases = catalog.getRecommendationsByYear(initialGenericRecsCount);
        newReleases.removeAll(watched);

        newReleases.removeIf(film -> !film.rating().isAppropriateFor(limitRating));

        return newReleases;
    }
//updating each methods
    public Map<String, Set<Film>> getAllRecommendations(Catalog catalog, int initialGenericRecsCount) {

        //we have to filter wich already watched films
        Set<Film> newReleases = RecommendationsNewReleases(catalog, initialGenericRecsCount);
        newReleases.removeIf(watched :: contains);
        //this removes if the rating if their rating is not Appropriate
        newReleases.removeIf(film -> !film.rating().isAppropriateFor(limitRating));

        Set<Film> favDirectors = getRecommendationsByDirector(catalog);
        favDirectors.removeIf(watched ::contains);
        //this removes if the rating if their rating is not Appropriate
        favDirectors.removeIf(film -> !film.rating().isAppropriateFor(limitRating));

        Set<Film> favGenres =catalog.getRecommendationsByGenre(this);
        favGenres.removeIf(watched :: contains);
        //this removes  the rating if their rating is not Appropriate
        favGenres.removeIf(film -> !film.rating().isAppropriateFor(limitRating));

        return Map.of(
                "New Releases", newReleases,
                // catalog.getRecommendationsByYear(initialGenericRecsCount)
                "Favorite Genres",favGenres,
                "Favorite Directors", favDirectors,
                // getRecommendationsByDirector(catalog)),
                    "Most Watched",catalog.getRecommendationsMostWatched(initialGenericRecsCount),
                "Most Liked",catalog.getRecommendationsMostLiked(initialGenericRecsCount));
    }

    public Set<Film> getRecommendationsByDirector(Catalog catalog) {

        Set<Film> directorRecommendations = catalog.getRecommendationsByDirector(this);
        directorRecommendations.removeIf(watched::contains);
        //return directorRecommendations;
        //return catalog.getRecommendationsByDirector(this);
        //this removes if the rating if their rating is not Appropriate
      directorRecommendations.removeIf(film -> !film.isAppropriateFor(limitRating));
        return directorRecommendations;
        //public Set<Film> getRecommendationsByGenre(Catalog catalog)

        // Do any of the films the user liked have the given
        // director/genre?
    }

    public boolean isLikedDirector(String director) {
        for (Film film : liked) {
            if (film.director().equals(director)) return true;
        }
        return false;
    }

    public boolean isLikedGenre(String genre) {
        for (Film film : liked) {
            if (film.genre().equals((genre))) return true;
        }
        return false;
    }
}
