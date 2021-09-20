import { React, useEffect, useState } from 'react';
import {MovieCard} from "../components/MovieCard";

export const HomePage = () => {

    const [recentMovies, setRecentMovies] = useState();

    useEffect(() => {
        const fetchMovies = async () => {
            const response = await fetch('http://localhost:8090/movie/recent');
            const recentMovies = await response.json();
            setRecentMovies(recentMovies);
        };
        fetchMovies();
    }, []);

    if (!recentMovies) return null;

    return (
        <div className="HomePage">
            <h1>Movies</h1>
            <input type="text" />
            <button>Search</button>
            {recentMovies.map(movie => <MovieCard movie={movie} />)}
        </div>
    );
}
