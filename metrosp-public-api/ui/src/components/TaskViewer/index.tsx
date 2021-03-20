import { faBookReader, faCircleNotch } from '@fortawesome/free-solid-svg-icons';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import moment from 'moment';
import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router';
import api from '../../api';
import { Task } from '../../api/model';
import { AppContext } from '../../contexts/AppContext';
import { Container } from "./styles";
import $ from 'jquery';

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

    const open = (task: Task) => {
        $('#' + task.uuid).modal('show');
    }

    const close = (task: Task) => {
        $('#' + task.uuid).modal('hide');
    }

    useEffect(() => {
        init();
        // eslint-disable-next-line
    }, [alias]);


    return (
        <Container>
            <div className="my-1">
                <button className="btn btn-outline-primary" onClick={getTasksOfCategory}>
                    <FontAwesomeIcon icon={faCircleNotch} /> Atualizar dados
                </button>
            </div>
            <div>
                <table className="table table-striped table-hover">
                    <thead className="table-dark">
                        <tr>
                            <th>#</th>
                            <th>Requisitante</th>
                            <th>Data/Hora</th>
                            <th>Conteúdo</th>
                        </tr>
                    </thead>
                    <tbody>
                        {tasks.length === 0 && <tr><td colSpan={4} className="text-center">Não há atividades disponíveis para atuação</td></tr>}
                        {
                            tasks.map((task, index) => {
                                return <tr key={index}>
                                    <td><div className="text-truncate truncate">{task.uuid}</div></td>
                                    <td>{task.requester}</td>
                                    <td>{moment(task.createdAt).format('DD/MM/YYYY HH:MM:SS')}</td>
                                    <td>
                                        <button type="button" className="btn btn-outline-dark" onClick={ev => open(task)}>
                                            <FontAwesomeIcon icon={faBookReader} /> Visualizar
                                        </button>
                                        <div className="modal fade" id={task.uuid}>
                                            <div className="modal-dialog">
                                                <div className="modal-content">
                                                    <div className="modal-header">
                                                        <h5 className="modal-title">Descrição</h5>
                                                    </div>
                                                    <div className="modal-body">
                                                        <p>{task.content}</p>
                                                    </div>
                                                    <div className="modal-footer">
                                                        <button type="button" className="btn btn-secondary" onClick={ev => close(task)}>Fechar janela</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                            })
                        }
                    </tbody>
                </table>
            </div>
        </Container>
    )
}

export default TaskViewer;