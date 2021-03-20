import styled from 'styled-components';

export const Container = styled.aside`
   
   color: #fff;
   background-color: ${props => props.theme.colors.primary};
   padding: 2px;

   a {
       color: #fff;
   }

   a:hover {
       text-decoration: none;
       
   }

   hr {
       margin: 0;
       color: ${props => props.theme.colors.primary};
   }

   .menu {
        padding:5px;
   }

   .menu-item {
       padding: 3px;
       cursor: pointer;
   }

   .menu-item:hover {
       padding: 3px;
       background-color: ${props => props.theme.colors.hover};
       transition: background-color 0.5s ease-in;
   }

  
`;