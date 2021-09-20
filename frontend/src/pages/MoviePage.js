import { React, useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';

export const MoviePage = () => {

    const { title } = useParams();
    const [movie, setMovie] = useState();

    useEffect(() => {
        const fetchMovie = async () => {
            const response = await fetch(`http://localhost:8090/movie/${title}`);
            const movie = await response.json();
            setMovie(movie);
        };
        fetchMovie();
    }, [title]);

    if (!movie) return null;

    return (
        <div className="MoviePage">
            <h1>{movie.title}</h1>
        </div>
    );
}
