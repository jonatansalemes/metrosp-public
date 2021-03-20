import React from 'react';
import { Container } from "./styles";

const Home: React.FC = () => {

    return (
        <Container>
            <div className="alert alert-primary" role="alert">
			   Boas vindas
			</div>
        </Container>
    )
}

export default Home;