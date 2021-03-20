import styled from 'styled-components';

export const Container = styled.header`
   color: #fff;
   display: flex;
   align-items: center;
   justify-content: center;
   background-color: ${props => props.theme.colors.primary};
`;