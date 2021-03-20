import styled from 'styled-components';

export const Container = styled.div`
    display: grid;
    grid-template-columns: 180px 1fr;
    grid-template-rows: 90px 1fr;
    grid-template-areas: 
        'h h'
        's m';
    height: 100vh;

    header {
       grid-area: h;
    }

    aside {
       grid-area: s;
    }

    main {
        grid-area: m;
    }
`;