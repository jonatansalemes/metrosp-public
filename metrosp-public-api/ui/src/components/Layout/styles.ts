import styled from 'styled-components';

export const Container = styled.div`
    display: grid;
    grid-template-columns: 150px 1fr;
    grid-template-rows: 90px 1fr;
    grid-template-areas: 
        'h h'
        's m';
    height: 100vh;

    header {
       grid-area: h;
       border:1px solid black;
    }

    aside {
       grid-area: s;
       border:1px solid black;
    }

    main {
        grid-area: m;
        border:1px solid black;
    }
`;