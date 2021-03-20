import React, { createContext, useState } from 'react';

type AppContextProps = {
    children?: any;
    loading: boolean;
    setLoading(loading: boolean): void;
};

type AppProviderProps = {

}

export const AppContext = createContext<AppContextProps>({} as AppContextProps);

const AppProvider: React.FC<AppProviderProps> = ({ children }) => {

    const [loading, setLoading] = useState<boolean>(false);

    return (
        <AppContext.Provider value={{ children, loading, setLoading }}>
            {children}
        </AppContext.Provider>
    )
}
export default AppProvider;