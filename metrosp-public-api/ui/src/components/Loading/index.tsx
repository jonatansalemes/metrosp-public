import React from 'react';
import { Container } from './styles';

export type LoadingProps = {
    enabled: boolean;
}

const Loading: React.FC<LoadingProps> = ({ enabled }) => {
    if (enabled) {
        return (
            <Container>
                <div className="ui-loading">
                    <div className="ui-loading-indicator">
                        <div className="square animation-delay-02s"></div>
                        <div className="square animation-delay-03s"></div>
                        <div className="square animation-delay-04s"></div>
                        <div className="square animation-delay-01s"></div>
                        <div className="square animation-delay-02s"></div>
                        <div className="square animation-delay-03s"></div>
                        <div className="square animation-delay-0s"></div>
                        <div className="square animation-delay-01s"></div>
                        <div className="square animation-delay-02s"></div>
                    </div>
                </div>
            </Container>
        );
    }
    return <></>;
};

export default Loading;