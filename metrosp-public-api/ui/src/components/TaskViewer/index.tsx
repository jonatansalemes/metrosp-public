import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router';
import api from '../../api';
import { Task } from '../../api/model';
import { AppContext } from '../../contexts/AppContext';
import { Container } from "./styles";

type TaskViewerProps = {
    appContext: React.ContextType<typeof AppContext>;
}

type RouteParams = {
    alias: string
}

const TaskViewer: React.FC<TaskViewerProps> = ({ appContext }) => {

    const { alias } = useParams<RouteParams>();
    const [tasks, setTasks] = useState<Task[]>([]);

    const loading = () => {
        appContext.setLoading(true);
    };

    const unloading = () => {
        appContext.setLoading(false);
    };

    const getTasksOfCategory = async () => {
        loading();
        try {
            setTasks(await api.task.allForCategory({ alias }));
        } finally {
            unloading();
        }
    };

    const init = async () => {
        await getTasksOfCategory();
    };

    useEffect(() => {
        init();
        // eslint-disable-next-line
    }, [alias]);

    return (
        <Container>
            TaskViewer {alias}
            { tasks.length === 0 && <div>Não há tickets pendentes para</div>}
            {
                tasks.map(task => {
                    return <div>{task.requester}</div>
                })
            }

        </Container>
    )
}

export default TaskViewer;