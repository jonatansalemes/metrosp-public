import React from 'react';
import { Route } from 'react-router';
import Aside from './components/Aside';
import Header from './components/Header';
import Layout from './components/Layout';
import Main from './components/Main';
import { AppContext } from './contexts/AppContext';
import Home from './components/Home';
import { ThemeContext } from 'styled-components';
import Loading from './components/Loading';
import TaskViewer from './components/TaskViewer';

type AppProps = {
    appContext: React.ContextType<typeof AppContext>;
    themeContext: React.ContextType<typeof ThemeContext>;
}

const App: React.FC<AppProps> = ({ appContext, themeContext }) => {
    return (
        <Layout>
            <Loading enabled={appContext.loading} />
            <Header />
            <Aside appContext={appContext} />
            <Main>
                <Route exact path='/' component={Home} />
                <Route path='/tasks/:alias' render={props => <TaskViewer {...props} appContext={appContext} />} />
            </Main>
        </Layout>
    );
};

export default App;
