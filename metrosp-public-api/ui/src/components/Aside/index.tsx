import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import api from '../../api';
import { TaskCategory } from '../../api/model';
import { AppContext } from '../../contexts/AppContext';
import { Container } from "./styles";
import { faCommentAlt } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';


type AsideProps = {
    appContext: React.ContextType<typeof AppContext>;
}

const Aside: React.FC<AsideProps> = ({ appContext }) => {

    const [taskCategories, setTaskCategories] = useState<TaskCategory[]>([]);

    const getTaskCategories = async () => {
        setTaskCategories(await api.taskCategory.all());
    };

    const loading = () => {
        appContext.setLoading(true);
    };

    const unloading = () => {
        appContext.setLoading(false);
    };

    const init = async () => {
        loading();
        try {
            await getTaskCategories();
        } finally {
            unloading();
        }
    };

    useEffect(() => {
        init();
        // eslint-disable-next-line
    }, []);

    return (
        <Container>
            <hr className="divisor" />
            <div className="menu">
                {
                    taskCategories.map((taskCategory, index) => {
                        return (
                            <div key={index} className="menu-item">
                                <FontAwesomeIcon icon={faCommentAlt} /> <Link to={`/tasks/${taskCategory.alias}`}>
                                    {taskCategory.name}
                                </Link>
                            </div>
                        )
                    })
                }
            </div>
        </Container>
    )
}

export default Aside;