import { React } from 'react';

export const MovieCard = ({movie}) => {
    return (
        <div className="MovieCard">
            <h2>{movie.title}</h2>
        </div>
    );
}
