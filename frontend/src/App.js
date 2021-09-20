import './App.css';
import {HomePage} from "./pages/HomePage";
import { BrowserRouter, Route } from 'react-router-dom';
import {MoviePage} from "./pages/MoviePage";

function App() {
  return (
    <div className="App">
      <BrowserRouter>
          <Route path="/movie/:title">
              <MoviePage />
          </Route>
          <Route path="/">
              <HomePage />
          </Route>
      </BrowserRouter>
    </div>
  );
}

export default App;
