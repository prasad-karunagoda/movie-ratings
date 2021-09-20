import { React } from 'react';
import { Link } from 'react-router-dom';

export const MovieCard = ({movie}) => {

    const movieRoute = `/movie/${movie.title}`;

    return (
        <div className="MovieCard">
            <h2><Link to={movieRoute}>{movie.title}</Link></h2>
        </div>
    );
}
