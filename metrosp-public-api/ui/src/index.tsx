import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter } from 'react-router-dom';
import GlobalStyle from './styles/global';
import App from './App';
import AppProvider, { AppContext } from './contexts/AppContext';
import { ThemeContext, ThemeProvider } from 'styled-components';
import light from './styles/theme/light';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap';
import 'popper.js/dist/popper';

ReactDOM.render(
  <React.StrictMode>
    <BrowserRouter>
      <ThemeProvider theme={light}>
        <GlobalStyle />
        <AppProvider>
          <ThemeContext.Consumer>
            {themeContext => (
              <AppContext.Consumer>
                {appContext => (
                  <App appContext={appContext} themeContext={themeContext} />
                )}
              </AppContext.Consumer>
            )}
          </ThemeContext.Consumer>
        </AppProvider>
      </ThemeProvider>
    </BrowserRouter>
  </React.StrictMode>,
  document.getElementById('root')
);

